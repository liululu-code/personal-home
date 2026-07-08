package ${nativeQueryPackageName};

import ${entityPackageName}.${entityClassName};

import java.util.List;

/**
 * @Description: ${descriptionName} NativeQuery
 * @author: ${author}
 * @date: ${date}
 */
public interface ${nativeQueryClassName} {

    /**
     * 查询${descriptionName}列表。
     *
     * @return ${descriptionName}列表
     */
    List<${entityClassName}> find${baseClassName}Condition();

    /**
     * 查询${descriptionName}总数。
     *
     * @return ${descriptionName}总数
     */
    Integer find${baseClassName}Total();

}
