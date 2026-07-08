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
@Schema(title = "离线代码生成响应")
public class LocalGenerateResVo {

    @Schema(title = "离线生成目录")
    private String generatedDirectory;

    @Schema(title = "离线生成 zip 文件路径")
    private String zipFilePath;

    @Schema(title = "离线生成 zip 文件名")
    private String zipFileName;

    @Schema(title = "离线生成结果文件")
    private List<String> generatedFiles = new ArrayList<>();

    @Schema(title = "生成消息")
    private String message;
}
