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
@Schema(title = "本地建表 SQL 解读响应")
public class LocalParseTableResVo {

    @Schema(title = "Schema 名称")
    private String schemaName;

    @Schema(title = "表名")
    private String tableName;

    @Schema(title = "完整表名")
    private String fullTableName;

    @Schema(title = "字段解读结果")
    private List<LocalColumnResVo> columns = new ArrayList<>();
}
