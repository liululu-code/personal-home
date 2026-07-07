package top.lll44556.codeGenerator.vo.codeGenerator.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "本地生成字段信息")
public class LocalEntityFieldReqVo {

    @Schema(title = "数据库字段名")
    private String columnName;

    @Schema(title = "Entity 成员类型")
    private String entityType;

    @Schema(title = "Entity 成员名称")
    private String entityName;

    @Schema(title = "字段注释")
    private String entityComment;
}
