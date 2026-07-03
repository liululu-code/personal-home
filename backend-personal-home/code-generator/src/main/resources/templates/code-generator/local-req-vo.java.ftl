package ${reqVoPackageName};

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ${reqVoClassName} {

<#list entityFields as field>
    /**
     * ${field.entityComment}
     */
    private ${field.entityType} ${field.entityName};

</#list>
}
