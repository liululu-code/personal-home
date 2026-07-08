package top.lll44556.codeGenerator.service;

import top.lll44556.codeGenerator.vo.codeGenerator.req.LocalGenerateReqVo;
import top.lll44556.codeGenerator.vo.codeGenerator.req.LocalParseTableReqVo;
import top.lll44556.codeGenerator.vo.codeGenerator.res.LocalGenerateResVo;
import top.lll44556.codeGenerator.vo.codeGenerator.res.LocalParseTableResVo;

public interface LocalCodeGeneratorService {

    LocalParseTableResVo parseTable(LocalParseTableReqVo request);

    LocalGenerateResVo generate(LocalGenerateReqVo request);
}
