package top.lll44556.codeGenerator.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.lll44556.codeGenerator.service.TemplateRenderService;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Service
@AllArgsConstructor
public class FreeMarkerTemplateRenderServiceImpl implements TemplateRenderService {

    private final Configuration freeMarkerConfiguration;

    @Override
    public String render(String templateName, Map<String, Object> dataModel) {
        try (StringWriter writer = new StringWriter()) {
            Template template = freeMarkerConfiguration.getTemplate(templateName);
            template.process(dataModel, writer);
            return writer.toString();
        } catch (IOException | TemplateException e) {
            // 模板渲染失败属于生成链路的核心异常，先统一转为运行时异常，后续接全局异常响应。
            throw new IllegalStateException("模板渲染失败: " + templateName, e);
        }
    }
}
