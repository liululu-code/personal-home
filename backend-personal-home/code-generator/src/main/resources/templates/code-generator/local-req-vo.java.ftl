package ${reqVoPackageName};

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
@Schema(title = "${reqVoClassName}")
public class ${reqVoClassName} {

<#list businessFields as field>
    /**
     * ${field.entityComment}
     */
    @Schema(title = "${field.entityComment}")
    private ${field.entityType} ${field.entityName};

</#list>
}
