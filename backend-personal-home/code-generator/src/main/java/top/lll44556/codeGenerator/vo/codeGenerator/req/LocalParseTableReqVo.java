package top.lll44556.codeGenerator.vo.codeGenerator.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.lll44556.codeGenerator.enums.DatabaseType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "本地建表 SQL 解读请求")
public class LocalParseTableReqVo {

    @Schema(title = "数据库类型")
    private DatabaseType databaseType;

    @Schema(title = "建表 SQL")
    private String createTableSql;
}
