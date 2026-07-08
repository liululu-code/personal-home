package top.lll44556.codeGenerator.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.lll44556.codeGenerator.enums.DatabaseType;
import top.lll44556.codeGenerator.enums.LocalGenerateContentType;
import top.lll44556.codeGenerator.enums.SqlTypeCategory;
import top.lll44556.codeGenerator.model.ColumnMeta;
import top.lll44556.codeGenerator.model.TableMeta;
import top.lll44556.codeGenerator.parser.CreateTableSqlParser;
import top.lll44556.codeGenerator.parser.PostgresqlTypeClassifier;
import top.lll44556.codeGenerator.service.LocalCodeGeneratorService;
import top.lll44556.codeGenerator.service.TemplateRenderService;
import top.lll44556.codeGenerator.vo.codeGenerator.req.LocalEntityFieldReqVo;
import top.lll44556.codeGenerator.vo.codeGenerator.req.LocalGenerateReqVo;
import top.lll44556.codeGenerator.vo.codeGenerator.req.LocalParseTableReqVo;
import top.lll44556.codeGenerator.vo.codeGenerator.res.LocalColumnResVo;
import top.lll44556.codeGenerator.vo.codeGenerator.res.LocalGenerateResVo;
import top.lll44556.codeGenerator.vo.codeGenerator.res.LocalParseTableResVo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@AllArgsConstructor
public class LocalCodeGeneratorServiceImpl implements LocalCodeGeneratorService {

    private static final String LOCAL_ENTITY_TEMPLATE = "code-generator/local-entity.java.ftl";

    private static final String LOCAL_BEAN_TEMPLATE = "code-generator/local-bean.java.ftl";

    private static final String LOCAL_REPOSITORY_TEMPLATE = "code-generator/local-repository.java.ftl";

    private static final String LOCAL_NATIVE_QUERY_TEMPLATE = "code-generator/local-native-query.java.ftl";

    private static final String LOCAL_NATIVE_QUERY_POSTGRESQL_TEMPLATE = "code-generator/local-native-query-postgresql.java.ftl";

    private static final String LOCAL_SERVICE_TEMPLATE = "code-generator/local-service.java.ftl";

    private static final String LOCAL_SERVICE_IMPL_TEMPLATE = "code-generator/local-service-impl.java.ftl";

    private static final String LOCAL_CONTROLLER_TEMPLATE = "code-generator/local-controller.java.ftl";

    private static final String LOCAL_CONVERT_TEMPLATE = "code-generator/local-convert.java.ftl";

    private static final String LOCAL_REQ_VO_TEMPLATE = "code-generator/local-req-vo.java.ftl";

    private static final String LOCAL_LIST_REQ_VO_TEMPLATE = "code-generator/local-list-req-vo.java.ftl";

    private static final String LOCAL_RES_VO_TEMPLATE = "code-generator/local-res-vo.java.ftl";

    private static final String LOCAL_PAGE_RESULT_TEMPLATE = "code-generator/local-page-result.java.ftl";

    private static final String LOCAL_PAGINATION_TEMPLATE = "code-generator/local-pagination.java.ftl";

    private static final String LOCAL_PAGINATION_REQ_VO_TEMPLATE = "code-generator/local-pagination-req-vo.java.ftl";

    private static final String LOCAL_PAGE_UTIL_TEMPLATE = "code-generator/local-page-util.java.ftl";

    private static final String DEFAULT_ENTITY_FIELD_COMMENT = "默认注释";

    private static final String DEFAULT_RESPONSE_CLASS_PACKAGE_NAME = "top.lll44556.common.util";

    private static final String DEFAULT_RESPONSE_CLASS_NAME = "Result";

    private static final String DEFAULT_RESPONSE_SUCCESS_METHOD_NAME = "success";

    private static final String DEFAULT_PAGE_PACKAGE_NAME = "common.lll44556.top.page";

    private static final String DEFAULT_PAGE_UTIL_PACKAGE_NAME = "common.lll44556.top.util";

    private static final Set<String> COMMON_FIELD_COLUMN_NAMES = Set.of("id", "cjsj", "gxsj", "czz", "yxx");

    private static final Set<String> COMMON_FIELD_ENTITY_NAMES = Set.of("id", "createdtime", "updatedtime", "operator", "valid");

    private static final String BACKEND_ROOT_DIR_NAME = "backend-personal-home";

    private static final String OFFLINE_FILE_DIR_NAME = "file";

    private static final DateTimeFormatter OFFLINE_TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    private final CreateTableSqlParser createTableSqlParser;

    private final PostgresqlTypeClassifier postgresqlTypeClassifier;

    private final TemplateRenderService templateRenderService;

    @Override
    public LocalParseTableResVo parseTable(LocalParseTableReqVo request) {
        checkParseRequest(request);

        // 当前先复用已有建表 SQL 解析器，保证页面能够拿到稳定字段结构；后续再补充注释、Java 类型映射等生成专用元数据。
        TableMeta tableMeta = createTableSqlParser.parse(request.getCreateTableSql());
        List<LocalColumnResVo> columns = tableMeta.getColumns()
                .stream()
                .map(this::toLocalColumnResVo)
                .toList();

        return new LocalParseTableResVo(
                tableMeta.getSchemaName(),
                tableMeta.getTableName(),
                tableMeta.getFullTableName(),
                columns
        );
    }

    @Override
    public LocalGenerateResVo generate(LocalGenerateReqVo request) {
        checkGenerateRequest(request);

        // 生成阶段仍重新解析 SQL，只用于校验字段来源和获取表信息；用户编辑后的 Entity 字段信息不能被解析结果覆盖。
        TableMeta tableMeta = createTableSqlParser.parse(request.getCreateTableSql());
        checkEntityFieldsMatchTable(request.getEntityFields(), tableMeta);

        Map<String, Object> dataModel = buildLocalGenerateDataModel(request, tableMeta);
        String baseClassName = dataModel.get("baseClassName").toString();
        String timestamp = LocalDateTime.now().format(OFFLINE_TIMESTAMP_FORMATTER);
        Path generatedDirectory = createUniqueGeneratedDirectory(baseClassName, timestamp);
        List<String> generatedFiles = new ArrayList<>();
        for (LocalGenerateContentType contentType : request.getContentTypes()) {
            for (String generatedFile : generateFiles(generatedDirectory, contentType, dataModel)) {
                generatedFiles.add(contentType.name() + "：已生成 " + generatedFile);
            }
        }

        Path zipFilePath = zipGeneratedDirectory(generatedDirectory, generatedDirectory.getFileName() + ".zip");
        return new LocalGenerateResVo(
                generatedDirectory.toString(),
                zipFilePath.toString(),
                zipFilePath.getFileName().toString(),
                generatedFiles,
                "离线代码生成完成"
        );
    }

    private LocalColumnResVo toLocalColumnResVo(ColumnMeta columnMeta) {
        return new LocalColumnResVo(
                columnMeta.getColumnName(),
                columnMeta.getQuotedColumnName(),
                columnMeta.getDataType(),
                columnMeta.getLength(),
                columnMeta.isNullable(),
                columnMeta.isPrimaryKey(),
                columnMeta.getDefaultValue(),
                columnMeta.getComment(),
                inferEntityType(columnMeta),
                toLowerCamelCase(columnMeta.getColumnName())
        );
    }

    private void checkParseRequest(LocalParseTableReqVo request) {
        if (request == null) {
            throw new IllegalArgumentException("本地解析请求不能为空");
        }
        if (request.getDatabaseType() != DatabaseType.POSTGRESQL) {
            throw new IllegalArgumentException("当前离线生成框架仅支持 PostgreSQL 建表 SQL");
        }
        if (request.getCreateTableSql() == null || request.getCreateTableSql().isBlank()) {
            throw new IllegalArgumentException("建表 SQL 不能为空");
        }
    }

    private void checkGenerateRequest(LocalGenerateReqVo request) {
        checkParseRequest(new LocalParseTableReqVo(request == null ? null : request.getDatabaseType(),
                request == null ? null : request.getCreateTableSql()));

        if (request.getPackageName() == null || request.getPackageName().isBlank()) {
            throw new IllegalArgumentException("Entity 包名不能为空");
        }
        if (request.getEntityClassName() == null || request.getEntityClassName().isBlank()) {
            throw new IllegalArgumentException("Entity 类名不能为空");
        }
        if (request.getContentTypes() == null || request.getContentTypes().isEmpty()) {
            throw new IllegalArgumentException("请选择至少一种离线生成内容");
        }
        if (request.getEntityFields() == null || request.getEntityFields().isEmpty()) {
            throw new IllegalArgumentException("请先解读字段，并确认 Entity 成员信息");
        }
        for (LocalEntityFieldReqVo entityField : request.getEntityFields()) {
            if (entityField == null) {
                throw new IllegalArgumentException("Entity 字段不能为空");
            }
            if (entityField.getColumnName() == null || entityField.getColumnName().isBlank()) {
                throw new IllegalArgumentException("Entity 字段必须关联数据库列名");
            }
            if (entityField.getEntityType() == null || entityField.getEntityType().isBlank()) {
                throw new IllegalArgumentException("Entity 成员类型不能为空");
            }
            if (entityField.getEntityName() == null || entityField.getEntityName().isBlank()) {
                throw new IllegalArgumentException("Entity 成员名称不能为空");
            }
            if (entityField.getEntityComment() == null || entityField.getEntityComment().isBlank()) {
                entityField.setEntityComment(DEFAULT_ENTITY_FIELD_COMMENT);
            }
        }

        // 复制一份内容类型列表，避免后续实现真实生成时被外部可变集合影响。
        request.setContentTypes(new ArrayList<LocalGenerateContentType>(request.getContentTypes()));
        request.setEntityFields(new ArrayList<LocalEntityFieldReqVo>(request.getEntityFields()));
    }

    private Map<String, Object> buildLocalGenerateDataModel(LocalGenerateReqVo request, TableMeta tableMeta) {
        String entityPackageName = request.getPackageName().trim();
        String entityClassName = request.getEntityClassName().trim();
        String basePackageName = resolveBasePackageName(entityPackageName);
        String baseClassName = resolveBaseClassName(entityClassName);
        String lowerBaseClassName = toLowerFirst(baseClassName);

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("author", "lll");
        dataModel.put("date", LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        dataModel.put("descriptionName", baseClassName);

        dataModel.put("baseEntityPackageName", "common.lll44556.top.entity");
        dataModel.put("baseBeanPackageName", "common.lll44556.top.bean");

        dataModel.put("packageName", entityPackageName);
        dataModel.put("entityPackageName", entityPackageName);
        dataModel.put("entityClassName", entityClassName);
        dataModel.put("basePackageName", basePackageName);
        dataModel.put("baseClassName", baseClassName);
        dataModel.put("lowerBaseClassName", lowerBaseClassName);
        dataModel.put("tableName", tableMeta.getTableName());
        List<LocalEntityFieldReqVo> normalizedEntityFields = normalizeEntityFieldComments(request.getEntityFields());
        dataModel.put("entityFields", normalizedEntityFields);
        dataModel.put("businessFields", filterBusinessFields(normalizedEntityFields));

        dataModel.put("repositoryPackageName", basePackageName + ".repository");
        dataModel.put("repositoryClassName", baseClassName + "Repository");
        dataModel.put("repositoryFieldName", lowerBaseClassName + "Repository");
        dataModel.put("nativeQueryPackageName", basePackageName + ".service.nativequery");
        dataModel.put("nativeQueryPostgreSQLPackageName", basePackageName + ".service.nativequery.postgresql");
        dataModel.put("nativeQueryClassName", baseClassName + "NativeQuery");
        dataModel.put("nativeQueryPostgreSQLClassName", baseClassName + "NativeQueryPostgreSQL");

        dataModel.put("beanPackageName", basePackageName + ".service.bean");
        dataModel.put("beanClassName", baseClassName + "Bean");

        dataModel.put("servicePackageName", basePackageName + ".service");
        dataModel.put("serviceClassName", baseClassName + "Service");
        dataModel.put("serviceFieldName", lowerBaseClassName + "Service");
        dataModel.put("serviceImplPackageName", basePackageName + ".service.impl");
        dataModel.put("serviceImplClassName", baseClassName + "ServiceImpl");

        dataModel.put("controllerPackageName", basePackageName + ".controller");
        dataModel.put("controllerClassName", baseClassName + "Controller");
        dataModel.put("responseClassPackageName", resolveTemplateValue(
                request.getResponseClassPackageName(), DEFAULT_RESPONSE_CLASS_PACKAGE_NAME));
        dataModel.put("responseClassName", resolveTemplateValue(
                request.getResponseClassName(), DEFAULT_RESPONSE_CLASS_NAME));
        dataModel.put("responseSuccessMethodName", resolveTemplateValue(
                request.getResponseSuccessMethodName(), DEFAULT_RESPONSE_SUCCESS_METHOD_NAME));
        dataModel.put("pageResultPackageName", resolveTemplateValue(
                request.getPageResultPackageName(), DEFAULT_PAGE_PACKAGE_NAME));
        dataModel.put("paginationPackageName", resolveTemplateValue(
                request.getPaginationPackageName(), DEFAULT_PAGE_PACKAGE_NAME));
        dataModel.put("paginationReqVoPackageName", resolveTemplateValue(
                request.getPaginationReqVoPackageName(), DEFAULT_PAGE_PACKAGE_NAME));
        dataModel.put("pageUtilPackageName", resolveTemplateValue(
                request.getPageUtilPackageName(), DEFAULT_PAGE_UTIL_PACKAGE_NAME));
        dataModel.put("pageResultClassName", "PageResult");
        dataModel.put("paginationClassName", "Pagination");
        dataModel.put("paginationReqVoClassName", "PaginationReqVO");
        dataModel.put("pageUtilClassName", "PageUtil");
        dataModel.put("convertPackageName", basePackageName + ".convert");
        dataModel.put("convertClassName", baseClassName + "Convert");

        dataModel.put("reqVoPackageName", basePackageName + ".vo.req");
        dataModel.put("reqVoClassName", baseClassName + "SaveReqVO");
        dataModel.put("listReqVoClassName", baseClassName + "ListReqVO");

        dataModel.put("resVoPackageName", basePackageName + ".vo.res");
        dataModel.put("resVoClassName", baseClassName + "ResVO");
        return dataModel;
    }

    private List<String> generateFiles(Path generatedDirectory,
                                       LocalGenerateContentType contentType,
                                       Map<String, Object> dataModel) {
        if (contentType != LocalGenerateContentType.PAGE_SUPPORT) {
            return List.of(generateFile(generatedDirectory, contentType, dataModel));
        }

        return List.of(
                generateFile(generatedDirectory, LOCAL_PAGE_RESULT_TEMPLATE,
                        dataModel.get("pageResultPackageName").toString(),
                        dataModel.get("pageResultClassName").toString(),
                        dataModel),
                generateFile(generatedDirectory, LOCAL_PAGINATION_TEMPLATE,
                        dataModel.get("paginationPackageName").toString(),
                        dataModel.get("paginationClassName").toString(),
                        dataModel),
                generateFile(generatedDirectory, LOCAL_PAGINATION_REQ_VO_TEMPLATE,
                        dataModel.get("paginationReqVoPackageName").toString(),
                        dataModel.get("paginationReqVoClassName").toString(),
                        dataModel),
                generateFile(generatedDirectory, LOCAL_PAGE_UTIL_TEMPLATE,
                        dataModel.get("pageUtilPackageName").toString(),
                        dataModel.get("pageUtilClassName").toString(),
                        dataModel)
        );
    }

    private String generateFile(Path generatedDirectory,
                                LocalGenerateContentType contentType,
                                Map<String, Object> dataModel) {
        String targetPackageName = resolveTargetPackageName(contentType, dataModel);
        String targetClassName = resolveTargetClassName(contentType, dataModel);
        String templateName = resolveTemplateName(contentType);
        return generateFile(generatedDirectory, templateName, targetPackageName, targetClassName, dataModel);
    }

    private String generateFile(Path generatedDirectory,
                                String templateName,
                                String targetPackageName,
                                String targetClassName,
                                Map<String, Object> dataModel) {
        String fileContent = templateRenderService.render(templateName, dataModel);
        Path filePath = buildJavaFilePath(generatedDirectory, targetPackageName, targetClassName);

        try {
            Files.createDirectories(filePath.getParent());
            // 每次离线生成都落在独立目录中，单次目录内同名文件可直接写入，避免用户侧路径输入导致覆盖风险。
            Files.writeString(filePath, fileContent, StandardCharsets.UTF_8);
            return filePath.toString();
        } catch (IOException e) {
            throw new IllegalStateException("离线代码文件写入失败: " + filePath, e);
        }
    }

    private List<LocalEntityFieldReqVo> normalizeEntityFieldComments(List<LocalEntityFieldReqVo> entityFields) {
        for (LocalEntityFieldReqVo entityField : entityFields) {
            if (entityField.getEntityComment() == null || entityField.getEntityComment().isBlank()) {
                entityField.setEntityComment(DEFAULT_ENTITY_FIELD_COMMENT);
            } else {
                entityField.setEntityComment(entityField.getEntityComment().trim());
            }
        }
        return entityFields;
    }

    private List<LocalEntityFieldReqVo> filterBusinessFields(List<LocalEntityFieldReqVo> entityFields) {
        // 公共字段由 BaseEntity/BaseBean 承载，模板只遍历业务字段，避免生成重复成员或重复接口字段。
        return entityFields.stream()
                .filter(entityField -> !isCommonField(entityField))
                .toList();
    }

    private boolean isCommonField(LocalEntityFieldReqVo entityField) {
        String columnName = normalizeName(entityField.getColumnName());
        String entityName = normalizeName(entityField.getEntityName());
        return COMMON_FIELD_COLUMN_NAMES.contains(columnName) || COMMON_FIELD_ENTITY_NAMES.contains(entityName);
    }

    private String normalizeName(String name) {
        if (name == null) {
            return "";
        }
        return name.trim().replace("_", "").toLowerCase(Locale.ROOT);
    }

    private String resolveTemplateValue(String value, String defaultValue) {
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return value.trim();
    }

    private String resolveTargetPackageName(LocalGenerateContentType contentType, Map<String, Object> dataModel) {
        return switch (contentType) {
            case ENTITY -> dataModel.get("entityPackageName").toString();
            case BEAN -> dataModel.get("beanPackageName").toString();
            case REPOSITORY -> dataModel.get("repositoryPackageName").toString();
            case NATIVE_QUERY -> dataModel.get("nativeQueryPackageName").toString();
            case NATIVE_QUERY_POSTGRESQL -> dataModel.get("nativeQueryPostgreSQLPackageName").toString();
            case SERVICE -> dataModel.get("servicePackageName").toString();
            case SERVICE_IMPL -> dataModel.get("serviceImplPackageName").toString();
            case CONTROLLER -> dataModel.get("controllerPackageName").toString();
            case CONVERT -> dataModel.get("convertPackageName").toString();
            case REQ_VO -> dataModel.get("reqVoPackageName").toString();
            case LIST_REQ_VO -> dataModel.get("reqVoPackageName").toString();
            case RES_VO -> dataModel.get("resVoPackageName").toString();
            case PAGE_SUPPORT -> dataModel.get("pageResultPackageName").toString();
        };
    }

    private String resolveTargetClassName(LocalGenerateContentType contentType, Map<String, Object> dataModel) {
        return switch (contentType) {
            case ENTITY -> dataModel.get("entityClassName").toString();
            case BEAN -> dataModel.get("beanClassName").toString();
            case REPOSITORY -> dataModel.get("repositoryClassName").toString();
            case NATIVE_QUERY -> dataModel.get("nativeQueryClassName").toString();
            case NATIVE_QUERY_POSTGRESQL -> dataModel.get("nativeQueryPostgreSQLClassName").toString();
            case SERVICE -> dataModel.get("serviceClassName").toString();
            case SERVICE_IMPL -> dataModel.get("serviceImplClassName").toString();
            case CONTROLLER -> dataModel.get("controllerClassName").toString();
            case CONVERT -> dataModel.get("convertClassName").toString();
            case REQ_VO -> dataModel.get("reqVoClassName").toString();
            case LIST_REQ_VO -> dataModel.get("listReqVoClassName").toString();
            case RES_VO -> dataModel.get("resVoClassName").toString();
            case PAGE_SUPPORT -> dataModel.get("pageResultClassName").toString();
        };
    }

    private String resolveTemplateName(LocalGenerateContentType contentType) {
        return switch (contentType) {
            case ENTITY -> LOCAL_ENTITY_TEMPLATE;
            case BEAN -> LOCAL_BEAN_TEMPLATE;
            case REPOSITORY -> LOCAL_REPOSITORY_TEMPLATE;
            case NATIVE_QUERY -> LOCAL_NATIVE_QUERY_TEMPLATE;
            case NATIVE_QUERY_POSTGRESQL -> LOCAL_NATIVE_QUERY_POSTGRESQL_TEMPLATE;
            case SERVICE -> LOCAL_SERVICE_TEMPLATE;
            case SERVICE_IMPL -> LOCAL_SERVICE_IMPL_TEMPLATE;
            case CONTROLLER -> LOCAL_CONTROLLER_TEMPLATE;
            case CONVERT -> LOCAL_CONVERT_TEMPLATE;
            case REQ_VO -> LOCAL_REQ_VO_TEMPLATE;
            case LIST_REQ_VO -> LOCAL_LIST_REQ_VO_TEMPLATE;
            case RES_VO -> LOCAL_RES_VO_TEMPLATE;
            case PAGE_SUPPORT -> LOCAL_PAGE_RESULT_TEMPLATE;
        };
    }

    private Path buildJavaFilePath(Path generatedDirectory, String packageName, String className) {
        Path packagePath = Path.of(packageName.trim().replace(".", "/"));
        return generatedDirectory.resolve(packagePath).resolve(className.trim() + ".java");
    }

    private Path createUniqueGeneratedDirectory(String baseClassName, String timestamp) {
        Path fileDirectory = resolveBackendRootDirectory().resolve(OFFLINE_FILE_DIR_NAME);
        String baseDirectoryName = baseClassName + "_" + timestamp;

        try {
            Files.createDirectories(fileDirectory);
            for (int index = 0; ; index++) {
                String directoryName = index == 0 ? baseDirectoryName : baseDirectoryName + "_" + index;
                Path generatedDirectory = fileDirectory.resolve(directoryName);
                try {
                    // 用 createDirectory 原子创建目录，避免并发请求拿到同一个生成目录。
                    return Files.createDirectory(generatedDirectory);
                } catch (java.nio.file.FileAlreadyExistsException ignored) {
                    // 时间戳极端冲突时递增后缀继续尝试，保证每次生成结果独立保存。
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("离线生成目录创建失败: " + fileDirectory, e);
        }
    }

    private Path resolveBackendRootDirectory() {
        Path currentPath = Path.of(System.getProperty("user.dir")).toAbsolutePath().normalize();
        Path childBackendRoot = currentPath.resolve(BACKEND_ROOT_DIR_NAME);
        if (Files.isDirectory(childBackendRoot) && isBackendRootDirectory(childBackendRoot)) {
            return childBackendRoot;
        }

        Path candidate = currentPath;
        while (candidate != null) {
            if (isBackendRootDirectory(candidate)) {
                return candidate;
            }
            candidate = candidate.getParent();
        }
        throw new IllegalStateException("未找到后端项目根目录: " + currentPath);
    }

    private boolean isBackendRootDirectory(Path directory) {
        if (directory.getFileName() != null && BACKEND_ROOT_DIR_NAME.equals(directory.getFileName().toString())) {
            return true;
        }

        Path pomPath = directory.resolve("pom.xml");
        if (!Files.isRegularFile(pomPath)) {
            return false;
        }
        try {
            String pomContent = Files.readString(pomPath, StandardCharsets.UTF_8);
            return pomContent.contains("<artifactId>backend-personal-home</artifactId>");
        } catch (IOException e) {
            return false;
        }
    }

    private Path zipGeneratedDirectory(Path generatedDirectory, String zipFileName) {
        Path zipFilePath = generatedDirectory.getParent().resolve(zipFileName);
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFilePath), StandardCharsets.UTF_8);
             Stream<Path> generatedPaths = Files.walk(generatedDirectory)) {
            for (Path path : generatedPaths.filter(Files::isRegularFile).toList()) {
                Path relativePath = generatedDirectory.relativize(path);
                ZipEntry zipEntry = new ZipEntry(relativePath.toString().replace("\\", "/"));
                zipOutputStream.putNextEntry(zipEntry);
                Files.copy(path, zipOutputStream);
                zipOutputStream.closeEntry();
            }
            return zipFilePath;
        } catch (IOException e) {
            throw new IllegalStateException("离线生成 zip 文件创建失败: " + zipFilePath, e);
        }
    }

    private String resolveBasePackageName(String entityPackageName) {
        if (entityPackageName.endsWith(".entity")) {
            return entityPackageName.substring(0, entityPackageName.length() - ".entity".length());
        }
        return entityPackageName;
    }

    private String resolveBaseClassName(String entityClassName) {
        if (entityClassName.endsWith("Entity")) {
            return entityClassName.substring(0, entityClassName.length() - "Entity".length());
        }
        return entityClassName;
    }

    private String toLowerFirst(String className) {
        if (className == null || className.isBlank()) {
            return className;
        }
        return className.substring(0, 1).toLowerCase(Locale.ROOT) + className.substring(1);
    }

    private void checkEntityFieldsMatchTable(List<LocalEntityFieldReqVo> entityFields, TableMeta tableMeta) {
        Set<String> parsedColumnNames = new HashSet<>();
        for (ColumnMeta columnMeta : tableMeta.getColumns()) {
            parsedColumnNames.add(columnMeta.getColumnName());
        }

        // Entity 字段允许用户编辑类型和名称，但必须保持和建表 SQL 的字段来源一致，避免后续生成出脱离表结构的代码。
        for (LocalEntityFieldReqVo entityField : entityFields) {
            if (!parsedColumnNames.contains(entityField.getColumnName())) {
                throw new IllegalArgumentException("Entity 字段未在建表 SQL 中找到：" + entityField.getColumnName());
            }
        }
    }

    private String inferEntityType(ColumnMeta columnMeta) {
        SqlTypeCategory typeCategory = postgresqlTypeClassifier.classify(columnMeta.getDataType());
        if (typeCategory == SqlTypeCategory.TEXT) {
            return "String";
        }
        if (typeCategory == SqlTypeCategory.INTEGER) {
            return "Long";
        }
        if (typeCategory == SqlTypeCategory.BOOLEAN) {
            return "Boolean";
        }

        // TODO 后续补充时间、数值精度、JSON、数组等 PostgreSQL 类型到 Java 类型的完整映射。
        return "String";
    }

    private String toLowerCamelCase(String columnName) {
        if (columnName == null || columnName.isBlank()) {
            return columnName;
        }

        // 当前仅处理 snake_case 到 lowerCamelCase，复杂命名和缩写规则后续在统一命名策略中补充。
        String[] parts = columnName.toLowerCase(Locale.ROOT).split("_");
        StringBuilder entityName = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            if (parts[i].isBlank()) {
                continue;
            }
            entityName.append(parts[i].substring(0, 1).toUpperCase(Locale.ROOT))
                    .append(parts[i].substring(1));
        }
        return entityName.toString();
    }
}
