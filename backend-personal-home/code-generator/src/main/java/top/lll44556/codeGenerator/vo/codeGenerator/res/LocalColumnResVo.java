package top.lll44556.codeGenerator.vo.codeGenerator.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalColumnResVo {

    private String columnName;

    private String quotedColumnName;

    private String dataType;

    private String length;

    private boolean nullable;

    private boolean primaryKey;

    private String defaultValue;

    private String comment;

    private String entityType;

    private String entityName;
}
