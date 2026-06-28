package top.lll44556.codeGenerator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.lll44556.codeGenerator.vo.codeGenerator.req.GenerateReqVo;
import top.lll44556.codeGenerator.enums.DatabaseType;
import top.lll44556.codeGenerator.enums.InputType;
import top.lll44556.codeGenerator.enums.OutputType;
import top.lll44556.codeGenerator.service.CodeGeneratorService;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = CodeGeneratorTestApplication.class)
class CodeGeneratorServiceTest {

    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Test
    void generatePostgresqlFakeDataPlaceholderSql() {
        GenerateReqVo request = new GenerateReqVo(
                InputType.CREATE_TABLE_SQL,
                DatabaseType.POSTGRESQL,
                OutputType.FAKE_DATA_INSERT_SQL,
                "CREATE TABLE public.sys_user (id varchar(32) NOT NULL);"
        );

        String outputContent = codeGeneratorService.generate(request);

        // 当前测试锁定生成链路和模板占位，真实 SQL 解析逻辑后续再补充。
        assertTrue(outputContent.contains("generate_series(1, 10)"));
        assertTrue(outputContent.contains("CREATE TABLE public.sys_user"));
    }
}
