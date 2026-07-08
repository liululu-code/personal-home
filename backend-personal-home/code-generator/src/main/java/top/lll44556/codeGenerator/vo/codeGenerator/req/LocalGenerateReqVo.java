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

    @Schema(title = "响应类包名")
    private String responseClassPackageName;

    @Schema(title = "响应类名称")
    private String responseClassName;

    @Schema(title = "成功响应函数名")
    private String responseSuccessMethodName;

    @Schema(title = "分页结果类包名")
    private String pageResultPackageName;

    @Schema(title = "分页参数类包名")
    private String paginationPackageName;

    @Schema(title = "分页请求类包名")
    private String paginationReqVoPackageName;

    @Schema(title = "分页工具类包名")
    private String pageUtilPackageName;
}
