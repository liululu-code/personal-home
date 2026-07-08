package ${beanPackageName};

import ${baseBeanPackageName}.BaseBean;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: ${descriptionName}
 * @author: ${author}
 * @date: ${date}
 */
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
