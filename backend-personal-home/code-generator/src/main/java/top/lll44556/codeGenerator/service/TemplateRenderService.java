package top.lll44556.codeGenerator.service;

import java.util.Map;

public interface TemplateRenderService {

    String render(String templateName, Map<String, Object> dataModel);
}
