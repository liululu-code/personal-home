package top.lll44556.codeGenerator.parser;

import org.springframework.stereotype.Component;
import top.lll44556.codeGenerator.enums.SqlTypeCategory;

import java.util.Locale;
import java.util.Set;

@Component
public class PostgresqlTypeClassifier {

    private static final Set<String> TEXT_TYPES = Set.of(
            "varchar",
            "character varying",
            "char",
            "character",
            "text"
    );

    private static final Set<String> INTEGER_TYPES = Set.of(
            "int",
            "int2",
            "int4",
            "int8",
            "integer",
            "smallint",
            "bigint"
    );

    private static final Set<String> BOOLEAN_TYPES = Set.of(
            "bool",
            "boolean"
    );

    /**
     * 将 PostgreSQL 类型名归类为生成器可理解的粗粒度类型。
     * JSQLParser 只提供原始类型字符串，具体数据库语义需要在生成器侧集中维护。
     */
    public SqlTypeCategory classify(String dataType) {
        if (dataType == null || dataType.isBlank()) {
            return SqlTypeCategory.UNKNOWN;
        }

        // 统一大小写、首尾空白和长度参数，避免 varchar(32) / varchar (32) 这类写法影响归类。
        String normalizedDataType = normalizeDataType(dataType);
        if (TEXT_TYPES.contains(normalizedDataType)) {
            return SqlTypeCategory.TEXT;
        }
        if (INTEGER_TYPES.contains(normalizedDataType)) {
            return SqlTypeCategory.INTEGER;
        }
        if (BOOLEAN_TYPES.contains(normalizedDataType)) {
            return SqlTypeCategory.BOOLEAN;
        }
        return SqlTypeCategory.UNKNOWN;
    }

    private String normalizeDataType(String dataType) {
        String normalizedDataType = dataType.trim().toLowerCase(Locale.ROOT);
        int argumentStartIndex = normalizedDataType.indexOf('(');
        if (argumentStartIndex < 0) {
            return normalizedDataType;
        }

        // JSQLParser 可能把 varchar(32) 保存在 dataType 中，这里只保留类型主体用于语义分类。
        return normalizedDataType.substring(0, argumentStartIndex).trim();
    }
}
