package ${nativeQueryPostgreSQLPackageName};

import ${entityPackageName}.${entityClassName};
import ${nativeQueryPackageName}.${nativeQueryClassName};
import ${repositoryPackageName}.${repositoryClassName};
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: ${descriptionName} PostgreSQL NativeQuery 适配器
 * @author: ${author}
 * @date: ${date}
 */
@Component
@AllArgsConstructor
public class ${nativeQueryPostgreSQLClassName} implements ${nativeQueryClassName} {

    private final ${repositoryClassName} ${repositoryFieldName};

    @Override
    public List<${entityClassName}> find${baseClassName}Condition() {
        return ${repositoryFieldName}.find${baseClassName}ConditionByNativeQuery();
    }

    @Override
    public Integer find${baseClassName}Total() {
        return ${repositoryFieldName}.count${baseClassName}ConditionByNativeQuery();
    }

}
