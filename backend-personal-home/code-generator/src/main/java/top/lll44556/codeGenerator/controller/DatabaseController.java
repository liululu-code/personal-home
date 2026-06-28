package top.lll44556.codeGenerator.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/database")
public class DatabaseController {

    private final Configuration freemarkerConfig;

    /**
     * TODO: 旧测试接口仅用于验证 FreeMarker 可用，后续统一迁移到 /api/code-generator/generate。
     */
    @GetMapping("test")
    public void test() throws IOException, TemplateException {
        // TODO: 后续删除文件落盘行为，避免测试接口在项目根目录生成临时文件。
        Template template = freemarkerConfig.getTemplate("hello.java.ftl");

        Map<String, Object> data = new HashMap<>();
        data.put("className", "Hello");

        template.process(data, new FileWriter(new File("t.java")));
    }
}
