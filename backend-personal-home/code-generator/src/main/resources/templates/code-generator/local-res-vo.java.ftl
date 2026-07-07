package ${resVoPackageName};

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "${resVoClassName}")
public class ${resVoClassName} {

<#list businessFields as field>
    /**
     * ${field.entityComment}
     */
    @Schema(title = "${field.entityComment}")
    private ${field.entityType} ${field.entityName};

</#list>
}
