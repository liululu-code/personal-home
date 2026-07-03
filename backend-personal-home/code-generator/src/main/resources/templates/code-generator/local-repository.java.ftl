package ${repositoryPackageName};

import ${entityPackageName}.${entityClassName};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ${repositoryClassName} extends JpaRepository<${entityClassName}, String> {
}
