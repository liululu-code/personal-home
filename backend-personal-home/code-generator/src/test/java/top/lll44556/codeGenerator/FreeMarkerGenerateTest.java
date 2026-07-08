package top.lll44556.codeGenerator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest(classes = CodeGeneratorTestApplication.class)
public class FreeMarkerGenerateTest {
    @Autowired
    private Configuration freeMarkerConfiguration;

    @Test
    void generateHelloJavaFile() throws Exception {
        Template template = freeMarkerConfiguration.getTemplate("hello.java.ftl");

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("className", "Student");

        StringWriter writer = new StringWriter();
        template.process(dataModel, writer);

        String code = writer.toString();

        Path outputDir = Path.of("target", "generated-test-code");
        Files.createDirectories(outputDir);

        Path outputFile = outputDir.resolve("Student.java");
        Files.writeString(outputFile, code, StandardCharsets.UTF_8);

        System.out.println("生成成功: " + outputFile.toAbsolutePath());
    }
}
