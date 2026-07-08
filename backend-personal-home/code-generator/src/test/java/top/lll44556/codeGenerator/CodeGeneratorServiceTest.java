package top.lll44556.codeGenerator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.lll44556.codeGenerator.enums.DatabaseType;
import top.lll44556.codeGenerator.enums.InputType;
import top.lll44556.codeGenerator.enums.OutputType;
import top.lll44556.codeGenerator.service.CodeGeneratorService;
import top.lll44556.codeGenerator.vo.codeGenerator.req.GenerateReqVo;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = CodeGeneratorTestApplication.class)
class CodeGeneratorServiceTest {

    private static final String SYS_USER_CREATE_TABLE_SQL = """
            CREATE TABLE public.sys_user (
                id varchar(32) NOT NULL,
                created_time int8 NULL,
                updated_time int8 NULL,
                "operator" varchar(200) NULL,
                "valid" int4 DEFAULT 1 NOT NULL,
                c_nickname varchar(100) NULL,
                c_avatar_url text NULL,
                c_real_name varchar(100) NULL,
                c_status int4 DEFAULT 1 NOT NULL,
                c_last_login_time int8 NULL,
                CONSTRAINT sys_user_pkey PRIMARY KEY (id)
            );
            """;

    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Test
    void generatePostgresqlFakeDataSqlByCreateTableSql() {
        GenerateReqVo request = new GenerateReqVo(
                InputType.CREATE_TABLE_SQL,
                DatabaseType.POSTGRESQL,
                OutputType.FAKE_DATA_INSERT_SQL,
                SYS_USER_CREATE_TABLE_SQL
        );

        String outputContent = codeGeneratorService.generate(request);

        // 锁定 PostgreSQL 假数据 SQL 的核心结构，避免后续模板调整破坏生成链路。
        assertTrue(outputContent.contains("INSERT INTO public.sys_user"));
        assertTrue(outputContent.contains("\"operator\""));
        assertTrue(outputContent.contains("\"valid\""));
        assertTrue(outputContent.contains("generate_series(1, 10)"));
        assertTrue(outputContent.contains("md5(gs::text) AS id"));
        assertTrue(outputContent.contains("1 AS \"valid\""));
        assertTrue(outputContent.contains("1 AS c_status"));
    }
}
