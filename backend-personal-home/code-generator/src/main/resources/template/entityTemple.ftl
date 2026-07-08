package ${packageName}.dal.entity;

import com.dscomm.ems.datasource.jpa.entity.BaseEntity;
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


/**
* @Description: ${name}
* @author: ${author}
* @date: ${date}
*/
@Entity
@Table(name = "${tableName}")
@DynamicInsert()
@DynamicUpdate()
@Data
public class ${UpperPojoName}Entity extends BaseEntity {

    <#list attribute as item>
        /**
         * ${item.explain}
         */
        @Column(name = "${item.columnName}")
        private String ${item.lowerFieldName};

    </#list>

}
