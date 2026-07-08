package ${serviceImplPackageName};

import ${beanPackageName}.${beanClassName};
import ${convertPackageName}.${convertClassName};
import ${entityPackageName}.${entityClassName};
import ${pageResultPackageName}.${pageResultClassName};
import ${pageUtilPackageName}.${pageUtilClassName};
import ${paginationReqVoPackageName}.${paginationReqVoClassName};
import ${repositoryPackageName}.${repositoryClassName};
import ${reqVoPackageName}.${listReqVoClassName};
import ${reqVoPackageName}.${reqVoClassName};
import ${servicePackageName}.${serviceClassName};
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: ${descriptionName} 服务实现
 * @author: ${author}
 * @date: ${date}
 */
@Service
@AllArgsConstructor
@Slf4j
public class ${serviceImplClassName} implements ${serviceClassName} {

    private final ${repositoryClassName} ${repositoryFieldName};

    @Override
    @Transactional
    public ${beanClassName} save(${reqVoClassName} request) {
        if (request == null) {
            throw new IllegalArgumentException("${descriptionName}保存参数不能为空");
        }
        try {
            // 保存实体
            ${beanClassName} bean = ${convertClassName}.INSTANCE.convertSaveReqVOToBean(request);
            ${entityClassName} entity = ${convertClassName}.INSTANCE.convertBeanToEntity(bean);
            ${entityClassName} savedEntity = ${repositoryFieldName}.save(entity);
            return ${convertClassName}.INSTANCE.convertEntityToBean(savedEntity);
        } catch (Exception ex) {
            log.error("${descriptionName}保存失败", ex);
            throw new IllegalStateException("${descriptionName}保存失败", ex);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ${pageResultClassName}<${beanClassName}> list(${paginationReqVoClassName}<${listReqVoClassName}> request) {
        if (request == null) {
            throw new IllegalArgumentException("${descriptionName}列表查询参数不能为空");
        }
        boolean whetherPagination = Boolean.TRUE.equals(request.getWhetherPagination());
        Pageable pageable = whetherPagination
                ? ${pageUtilClassName}.checkPage(request.getPagination().getPage(), request.getPagination().getSize())
                : Pageable.unpaged();
        try {
            // 查询列表
            Page<${entityClassName}> entityPage = ${repositoryFieldName}.findAll(pageable);
            // 结果处理
            List<${beanClassName}> beanList = ${convertClassName}.INSTANCE.convertEntityToBeanList(entityPage.getContent());
            // 封装返回值
            ${pageResultClassName}<${beanClassName}> pageResult = new ${pageResultClassName}<>();
            if (whetherPagination) {
                pageResult.setPage(request.getPagination().getPage());
                pageResult.setPageSize(request.getPagination().getSize());
                pageResult.setTotal(entityPage.getTotalElements());
            }
            pageResult.setRecords(beanList);
            // 返回结果
            return pageResult;
        } catch (Exception ex) {
            log.error("${descriptionName}列表查询失败", ex);
            throw new IllegalStateException("${descriptionName}列表查询失败", ex);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ${beanClassName} detail(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("${descriptionName}详情查询 id 不能为空");
        }
        try {
            return ${repositoryFieldName}.findById(id)
                    .map(${convertClassName}.INSTANCE::convertEntityToBean)
                    .orElse(null);
        } catch (Exception ex) {
            log.error("${descriptionName}详情查询失败", ex);
            throw new IllegalStateException("${descriptionName}详情查询失败", ex);
        }
    }

    @Override
    @Transactional
    public Boolean delete(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("${descriptionName}删除 id 集合不能为空");
        }
        try {
            ${repositoryFieldName}.deleteAllById(ids);
            return true;
        } catch (Exception ex) {
            log.error("${descriptionName}删除失败", ex);
            throw new IllegalStateException("${descriptionName}删除失败", ex);
        }
    }
}
