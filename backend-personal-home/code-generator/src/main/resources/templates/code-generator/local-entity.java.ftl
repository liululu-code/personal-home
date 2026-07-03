package ${packageName};

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "${tableName}")
public class ${entityClassName} {

<#list entityFields as field>
    /**
     * ${field.entityComment}
     */
    @Column(name = "${field.columnName}")
    private ${field.entityType} ${field.entityName};

</#list>
}
