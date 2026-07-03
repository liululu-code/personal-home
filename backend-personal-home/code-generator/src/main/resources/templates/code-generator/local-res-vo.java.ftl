package ${resVoPackageName};

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ${resVoClassName} {

<#list entityFields as field>
    /**
     * ${field.entityComment}
     */
    private ${field.entityType} ${field.entityName};

</#list>
}
