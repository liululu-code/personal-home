package ${packageName}.converts;

import com.dscomm.ems.ychz.constants.DictionaryType;
import org.mapstruct.factory.Mappers;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mapper;
import ${packageName}.web.vo.req.${UpperPojoName}QueryReqVO;
import ${packageName}.service.bean.${UpperPojoName}Bean;
import ${packageName}.web.vo.req.${UpperPojoName}SaveReqVO;
import ${packageName}.dal.entity.${UpperPojoName}Entity;
import ${packageName}.web.vo.res.${UpperPojoName}ResVO;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import org.mapstruct.Context;
import com.dscomm.ems.framework.common.util.StringUtils;
import com.dscomm.ems.ychz.service.cache.DictionaryCacheService;

/**
* @Description: ${name}
* @author: ${author}
* @date: ${date}
*/
@Mapper(componentModel = "spring")
public interface ${UpperPojoName}Convert{
    ${UpperPojoName}Convert INSTANCE = Mappers.getMapper(${UpperPojoName}Convert.class);
    
    /**
    * 输入QueryReqVO参数转换为bean
    *
    * @param vo req参数
    * @return bean返回值
    */
    ${UpperPojoName}Bean convertQueryReqVOToBean(${UpperPojoName}QueryReqVO vo);

    /**
     * List 输入QueryReqVO参数转换为bean
     *
     * @param voList req参数
     * @return bean返回值
     */
    List<${UpperPojoName}Bean> convertListQueryReqVOToBean(List<${UpperPojoName}QueryReqVO> voList);

    /**
     * 输入SaveReqVO参数转换为bean
     *
     * @param vo req参数
     * @return bean返回值
     */
    ${UpperPojoName}Bean convertSaveReqVOToBean(${UpperPojoName}SaveReqVO vo);


    /**
    * List 输入SaveReqVO参数转换为bean
    *
    * @param vo req参数
    * @return bean返回值
    */
    List<${UpperPojoName}Bean> convertSaveReqVOToBean(List<${UpperPojoName}SaveReqVO> vo);

    /**
    * bean转换为Entity
    *
    * @param bean 参数
    * @return entity返回值
    */
    ${UpperPojoName}Entity convertBeanToEntity(${UpperPojoName}Bean bean);

    /**
    * List bean转换为Entity
    *
    * @param bean 参数
    * @return entity返回值
    */
    List<${UpperPojoName}Entity> convertListBeanToEntity(List<${UpperPojoName}Bean> bean);
    
    /**
    * entity转换为bean
    *
    * @param entity 参数
    * @return bean 返回值
    */
    ${UpperPojoName}Bean convertEntityToBean(${UpperPojoName}Entity entity,@Context DictionaryCacheService dictionaryCacheService);

    /**
    * List entity转换为bean
    *
    * @param entities 参数
    * @return beans 返回值
    */
    List<${UpperPojoName}Bean> convertEntitiesToBeans(List<${UpperPojoName}Entity> entities,@Context DictionaryCacheService dictionaryCacheService);

    /**
    * bean 转为ResVO
    *
    * @param bean 参数
    * @return resvo返回值
    */
    ${UpperPojoName}ResVO convertBeanToResVO(${UpperPojoName}Bean bean);

    /**
     * List bean 转为ResVO
     *
     * @param bean 参数
     * @return resvo返回值
     */
    List<${UpperPojoName}ResVO> convertListBeanToResVO(List<${UpperPojoName}Bean> bean);

    String QYZT = DictionaryType.QYZT;//启用状态

    /**
     * 数据字典转换方法
     */
    @AfterMapping
    default void convertToDictionaryBean(@MappingTarget ${UpperPojoName}Bean bean, @Context DictionaryCacheService dictionaryCacheService){
        //Map<String, Map<String, String>> dictMap = dictionaryCacheService.getDictMap(Arrays.asList(QYZT));
        //if (!StringUtils.isBlank(bean.getType()) && dictMap.get(QYZT) != null){
        //    bean.setTypeName(dictMap.get(QYZT).get(bean.getType()));
        //}
    }
}
