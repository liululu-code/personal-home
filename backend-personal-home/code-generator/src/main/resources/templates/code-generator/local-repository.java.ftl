package ${repositoryPackageName};

import ${entityPackageName}.${entityClassName};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description: ${descriptionName} Repository
 * @author: ${author}
 * @date: ${date}
 */
@Repository
public interface ${repositoryClassName} extends JpaRepository<${entityClassName}, String> {
}
