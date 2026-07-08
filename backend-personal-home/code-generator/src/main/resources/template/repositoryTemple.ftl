package ${packageName}.dal.repository;

import ${packageName}.dal.entity.${UpperPojoName}Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Description: ${name}
* @author: ${author}
* @date: ${date}
*/
@Repository
public interface ${UpperPojoName}Repository extends JpaRepository<${UpperPojoName}Entity,String> {

    /**
     * 查询单个
     * @param id
     * @return
     */
    @Query(" select t from ${UpperPojoName}Entity t where t.valid = 1 and t.id = ?1 ")
    ${UpperPojoName}Entity get${UpperPojoName}ById(String id);

    /**
     * 批量查询
     * @param id
     * @return
     */
    @Query(" select t from ${UpperPojoName}Entity t where t.valid = 1 and t.id in (?1) ")
    List<${UpperPojoName}Entity> find${UpperPojoName}ByIds(List<String> id);

}
