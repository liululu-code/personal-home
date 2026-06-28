package top.lll44556.codeGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableMeta {

    private String schemaName;

    private String tableName;

    private String fullTableName;

    private List<ColumnMeta> columns = new ArrayList<>();

    private List<String> primaryKeyNames = new ArrayList<>();
}
