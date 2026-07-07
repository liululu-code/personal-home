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
     * @Description: 使用 PostgreSQL nativeQuery 查询${descriptionName}列表
     * @author: ${author}
     * @date: ${date}
     */
    @Query(value = "select * from ${tableName} where yxx = 1", nativeQuery = true)
    List<${entityClassName}> find${baseClassName}ConditionByNativeQuery();

    /**
     * @Description: 使用 PostgreSQL nativeQuery 查询${descriptionName}数量
     * @author: ${author}
     * @date: ${date}
     */
    @Query(value = "select count(1) from ${tableName} where yxx = 1", nativeQuery = true)
    Integer count${baseClassName}ConditionByNativeQuery();
}
