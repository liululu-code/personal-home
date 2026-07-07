package ${beanPackageName};

import common.lll44556.top.bean.BaseBean;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "${beanClassName}")
public class ${beanClassName} extends BaseBean {

<#list businessFields as field>
    /**
     * ${field.entityComment}
     */
    @Schema(title = "${field.entityComment}")
    private ${field.entityType} ${field.entityName};

</#list>
}
