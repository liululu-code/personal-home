package top.lll44556.codeGenerator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.lll44556.codeGenerator.enums.DatabaseType;
import top.lll44556.codeGenerator.enums.LocalGenerateContentType;
import top.lll44556.codeGenerator.service.LocalCodeGeneratorService;
import top.lll44556.codeGenerator.vo.codeGenerator.req.LocalEntityFieldReqVo;
import top.lll44556.codeGenerator.vo.codeGenerator.req.LocalGenerateReqVo;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = CodeGeneratorTestApplication.class)
class LocalCodeGeneratorServiceTest {

    private static final String CREATE_TABLE_SQL = """
            CREATE TABLE public.sys_user (
                id varchar(32) NOT NULL,
                cjsj int8 NULL,
                czz varchar(100) NULL,
                gxsj int8 NULL,
                yxx int4 DEFAULT 1 NOT NULL,
                c_nickname varchar(100) NULL,
                c_avatar_url text NULL,
                CONSTRAINT sys_user_pkey PRIMARY KEY (id)
            );
            COMMENT ON COLUMN public.sys_user.c_nickname IS '数据库昵称';
            """;

    @TempDir
    private Path tempDir;

    @Autowired
    private LocalCodeGeneratorService localCodeGeneratorService;

    @Test
    void generateLocalEntityReqVoResVoAndBeanWithEditableSchemaComment() throws Exception {
        LocalGenerateReqVo request = new LocalGenerateReqVo(
                DatabaseType.POSTGRESQL,
                CREATE_TABLE_SQL,
                "top.lll44556.demo.entity",
                "UserEntity",
                List.of(
                        LocalGenerateContentType.ENTITY,
                        LocalGenerateContentType.REQ_VO,
                        LocalGenerateContentType.RES_VO,
                        LocalGenerateContentType.BEAN,
                        LocalGenerateContentType.CONTROLLER,
                        LocalGenerateContentType.CONVERT,
                        LocalGenerateContentType.REPOSITORY,
                        LocalGenerateContentType.NATIVE_QUERY,
                        LocalGenerateContentType.NATIVE_QUERY_POSTGRESQL
                ),
                List.of(
                        new LocalEntityFieldReqVo("id", "String", "id", ""),
                        new LocalEntityFieldReqVo("cjsj", "Long", "createdTime", "cjsj注释"),
                        new LocalEntityFieldReqVo("czz", "String", "operator", ""),
                        new LocalEntityFieldReqVo("gxsj", "Long", "updatedTime", ""),
                        new LocalEntityFieldReqVo("yxx", "Integer", "valid", ""),
                        new LocalEntityFieldReqVo("c_nickname", "String", "nickname", "用户昵称"),
                        new LocalEntityFieldReqVo("c_avatar_url", "String", "avatarUrl", "  用户头像  ")
                ),
                tempDir.toString(),
                "",
                "",
                ""
        );

        localCodeGeneratorService.generate(request);

        String entityContent = readGeneratedJava("top.lll44556.demo.entity", "UserEntity");
        String reqVoContent = readGeneratedJava("top.lll44556.demo.vo.req", "UserSaveReqVO");
        String resVoContent = readGeneratedJava("top.lll44556.demo.vo.res", "UserResVO");
        String beanContent = readGeneratedJava("top.lll44556.demo.service.bean", "UserBean");
        String controllerContent = readGeneratedJava("top.lll44556.demo.controller", "UserController");
        String convertContent = readGeneratedJava("top.lll44556.demo.convert", "UserConvert");
        String repositoryContent = readGeneratedJava("top.lll44556.demo.repository", "UserRepository");
        String nativeQueryContent = readGeneratedJava("top.lll44556.demo.service.nativequery", "UserNativeQuery");
        String nativeQueryPostgreSQLContent = readGeneratedJava(
                "top.lll44556.demo.service.nativequery", "UserNativeQueryPostgreSQL");
        String today = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        // Entity 和 Bean 通过基类承载公共字段，生成模板不能重复声明这些通用成员。
        assertTrue(entityContent.contains("import common.lll44556.top.entity.BaseEntity;"));
        assertTrue(entityContent.contains("public class UserEntity extends BaseEntity"));
        assertTrue(beanContent.contains("import common.lll44556.top.bean.BaseBean;"));
        assertTrue(beanContent.contains("public class UserBean extends BaseBean"));
        assertTrue(!entityContent.contains("${base"));
        assertTrue(!beanContent.contains("${base"));
        assertNoCommonFieldDeclaration(entityContent);
        assertNoCommonFieldDeclaration(beanContent);
        assertNoCommonFieldDeclaration(reqVoContent);
        assertNoCommonFieldDeclaration(resVoContent);

        // 本地生成的 Java 文件需要统一带上文件头信息，便于落盘后追踪来源。
        assertGeneratedClassHeader(entityContent, "User", today);
        assertGeneratedClassHeader(beanContent, "User", today);
        assertGeneratedClassHeader(reqVoContent, "User", today);
        assertGeneratedClassHeader(resVoContent, "User", today);
        assertGeneratedClassHeader(controllerContent, "User 控制器", today);

        // Controller 响应包装配置为空时必须使用后端默认值，避免模板绑定到固定 R.ok。
        assertTrue(controllerContent.contains("import top.lll44556.common.util.Result;"));
        assertTrue(controllerContent.contains("public Result<UserResVO> save"));
        assertTrue(controllerContent.contains("return Result.success(userService.save(request));"));

        // Entity 不参与 OpenAPI 入出参描述，因此不能生成任何 Schema 注解。
        assertTrue(!entityContent.contains("io.swagger.v3.oas.annotations.media.Schema"));
        assertTrue(!entityContent.contains("@Schema"));

        // ReqVO、ResVO、Bean 需要类级 Schema，便于接口文档展示对象标题。
        assertTrue(reqVoContent.contains("public class UserSaveReqVO"));
        assertTrue(reqVoContent.contains("@Schema(title = \"UserSaveReqVO\")"));
        assertTrue(!reqVoContent.contains("UserReqVO"));
        assertTrue(resVoContent.contains("@Schema(title = \"UserResVO\")"));
        assertTrue(beanContent.contains("@Schema(title = \"UserBean\")"));

        // 用户在前端编辑后的字段注释必须成为接口/Bean 模板的字段文档和 OpenAPI 标题。
        assertGeneratedFieldComment(reqVoContent, "用户昵称");
        assertGeneratedFieldComment(resVoContent, "用户昵称");
        assertGeneratedFieldComment(beanContent, "用户昵称");

        // Entity 仍保留业务字段的 JavaDoc，但不生成字段级 Schema。
        assertTrue(entityContent.contains("* 用户昵称"));
        assertTrue(entityContent.contains("private String nickname;"));

        // 注释会在后端 trim 后再进入模板，避免生成带首尾空白的 Schema 标题。
        assertGeneratedFieldComment(beanContent, "用户头像");
        assertTrue(!beanContent.contains("@Schema(title = \"  用户头像  \")"));

        // Convert 使用本地生成的 SaveReqVO 命名，并要求列表转换方法统一以 List 作为后缀。
        assertGeneratedClassHeader(convertContent, "User 转换器", today);
        assertTrue(convertContent.contains("@Mapper(componentModel = \"spring\")"));
        assertTrue(convertContent.contains("UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);"));
        assertTrue(convertContent.contains("import top.lll44556.demo.vo.req.UserSaveReqVO;"));
        assertTrue(convertContent.contains("UserBean convertSaveReqVOToBean(UserSaveReqVO vo);"));
        assertTrue(convertContent.contains("List<UserBean> convertSaveReqVOToBeanList(List<UserSaveReqVO> voList);"));
        assertTrue(convertContent.contains("List<UserEntity> convertBeanToEntityList(List<UserBean> beanList);"));
        assertTrue(convertContent.contains("List<UserBean> convertEntityToBeanList(List<UserEntity> entityList);"));
        assertTrue(convertContent.contains("List<UserResVO> convertBeanToResVOList(List<UserBean> beanList);"));
        assertTrue(!convertContent.contains("convertListBeanToResVO"));
        assertTrue(!convertContent.contains("UserReqVO"));

        // PostgreSQL 特性的 nativeQuery SQL 统一放在 Repository，适配器只负责委托调用。
        assertTrue(repositoryContent.contains("@Query(value = \"select * from sys_user where yxx = 1\", nativeQuery = true)"));
        assertTrue(repositoryContent.contains("List<UserEntity> findUserConditionByNativeQuery();"));
        assertTrue(repositoryContent.contains("@Query(value = \"select count(1) from sys_user where yxx = 1\", nativeQuery = true)"));
        assertTrue(repositoryContent.contains("Integer countUserConditionByNativeQuery();"));
        assertTrue(nativeQueryContent.contains("public interface UserNativeQuery"));
        assertTrue(nativeQueryContent.contains("List<UserEntity> findUserCondition();"));
        assertTrue(nativeQueryContent.contains("Integer findUserTotal();"));
        assertTrue(nativeQueryPostgreSQLContent.contains("public class UserNativeQueryPostgreSQL implements UserNativeQuery"));
        assertTrue(nativeQueryPostgreSQLContent.contains("private final UserRepository userRepository;"));
        assertTrue(nativeQueryPostgreSQLContent.contains("return userRepository.findUserConditionByNativeQuery();"));
        assertTrue(nativeQueryPostgreSQLContent.contains("return userRepository.countUserConditionByNativeQuery();"));
        assertTrue(!nativeQueryPostgreSQLContent.contains("EntityManager"));
        assertTrue(!nativeQueryPostgreSQLContent.contains("createQuery"));
    }

    private String readGeneratedJava(String packageName, String className) throws Exception {
        Path javaFile = tempDir.resolve(Path.of(packageName.replace(".", "/"))).resolve(className + ".java");
        return Files.readString(javaFile, StandardCharsets.UTF_8);
    }

    private void assertNoCommonFieldDeclaration(String content) {
        assertTrue(!content.contains("private String id;"));
        assertTrue(!content.contains("private Long createdTime;"));
        assertTrue(!content.contains("private String operator;"));
        assertTrue(!content.contains("private Long updatedTime;"));
        assertTrue(!content.contains("private Integer valid;"));
    }

    private void assertGeneratedFieldComment(String content, String comment) {
        assertTrue(content.contains("* " + comment));
        assertTrue(content.contains("@Schema(title = \"" + comment + "\")"));
    }

    private void assertGeneratedClassHeader(String content, String descriptionName, String date) {
        assertTrue(content.contains("* @Description: " + descriptionName));
        assertTrue(content.contains("* @author: lll"));
        assertTrue(content.contains("* @date: " + date));
    }
}
