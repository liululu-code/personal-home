package top.lll44556.codeGenerator.vo.codeGenerator.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.lll44556.codeGenerator.enums.DatabaseType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalParseTableReqVo {

    private DatabaseType databaseType;

    private String createTableSql;
}
