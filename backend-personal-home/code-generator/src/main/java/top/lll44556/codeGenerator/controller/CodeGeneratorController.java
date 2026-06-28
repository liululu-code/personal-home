package top.lll44556.codeGenerator.controller;

import common.lll44556.top.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lll44556.codeGenerator.service.CodeGeneratorService;
import top.lll44556.codeGenerator.vo.codeGenerator.req.GenerateReqVo;
import top.lll44556.codeGenerator.vo.codeGenerator.res.GenerateResVo;

@RestController
@AllArgsConstructor
@RequestMapping("/api/code-generator")
public class CodeGeneratorController {

    private final CodeGeneratorService codeGeneratorService;

    @PostMapping("/generate")
    public R<GenerateResVo> generate(@RequestBody GenerateReqVo request) {
        String outputContent = codeGeneratorService.generate(request);
        return R.ok(new GenerateResVo(outputContent));
    }
}
