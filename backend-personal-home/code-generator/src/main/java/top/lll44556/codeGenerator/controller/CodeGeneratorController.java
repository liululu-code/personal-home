package top.lll44556.codeGenerator.controller;

import common.lll44556.top.util.R;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

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
    public ResponseEntity<Resource> generateLocal(@RequestBody LocalGenerateReqVo request) {
        LocalGenerateResVo generateResult = localCodeGeneratorService.generate(request);
        Resource zipResource = new FileSystemResource(Path.of(generateResult.getZipFilePath()));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/zip"))
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                        .filename(generateResult.getZipFileName(), StandardCharsets.UTF_8)
                        .build()
                        .toString())
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .body(zipResource);
    }
}
