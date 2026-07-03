package ${packageName}.web.vo.req;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

/**
* @Description: ${name}
* @author: ${author}
* @date: ${date}
*/
@Data
@Schema(title = "${name}")
public class ${UpperPojoName}QueryReqVO {


    @Schema(title = "关键字模糊匹配")
    private String keywords;

<#list attribute as item>
    @Schema(title = "${item.explain}")
    private String ${item.lowerFieldName};

</#list>



}
