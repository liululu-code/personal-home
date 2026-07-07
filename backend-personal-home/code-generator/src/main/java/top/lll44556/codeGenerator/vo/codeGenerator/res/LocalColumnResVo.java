package top.lll44556.codeGenerator.vo.codeGenerator.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "本地建表 SQL 字段解读结果")
public class LocalColumnResVo {

    @Schema(title = "数据库字段名")
    private String columnName;

    @Schema(title = "SQL 字段名")
    private String quotedColumnName;

    @Schema(title = "数据库字段类型")
    private String dataType;

    @Schema(title = "字段长度")
    private String length;

    @Schema(title = "是否可空")
    private boolean nullable;

    @Schema(title = "是否主键")
    private boolean primaryKey;

    @Schema(title = "默认值")
    private String defaultValue;

    @Schema(title = "字段注释")
    private String comment;

    @Schema(title = "Entity 成员类型")
    private String entityType;

    @Schema(title = "Entity 成员名称")
    private String entityName;
}
