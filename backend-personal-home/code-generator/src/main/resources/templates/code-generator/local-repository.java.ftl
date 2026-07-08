package ${repositoryPackageName};

import ${entityPackageName}.${entityClassName};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: ${descriptionName} Repository
 * @author: ${author}
 * @date: ${date}
 */
@Repository
public interface ${repositoryClassName} extends JpaRepository<${entityClassName}, String> {

    /**
     * 使用 PostgreSQL nativeQuery 查询有效${descriptionName}列表。
     * 当前模板提供基础示例，后续业务生成后应按查询条件调整 SQL 和方法参数。
     *
     * @return ${descriptionName}列表
     */
    @Query(value = "select * from ${tableName} where yxx = 1", nativeQuery = true)
    List<${entityClassName}> find${baseClassName}ConditionByNativeQuery();

    /**
     * 使用 PostgreSQL nativeQuery 统计有效${descriptionName}数量。
     * 当前模板提供基础示例，后续业务生成后应按查询条件调整 SQL 和方法参数。
     *
     * @return ${descriptionName}数量
     */
    @Query(value = "select count(1) from ${tableName} where yxx = 1", nativeQuery = true)
    Integer count${baseClassName}ConditionByNativeQuery();

}
