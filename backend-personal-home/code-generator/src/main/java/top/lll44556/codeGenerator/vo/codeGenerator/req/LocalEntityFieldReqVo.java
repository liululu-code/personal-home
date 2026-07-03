package top.lll44556.codeGenerator.vo.codeGenerator.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalEntityFieldReqVo {

    private String columnName;

    private String entityType;

    private String entityName;

    private String entityComment;
}
