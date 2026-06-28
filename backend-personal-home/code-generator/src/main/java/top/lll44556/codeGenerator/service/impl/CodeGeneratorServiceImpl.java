package top.lll44556.codeGenerator.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.lll44556.codeGenerator.enums.DatabaseType;
import top.lll44556.codeGenerator.enums.InputType;
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

    @Override
    public String generate(GenerateReqVo request) {
        checkSupportedRequest(request);

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("inputContent", request.getInputContent());
        dataModel.put("inputType", request.getInputType().name());
        dataModel.put("databaseType", request.getDatabaseType().name());
        dataModel.put("outputType", request.getOutputType().name());

        // 当前只搭建生成链路，后续在这里接入 SQL 解析结果、字段元数据和假数据规则。
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
