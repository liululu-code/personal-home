package top.lll44556.codeGenerator.parser;

import lombok.AllArgsConstructor;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColDataType;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.table.Index;
import net.sf.jsqlparser.schema.Table;
import org.springframework.stereotype.Component;
import top.lll44556.codeGenerator.enums.SqlTypeCategory;
import top.lll44556.codeGenerator.model.ColumnMeta;
import top.lll44556.codeGenerator.model.TableMeta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Component
@AllArgsConstructor
public class CreateTableSqlParser {

    private final PostgresqlTypeClassifier postgresqlTypeClassifier;

    /**
     * 解析入口：只接受单条 CREATE TABLE SQL，避免把非建表语句误传给后续代码生成模板。
     */
    public TableMeta parse(String sql) {
        try {
            // 先交给 JSQLParser 做语法解析，避免后续代码直接依赖字符串切割判断 SQL 类型。
            Statement statement = CCJSqlParserUtil.parse(sql);

            // 当前生成器只围绕建表 SQL 产出代码，非 CREATE TABLE 语句没有足够元数据可生成。
            if (!(statement instanceof CreateTable createTable)) {
                throw new IllegalArgumentException("当前仅支持 CREATE TABLE SQL");
            }

            // 将第三方 AST 转为内部模型，保证模板和生成策略不被 JSQLParser API 绑定。
            return toTableMeta(createTable);
        } catch (JSQLParserException e) {
            throw new IllegalArgumentException("建表 SQL 解析失败", e);
        }
    }

    /**
     * 表级元数据组装：把 JSQLParser 的 AST 转成项目内部稳定模型，后续模板不直接依赖第三方 AST。
     */
    private TableMeta toTableMeta(CreateTable createTable) {
        // 表名先做归一化，内部模型使用无引号名称进行比较和后续命名转换。
        Table table = createTable.getTable();
        String schemaName = normalizeIdentifier(table.getSchemaName());
        String tableName = normalizeIdentifier(table.getName());

        // 先填充表级信息，fullTableName 保留 schema，模板可直接用于生成 INSERT 目标表。
        TableMeta tableMeta = new TableMeta();
        tableMeta.setSchemaName(schemaName);
        tableMeta.setTableName(tableName);
        tableMeta.setFullTableName(buildFullTableName(schemaName, tableName));

        // 表级主键约束会影响字段假数据策略，所以需要在解析字段前先提取。
        Set<String> primaryKeyNames = parsePrimaryKeyNames(createTable);
        tableMeta.setPrimaryKeyNames(new ArrayList<>(primaryKeyNames));

        // 字段顺序沿用原始建表 SQL，确保生成的 INSERT 字段列表和 SELECT 表达式一一对应。
        List<ColumnMeta> columns = new ArrayList<>();
        for (ColumnDefinition columnDefinition : createTable.getColumnDefinitions()) {
            ColumnMeta columnMeta = toColumnMeta(columnDefinition, primaryKeyNames);
            columns.add(columnMeta);
        }
        tableMeta.setColumns(columns);

        return tableMeta;
    }

    /**
     * 列级元数据组装：统一提取字段名、类型、长度、默认值、可空性和主键信息。
     */
    private ColumnMeta toColumnMeta(ColumnDefinition columnDefinition, Set<String> primaryKeyNames) {
        // 同时保留原始字段名和归一化字段名：原始名用于还原双引号，归一化名用于匹配主键。
        String rawColumnName = columnDefinition.getColumnName();
        String columnName = normalizeIdentifier(rawColumnName);

        // 类型名统一转小写，减少 PostgreSQL 类型别名判断时的大小写分支。
        ColDataType colDataType = columnDefinition.getColDataType();
        String dataType = colDataType.getDataType().toLowerCase(Locale.ROOT);
        List<String> specs = columnDefinition.getColumnSpecs();

        // 主键可能来自表级约束，也可能直接写在字段定义上，两种形式都需要兼容。
        boolean primaryKey = primaryKeyNames.contains(columnName) || containsSpecSequence(specs, "PRIMARY", "KEY");

        // 字段模型集中保存模板需要的所有信息，避免模板里写复杂判断。
        ColumnMeta columnMeta = new ColumnMeta();
        columnMeta.setColumnName(columnName);
        columnMeta.setQuotedColumnName(toSqlIdentifier(rawColumnName, columnName));
        columnMeta.setDataType(dataType);
        columnMeta.setLength(parseLength(colDataType));
        columnMeta.setNullable(!containsSpecSequence(specs, "NOT", "NULL"));
        columnMeta.setPrimaryKey(primaryKey);
        columnMeta.setDefaultValue(parseDefaultValue(specs));

        // 生成表达式在解析阶段预计算，模板只负责按字段顺序输出 SQL。
        columnMeta.setInsertValueExpression(buildInsertValueExpression(columnMeta));
        return columnMeta;
    }

    /**
     * 表约束解析：当前重点识别 PRIMARY KEY 约束，用于主键字段生成更稳定的假数据表达式。
     */
    private Set<String> parsePrimaryKeyNames(CreateTable createTable) {
        Set<String> primaryKeyNames = new HashSet<>();

        // 没有表级索引/约束时直接返回空集合，字段级 PRIMARY KEY 会在字段解析阶段处理。
        if (createTable.getIndexes() == null) {
            return primaryKeyNames;
        }

        for (Index index : createTable.getIndexes()) {
            // JSQLParser 将 PRIMARY KEY 约束放在 Index 结构中，这里只处理主键，其他索引暂不参与生成。
            if (index.getType() == null || !"PRIMARY KEY".equalsIgnoreCase(index.getType())) {
                continue;
            }

            // 当前版本先从约束文本中提取括号内字段，兼容 CONSTRAINT xxx PRIMARY KEY (id) 的形式。
            String indexText = index.toString();
            int start = indexText.indexOf('(');
            int end = indexText.lastIndexOf(')');
            if (start < 0 || end <= start) {
                continue;
            }

            // 逐个归一化主键字段名，确保后续可匹配带双引号或不带双引号的字段定义。
            String columnsText = indexText.substring(start + 1, end);
            for (String columnName : columnsText.split(",")) {
                primaryKeyNames.add(normalizeIdentifier(columnName.trim()));
            }
        }
        return primaryKeyNames;
    }

    /**
     * 类型长度解析：varchar(32) 这类长度信息由模板和后续 Java 类型映射共同复用。
     */
    private String parseLength(ColDataType colDataType) {
        // 无参数类型如 text/int8 没有长度，返回 null 让后续模板或类型映射自行判断。
        if (colDataType.getArgumentsStringList() == null || colDataType.getArgumentsStringList().isEmpty()) {
            return null;
        }

        // 多参数类型保留逗号拼接形式，例如 numeric(10,2)，避免丢失精度信息。
        return String.join(",", colDataType.getArgumentsStringList());
    }

    /**
     * 默认值解析：只读取 DEFAULT 后到下一个约束关键字前的内容，避免把 NOT NULL 等约束误当成默认值。
     */
    private String parseDefaultValue(List<String> specs) {
        // 字段没有附加约束时，不存在 DEFAULT 信息。
        if (specs == null || specs.isEmpty()) {
            return null;
        }

        // DEFAULT 表达式可能由多个 token 组成，所以进入 DEFAULT 后持续收集，直到遇到约束边界。
        List<String> defaultParts = new ArrayList<>();
        boolean readingDefault = false;
        for (String spec : specs) {
            if ("DEFAULT".equalsIgnoreCase(spec)) {
                readingDefault = true;
                continue;
            }
            if (!readingDefault) {
                continue;
            }

            // 遇到 NOT NULL、PRIMARY KEY 等约束关键字时，说明默认值表达式已经结束。
            if (isDefaultBoundary(spec)) {
                break;
            }
            defaultParts.add(spec);
        }

        // 没有收集到默认值时保持 null，后续生成逻辑会按字段类型生成假数据。
        if (defaultParts.isEmpty()) {
            return null;
        }
        return String.join(" ", defaultParts);
    }

    /**
     * 默认值边界判断：这些关键字代表 DEFAULT 表达式结束，后面进入字段约束语义。
     */
    private boolean isDefaultBoundary(String spec) {
        // 这些关键字属于字段约束语义，不应被拼入 DEFAULT 表达式。
        return "NOT".equalsIgnoreCase(spec)
                || "NULL".equalsIgnoreCase(spec)
                || "PRIMARY".equalsIgnoreCase(spec)
                || "UNIQUE".equalsIgnoreCase(spec)
                || "CONSTRAINT".equalsIgnoreCase(spec)
                || "CHECK".equalsIgnoreCase(spec)
                || "REFERENCES".equalsIgnoreCase(spec);
    }

    /**
     * 约束片段匹配：JSQLParser 将字段约束拆成字符串列表，这里用相邻关键字识别 NOT NULL、PRIMARY KEY 等组合。
     */
    private boolean containsSpecSequence(List<String> specs, String first, String second) {
        // 少于两个 token 时不可能匹配组合关键字，提前返回减少循环判断。
        if (specs == null || specs.size() < 2) {
            return false;
        }

        // 字段约束被拆成 token 列表，按相邻 token 判断组合关键字最直接。
        for (int i = 0; i < specs.size() - 1; i++) {
            if (first.equalsIgnoreCase(specs.get(i)) && second.equalsIgnoreCase(specs.get(i + 1))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 假数据表达式生成：根据字段类型、默认值和字段命名约定生成 PostgreSQL SELECT 表达式。
     */
    private String buildInsertValueExpression(ColumnMeta columnMeta) {
        // 如果建表 SQL 已声明默认值，优先沿用默认值，保持生成数据与表设计意图一致。
        if (columnMeta.getDefaultValue() != null && !columnMeta.getDefaultValue().isBlank()) {
            return columnMeta.getDefaultValue();
        }

        // 字段命名规则和数据库类型规则分别处理：命名用于识别时间戳语义，类型分类用于选择假数据表达式。
        String columnName = columnMeta.getColumnName().toLowerCase(Locale.ROOT);
        SqlTypeCategory typeCategory = postgresqlTypeClassifier.classify(columnMeta.getDataType());

        // 项目中 int8 时间字段常用毫秒时间戳存储，按命名约定生成当前时间附近的 bigint 值。
        if (isTimestampLikeColumn(columnName)) {
            return "(extract(epoch from now()) * 1000)::bigint + gs";
        }

        // 文本主键需要稳定且不重复，md5(gs::text) 简单满足 varchar(32) 主键场景。
        if (columnMeta.isPrimaryKey() && typeCategory == SqlTypeCategory.TEXT) {
            return "md5(gs::text)";
        }

        // 普通文本字段拼接字段名和序号，生成结果更容易人工识别来自哪个字段。
        if (typeCategory == SqlTypeCategory.TEXT) {
            return "'" + columnMeta.getColumnName() + "_' || gs::text";
        }

        // 整数字段直接使用 generate_series 的序号，保证每行数据不同且表达式简单。
        if (typeCategory == SqlTypeCategory.INTEGER) {
            return "gs";
        }

        // 布尔字段暂用固定 true，后续如需要可改为按 gs 奇偶生成 true/false。
        if (typeCategory == SqlTypeCategory.BOOLEAN) {
            return "true";
        }

        // 暂未覆盖的类型先显式输出 NULL，避免生成错误语义的假数据。
        return "NULL";
    }

    /**
     * 时间字段识别：当前按字段命名约定处理毫秒时间戳字段，覆盖 created_time、updated_time 等常见场景。
     */
    private boolean isTimestampLikeColumn(String columnName) {
        // 当前没有完整业务字段字典，因此先通过常见命名后缀识别时间类字段。
        return columnName.endsWith("_time") || columnName.endsWith("_date") || columnName.contains("timestamp");
    }

    /**
     * 完整表名构建：保留 schema.table 结构，确保生成 SQL 能定位到原始建表 SQL 指定的 schema。
     */
    private String buildFullTableName(String schemaName, String tableName) {
        // 未声明 schema 时只输出表名，避免强行补 public 改变用户 SQL 的原始意图。
        if (schemaName == null || schemaName.isBlank()) {
            return toSqlIdentifier(tableName, tableName);
        }

        // schema 和 table 分别还原标识符，保证任意一侧带双引号时都能正确输出。
        return toSqlIdentifier(schemaName, schemaName) + "." + toSqlIdentifier(tableName, tableName);
    }

    /**
     * SQL 标识符还原：原 SQL 中显式双引号包裹的字段必须保留双引号，避免 operator、valid 等名称生成后语义变化。
     */
    private String toSqlIdentifier(String rawIdentifier, String normalizedIdentifier) {
        // 原始 SQL 已经使用双引号时必须保留，否则 PostgreSQL 会按小写普通标识符解释。
        if (rawIdentifier != null && rawIdentifier.startsWith("\"") && rawIdentifier.endsWith("\"")) {
            return rawIdentifier;
        }

        // 普通标识符直接使用归一化名称，生成 SQL 更简洁。
        return normalizedIdentifier;
    }

    /**
     * 标识符归一化：内部比较主键和字段名时去掉双引号，降低大小写和引号形式带来的匹配复杂度。
     */
    private String normalizeIdentifier(String identifier) {
        // null 代表 SQL 中未声明对应部分，例如没有 schema。
        if (identifier == null) {
            return null;
        }

        // 比较和生成 Java 名称时不需要外层双引号，先统一去除。
        String trimmedIdentifier = identifier.trim();
        if (trimmedIdentifier.startsWith("\"") && trimmedIdentifier.endsWith("\"")) {
            return trimmedIdentifier.substring(1, trimmedIdentifier.length() - 1);
        }

        // 无双引号标识符保持原值，避免在解析层做额外大小写策略。
        return trimmedIdentifier;
    }
}
