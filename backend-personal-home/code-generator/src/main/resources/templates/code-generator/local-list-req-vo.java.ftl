package ${reqVoPackageName};

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: ${descriptionName}列表查询参数
 * @author: ${author}
 * @date: ${date}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "${listReqVoClassName}")
public class ${listReqVoClassName} {

<#list businessFields as field>
    /**
     * ${field.entityComment}
     */
    @Schema(title = "${field.entityComment}")
    private ${field.entityType} ${field.entityName};

</#list>
}
