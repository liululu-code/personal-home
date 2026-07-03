package ${serviceImplPackageName};

import ${entityPackageName}.${entityClassName};
import ${repositoryPackageName}.${repositoryClassName};
import ${reqVoPackageName}.${reqVoClassName};
import ${resVoPackageName}.${resVoClassName};
import ${servicePackageName}.${serviceClassName};
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ${serviceImplClassName} implements ${serviceClassName} {

    private final ${repositoryClassName} ${repositoryFieldName};

    @Override
    @Transactional
    public ${resVoClassName} save(${reqVoClassName} request) {
        // TODO 后续补充 ReqVO 到 Entity 的字段映射，并根据主键判断新增或更新。
        ${entityClassName} entity = new ${entityClassName}();
        ${repositoryFieldName}.save(entity);

        // TODO 后续补充 Entity 到 ResVO 的字段映射。
        return new ${resVoClassName}();
    }

    @Override
    @Transactional(readOnly = true)
    public List<${resVoClassName}> list() {
        // TODO 后续补充分页、查询条件和 Entity 到 ResVO 的批量映射。
        return ${repositoryFieldName}.findAll()
                .stream()
                .map(entity -> new ${resVoClassName}())
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ${resVoClassName} detail(String id) {
        // TODO 后续补充未查询到数据时的业务异常和 Entity 到 ResVO 的字段映射。
        return ${repositoryFieldName}.findById(id)
                .map(entity -> new ${resVoClassName}())
                .orElse(null);
    }

    @Override
    @Transactional
    public Boolean delete(List<String> ids) {
        // TODO 后续补充空集合校验、软删除或业务删除规则。
        ${repositoryFieldName}.deleteAllById(ids);
        return true;
    }
}
