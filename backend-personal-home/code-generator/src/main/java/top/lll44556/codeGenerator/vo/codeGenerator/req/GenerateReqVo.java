package top.lll44556.codeGenerator.vo.codeGenerator.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.lll44556.codeGenerator.enums.DatabaseType;
import top.lll44556.codeGenerator.enums.InputType;
import top.lll44556.codeGenerator.enums.OutputType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateReqVo {

    private InputType inputType;

    private DatabaseType databaseType;

    private OutputType outputType;

    private String inputContent;
}
