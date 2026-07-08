package top.lll44556.codeGenerator.vo.codeGenerator.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.lll44556.codeGenerator.enums.DatabaseType;
import top.lll44556.codeGenerator.enums.InputType;
import top.lll44556.codeGenerator.enums.OutputType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "代码生成请求")
public class GenerateReqVo {

    @Schema(title = "输入类型")
    private InputType inputType;

    @Schema(title = "数据库类型")
    private DatabaseType databaseType;

    @Schema(title = "输出类型")
    private OutputType outputType;

    @Schema(title = "输入内容")
    private String inputContent;

}
