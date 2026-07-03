package top.lll44556.codeGenerator.vo.codeGenerator.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalGenerateResVo {

    private String outputDirectory;

    private List<String> plannedFiles = new ArrayList<>();

    private String message;
}
