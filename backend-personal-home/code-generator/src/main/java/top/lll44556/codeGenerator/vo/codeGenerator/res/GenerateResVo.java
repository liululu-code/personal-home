package top.lll44556.codeGenerator.vo.codeGenerator.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "代码生成响应")
public class GenerateResVo {

    @Schema(title = "生成内容")
    private String outputContent;
}
