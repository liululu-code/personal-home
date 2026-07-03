package ${packageName}.service.nativequery;

import ${packageName}.dal.entity.${UpperPojoName}Entity;
import ${packageName}.service.bean.${UpperPojoName}Bean;
import com.dscomm.ems.datasource.common.model.PaginationParamBean;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import com.dscomm.ems.datasource.common.model.Pagination;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
* @Description: ${name}
* @author: ${author}
* @date: 2024/7/8
*/
@Component
@Slf4j
public class ${UpperPojoName}NativeQueryPostgreSQL implements ${UpperPojoName}NativeQuery{
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * @Description: 查询${name}列表
     * @author: ${author}
     * @date: ${date}
     */
    public List<${UpperPojoName}Entity> find${UpperPojoName}Condition(PaginationParamBean<${UpperPojoName}Bean> inputInfo){
        String headsql = "  select t from ${UpperPojoName}Entity t  where t.valid = 1  ";
        StringBuilder conditionsql = new StringBuilder("");
        conditionsql.append(headsql);
        createQueryCondition(inputInfo.getParams(), conditionsql);
        String endsql = "   ";
        String ordersql=" order by t.createTime desc  ";

        conditionsql.append(endsql).append(ordersql);
        String sql = conditionsql.toString();
        Query query = entityManager.createQuery(sql);
        setParame(inputInfo.getParams(), query);
        // Pagination
        Pagination pagination = Optional.of(inputInfo).map(PaginationParamBean::getPagination).orElse(new Pagination());
        if (Boolean.TRUE.equals(inputInfo.getWhetherPagination())) {
            query.setFirstResult((pagination.getPage() - 1) * pagination.getSize());
            query.setMaxResults(pagination.getSize());
        }
        return query.getResultList();
    }

    /**
     * @Description: 查询${name}数
     * @author: ${author}
     * @date: ${date}
     */
    public Integer find${UpperPojoName}Total(PaginationParamBean<${UpperPojoName}Bean> inputInfo){
        String headsql = "  select count(1) as  NUM  from ${UpperPojoName}Entity t  where t.valid = 1  ";
        StringBuilder conditionsql = new StringBuilder("");
        conditionsql.append(headsql);
        createQueryCondition(inputInfo.getParams(),conditionsql);
        String endsql = "   ";
        conditionsql.append(endsql);
        String sql = conditionsql.toString();
        Query query = entityManager.createQuery(sql);
        setParame(inputInfo.getParams(),query);
        return Integer.valueOf(query.getSingleResult().toString());
    }

    /**
     * @Description: 创建查询语句
     * @author: ${author}
     * @date: ${date}
     */
    private static void createQueryCondition(${UpperPojoName}Bean inputInfo, StringBuilder conditionsql) {
        if (StringUtils.isNotBlank(inputInfo.getKeywords())){
            conditionsql.append(" and (t.name like :gjz ) ");
        }
        <#list attribute as item>
        if (StringUtils.isNotBlank(inputInfo.get${item.upperFieldName}())){
            conditionsql.append(" and t.${item.lowerFieldName} = :${item.columnName} ");
        }
        </#list>

    }

    /**
     * @Description: 对查询语句赋值
     * @author: ${author}
     * @date: ${date}
     */
    private static void setParame(${UpperPojoName}Bean inputInfo, Query query) {
        if (StringUtils.isNotBlank(inputInfo.getKeywords())){
            query.setParameter("gjz", "%" + inputInfo.getKeywords() + "%");
        }
        <#list attribute as item>
        if (StringUtils.isNotBlank(inputInfo.get${item.upperFieldName}())){
            query.setParameter("${item.columnName}", inputInfo.get${item.upperFieldName}());
        }
        </#list>

    }
}
