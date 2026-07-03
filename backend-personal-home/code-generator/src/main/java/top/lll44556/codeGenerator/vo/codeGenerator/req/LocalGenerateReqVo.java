package top.lll44556.codeGenerator.vo.codeGenerator.req;

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
public class LocalGenerateReqVo {

    private DatabaseType databaseType;

    private String createTableSql;

    private String packageName;

    private String entityClassName;

    private List<LocalGenerateContentType> contentTypes = new ArrayList<>();

    private List<LocalEntityFieldReqVo> entityFields = new ArrayList<>();

    private String outputDirectory;
}
