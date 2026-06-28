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
     * test方法
     */
    @GetMapping("test")
    public void test() throws IOException, TemplateException {
        // 模板
        Template template = freemarkerConfig.getTemplate("hello.java.ftl");

        // 数据
        Map<String, Object> data = new HashMap<>();
        data.put("className", "Hello");

        // 渲染
        template.process(data, new FileWriter(new File("t.java")));
    }
}
