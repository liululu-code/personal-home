package top.lll44556.codeGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnMeta {

    private String columnName;

    private String quotedColumnName;

    private String dataType;

    private String length;

    private boolean nullable;

    private boolean primaryKey;

    private String defaultValue;

    private String insertValueExpression;
}
