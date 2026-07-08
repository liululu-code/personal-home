package top.lll44556.codeGenerator.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.lll44556.codeGenerator.enums.DatabaseType;
import top.lll44556.codeGenerator.enums.InputType;
import top.lll44556.codeGenerator.enums.OutputType;
import top.lll44556.codeGenerator.model.TableMeta;
import top.lll44556.codeGenerator.parser.CreateTableSqlParser;
import top.lll44556.codeGenerator.service.CodeGeneratorService;
import top.lll44556.codeGenerator.service.TemplateRenderService;
import top.lll44556.codeGenerator.vo.codeGenerator.req.GenerateReqVo;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

    private static final String POSTGRESQL_FAKE_DATA_TEMPLATE = "code-generator/postgresql-fake-data-insert.sql.ftl";

    private final TemplateRenderService templateRenderService;

    private final CreateTableSqlParser createTableSqlParser;

    @Override
    public String generate(GenerateReqVo request) {
        checkSupportedRequest(request);

        if (request.getOutputType() != OutputType.FAKE_DATA_INSERT_SQL) {
            throw new IllegalArgumentException("当前仅实现数据库假数据生成 SQL");
        }

        // 解析输入
        TableMeta tableMeta = createTableSqlParser.parse(request.getInputContent());

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("inputContent", request.getInputContent());
        dataModel.put("inputType", request.getInputType().name());
        dataModel.put("databaseType", request.getDatabaseType().name());
        dataModel.put("outputType", request.getOutputType().name());
        dataModel.put("tableMeta", tableMeta);
        dataModel.put("rowCount", 10);

        // 使用统一表元数据驱动模板，后续 bean、projection、VO 等输出类型可复用解析结果。
        return templateRenderService.render(POSTGRESQL_FAKE_DATA_TEMPLATE, dataModel);
    }

    private void checkSupportedRequest(GenerateReqVo request) {
        if (request == null) {
            throw new IllegalArgumentException("生成请求不能为空");
        }
        if (request.getInputType() != InputType.CREATE_TABLE_SQL
                || request.getDatabaseType() != DatabaseType.POSTGRESQL
                || request.getOutputType() == null) {
            throw new IllegalArgumentException("当前仅支持建表 SQL + PostgreSQL 的代码生成骨架");
        }
        if (request.getInputContent() == null || request.getInputContent().isBlank()) {
            throw new IllegalArgumentException("输入内容不能为空");
        }
    }
}
