package top.lll44556.codeGenerator.vo.codeGenerator.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "本地代码生成响应")
public class LocalGenerateResVo {

    @Schema(title = "输出目录")
    private String outputDirectory;

    @Schema(title = "本地生成结果文件")
    private List<String> plannedFiles = new ArrayList<>();

    @Schema(title = "生成消息")
    private String message;
}
