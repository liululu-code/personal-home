package ${packageName}.service.impl;

import com.dscomm.ems.datasource.common.accessor.GeneralAccessor;
import com.dscomm.ems.ychz.constants.DictionaryType;
import com.dscomm.ems.datasource.common.model.PageResult;
import com.dscomm.ems.datasource.common.model.PaginationParamBean;
import com.dscomm.ems.ychz.service.cache.DictionaryCacheService;
import ${packageName}.converts.${UpperPojoName}Convert;
import com.dscomm.ems.ychz.exception.YCYLException;
import com.dscomm.ems.ychz.dal.entity.${UpperPojoName}Entity;
import ${packageName}.service.bean.${UpperPojoName}Bean;
import ${packageName}.service.${UpperPojoName}Service;
import ${packageName}.dal.repository.${UpperPojoName}Repository;
import ${packageName}.service.nativequery.${UpperPojoName}NativeQuery;
import org.apache.commons.collections4.CollectionUtils;
import com.dscomm.ems.ychz.service.baseNativeQuery.GetNativeQueryBeanService;
import org.apache.commons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;


/**
* @Description: ${name}
* @author: ${author}
* @date: ${date}
*/
@Service
@Slf4j
public class ${UpperPojoName}ServiceImpl implements ${UpperPojoName}Service {
private Logger logger = LoggerFactory.getLogger(${UpperPojoName}ServiceImpl.class);
    private final ${UpperPojoName}Repository ${LowerPojoName}Repository;
    private final ${UpperPojoName}NativeQuery ${LowerPojoName}NativeQuery;
    private final GetNativeQueryBeanService getNativeQueryBeanService;
    private final GeneralAccessor access;
    private final DictionaryCacheService dictionaryCacheService;

    public List<String> dics;//数据字典

    public ${UpperPojoName}ServiceImpl(${UpperPojoName}Repository ${LowerPojoName}Repository,
                                 GetNativeQueryBeanService getNativeQueryBeanService,
                                 @Qualifier("jpaGeneralAccessor") GeneralAccessor accessor,
                                 DictionaryCacheService dictionaryCacheService){
        this.${LowerPojoName}Repository = ${LowerPojoName}Repository;
        this.getNativeQueryBeanService = getNativeQueryBeanService;
        this.${LowerPojoName}NativeQuery = getNativeQueryBeanService.getBean(${UpperPojoName}NativeQuery.class);
        this.access = accessor;
        this.dictionaryCacheService = dictionaryCacheService;
        dics = new ArrayList<>(Arrays.asList(DictionaryType.QYZT));

    }


    /**
    * @Description: ${name}列表查询
    * @author: ${author}
    * @date: ${date}
    */
    @Override
    @Transactional(readOnly = true)
    public PageResult<${UpperPojoName}Bean> find${UpperPojoName}Condition(PaginationParamBean<${UpperPojoName}Bean> inputInfo) {
        if(Objects.isNull(inputInfo)){
            throw new YCYLException(YCYLException.Errors.DATA_NULL);
        }
        try {
            List<${UpperPojoName}Entity> ${LowerPojoName}EntityList = ${LowerPojoName}NativeQuery.find${UpperPojoName}Condition(inputInfo);
            List<${UpperPojoName}Bean> ${LowerPojoName}BeanList = ${UpperPojoName}Convert.INSTANCE.convertEntitiesToBeans(${LowerPojoName}EntityList,dictionaryCacheService);
            Integer total = ${LowerPojoName}NativeQuery.find${UpperPojoName}Total(inputInfo);
            PageResult<${UpperPojoName}Bean> result = new PageResult<>();
            result.setPage(inputInfo.getPagination().getPage());
            result.setPageSize(inputInfo.getPagination().getSize());
            result.setTotal(total);
            result.setRecords(${LowerPojoName}BeanList);
            return result;
        }catch (Exception ex){
            log.error("数据查询失败：" ,ex);
            throw new YCYLException(YCYLException.Errors.FIND_DATA_FAIL);
        }
    }

    /**
    * @Description: ${name}详情查询
    * @author: ${author}
    * @date: ${date}
    */
    @Override
    @Transactional(readOnly = true)
    public ${UpperPojoName}Bean get${UpperPojoName}ById(String id) {
        if (StringUtils.isBlank(id)){
            throw new YCYLException(YCYLException.Errors.DATA_NULL);
        }
        try {
            ${UpperPojoName}Entity ${LowerPojoName}Entity = access.getById(id, ${UpperPojoName}Entity.class);
            ${UpperPojoName}Bean ${LowerPojoName}Bean = ${UpperPojoName}Convert.INSTANCE.convertEntityToBean(${LowerPojoName}Entity,dictionaryCacheService);
            return ${LowerPojoName}Bean;
        }catch (Exception ex){
            log.error("数据查询失败：" ,ex);
            throw new YCYLException(YCYLException.Errors.FIND_DATA_FAIL);
        }
    }

    /**
    * @Description: ${name}保存
    * @author: ${author}
    * @date: ${date}
    */
    @Override
    @Transactional
    public ${UpperPojoName}Bean save${UpperPojoName}(${UpperPojoName}Bean inputInfo) {
        if (Objects.isNull(inputInfo)){
            throw new YCYLException(YCYLException.Errors.DATA_NULL);
        }
        try {
            ${UpperPojoName}Entity ${LowerPojoName}Entity = ${UpperPojoName}Convert.INSTANCE.convertBeanToEntity(inputInfo);
            access.save(${LowerPojoName}Entity);
            ${UpperPojoName}Bean resBean = ${UpperPojoName}Convert.INSTANCE.convertEntityToBean(${LowerPojoName}Entity, dictionaryCacheService);
            return resBean;
        }catch (Exception ex){
            log.error("数据保存失败：" ,ex);
            throw new YCYLException(YCYLException.Errors.SAVE_DATA_FAIL);
        }
    }

    /**
    * @Description: ${name}删除
    * @author: ${author}
    * @date: ${date}
    */
    @Override
    @Transactional
    public Boolean remove${UpperPojoName}(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)){
            throw new YCYLException(YCYLException.Errors.DATA_NULL);
        }
        try {
            List<${UpperPojoName}Entity> ${LowerPojoName}EntityList = ${LowerPojoName}Repository.findAllById(ids);
            if (CollectionUtils.isNotEmpty(${LowerPojoName}EntityList)){
                access.remove(${LowerPojoName}EntityList,true);
            }
            return true;
        }catch (Exception ex){
            log.error("数据删除失败：" ,ex);
            throw new YCYLException(YCYLException.Errors.DELETE_DATA_FAIL);
        }
    }


}
