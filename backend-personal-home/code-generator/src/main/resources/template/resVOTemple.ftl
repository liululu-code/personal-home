package ${packageName}.web.vo.res;

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
public class ${UpperPojoName}ResVO {

    @Schema(title = "主键id")
    private String id;
    @Schema(title = "创建时间")
    private Long createTime;

<#list attribute as item>
    @Schema(title = "${item.explain}")
    private String ${item.lowerFieldName};

</#list>

}
