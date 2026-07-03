package top.lll44556.codeGenerator.vo.codeGenerator.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalParseTableResVo {

    private String schemaName;

    private String tableName;

    private String fullTableName;

    private List<LocalColumnResVo> columns = new ArrayList<>();
}
