package ${convertPackageName};

import ${beanPackageName}.${beanClassName};
import ${entityPackageName}.${entityClassName};
import ${reqVoPackageName}.${listReqVoClassName};
import ${reqVoPackageName}.${reqVoClassName};
import ${resVoPackageName}.${resVoClassName};
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description: ${descriptionName} 转换器
 * @author: ${author}
 * @date: ${date}
 */
@Mapper(componentModel = "spring")
public interface ${convertClassName} {

    ${convertClassName} INSTANCE = Mappers.getMapper(${convertClassName}.class);

    /**
     * SaveReqVO 转换为 Bean
     *
     * @param vo 请求参数
     * @return Bean
     */
    ${beanClassName} convertSaveReqVOToBean(${reqVoClassName} vo);

    /**
     * SaveReqVO 列表转换为 Bean 列表
     *
     * @param voList 请求参数列表
     * @return Bean 列表
     */
    List<${beanClassName}> convertSaveReqVOToBeanList(List<${reqVoClassName}> voList);

    /**
     * ListReqVO 转换为 Bean
     *
     * @param vo 请求参数
     * @return Bean
     */
    ${beanClassName} convertListReqVOToBean(${listReqVoClassName} vo);

    /**
     * ListReqVO 列表转换为 Bean 列表
     *
     * @param voList 请求参数列表
     * @return Bean 列表
     */
    List<${beanClassName}> convertListReqVOToBeanList(List<${listReqVoClassName}> voList);

    /**
     * Bean 转换为 Entity
     *
     * @param bean Bean 参数
     * @return Entity
     */
    ${entityClassName} convertBeanToEntity(${beanClassName} bean);

    /**
     * Bean 列表转换为 Entity 列表
     *
     * @param beanList Bean 参数列表
     * @return Entity 列表
     */
    List<${entityClassName}> convertBeanToEntityList(List<${beanClassName}> beanList);

    /**
     * Entity 转换为 Bean
     *
     * @param entity Entity 参数
     * @return Bean
     */
    ${beanClassName} convertEntityToBean(${entityClassName} entity);

    /**
     * Entity 列表转换为 Bean 列表
     *
     * @param entityList Entity 参数列表
     * @return Bean 列表
     */
    List<${beanClassName}> convertEntityToBeanList(List<${entityClassName}> entityList);

    /**
     * Entity 转换为 Bean 后的字典字段占位处理
     *
     * @param entity Entity 参数
     * @param bean Bean 参数
     */
    @AfterMapping
    default void afterConvertEntityToBean(${entityClassName} entity, @MappingTarget ${beanClassName} bean) {
        ;
    }

    /**
     * Bean 转换为 ResVO
     *
     * @param bean Bean 参数
     * @return ResVO
     */
    ${resVoClassName} convertBeanToResVO(${beanClassName} bean);

    /**
     * Bean 列表转换为 ResVO 列表
     *
     * @param beanList Bean 参数列表
     * @return ResVO 列表
     */
    List<${resVoClassName}> convertBeanToResVOList(List<${beanClassName}> beanList);
}
