package ${packageName};

import common.lll44556.top.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "${tableName}")
public class ${entityClassName} extends BaseEntity {

<#list businessFields as field>
    /**
     * ${field.entityComment}
     */
    @Column(name = "${field.columnName}")
    private ${field.entityType} ${field.entityName};

</#list>
}
