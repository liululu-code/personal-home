package top.lll44556.codeGenerator.vo.codeGenerator.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.lll44556.codeGenerator.enums.DatabaseType;
import top.lll44556.codeGenerator.enums.LocalGenerateContentType;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "本地代码生成请求")
public class LocalGenerateReqVo {

    @Schema(title = "数据库类型")
    private DatabaseType databaseType;

    @Schema(title = "建表 SQL")
    private String createTableSql;

    @Schema(title = "Entity 包名")
    private String packageName;

    @Schema(title = "Entity 类名")
    private String entityClassName;

    @Schema(title = "本地生成内容")
    private List<LocalGenerateContentType> contentTypes = new ArrayList<>();

    @Schema(title = "本地生成字段信息")
    private List<LocalEntityFieldReqVo> entityFields = new ArrayList<>();

    @Schema(title = "本地生成文件夹")
    private String outputDirectory;
}
