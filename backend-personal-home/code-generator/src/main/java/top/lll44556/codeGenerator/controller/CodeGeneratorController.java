package top.lll44556.codeGenerator.controller;

import common.lll44556.top.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lll44556.codeGenerator.service.CodeGeneratorService;
import top.lll44556.codeGenerator.service.LocalCodeGeneratorService;
import top.lll44556.codeGenerator.vo.codeGenerator.req.GenerateReqVo;
import top.lll44556.codeGenerator.vo.codeGenerator.req.LocalGenerateReqVo;
import top.lll44556.codeGenerator.vo.codeGenerator.req.LocalParseTableReqVo;
import top.lll44556.codeGenerator.vo.codeGenerator.res.GenerateResVo;
import top.lll44556.codeGenerator.vo.codeGenerator.res.LocalGenerateResVo;
import top.lll44556.codeGenerator.vo.codeGenerator.res.LocalParseTableResVo;

@RestController
@AllArgsConstructor
@RequestMapping("/api/code-generator")
public class CodeGeneratorController {

    private final CodeGeneratorService codeGeneratorService;

    private final LocalCodeGeneratorService localCodeGeneratorService;

    @PostMapping("/generate")
    public R<GenerateResVo> generate(@RequestBody GenerateReqVo request) {
        String outputContent = codeGeneratorService.generate(request);  
        return R.ok(new GenerateResVo(outputContent));
    }

    @PostMapping("/local/parse-table")
    public R<LocalParseTableResVo> parseLocalTable(@RequestBody LocalParseTableReqVo request) {
        return R.ok(localCodeGeneratorService.parseTable(request));
    }

    @PostMapping("/local/generate")
    public R<LocalGenerateResVo> generateLocal(@RequestBody LocalGenerateReqVo request) {
        return R.ok(localCodeGeneratorService.generate(request));
    }
}
