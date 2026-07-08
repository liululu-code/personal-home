package ${packageName}.web.vo.req;

import lombok.Data;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

/**
* @Description: ${name}
* @author: ${author}
* @date: ${date}
*/
@Data
@Schema(title = "${name}")
public class ${UpperPojoName}SaveReqVO {

    @Schema(title = "主键id，修改时必传")
    @Size(max = 50, message = "主键id不能超过50")
    private String id;

<#list attribute as item>
    @Schema(title = "${item.explain}")
    private String ${item.lowerFieldName};

</#list>

}
