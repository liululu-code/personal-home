package ${packageName}.service.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @Description: ${name}
* @author: ${author}
* @date: ${date}
*/
@Data
@Accessors(chain = true)
public class ${UpperPojoName}Bean{

    private String keywords; //关键字模糊匹配

    @Schema(title = "主键id")
    private String id;
    @Schema(title = "创建时间")
    private Long createTime;

<#list attribute as item>
    /**
     * ${item.explain}
     */
    @Schema(title = "${item.explain}")
    private String ${item.lowerFieldName};

</#list>
}
