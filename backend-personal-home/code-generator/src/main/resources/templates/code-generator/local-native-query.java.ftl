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
     * @Description: 查询${descriptionName}列表
     * @author: ${author}
     * @date: ${date}
     */
    List<${entityClassName}> find${baseClassName}Condition();

    /**
     * @Description: 查询${descriptionName}数量
     * @author: ${author}
     * @date: ${date}
     */
    Integer find${baseClassName}Total();
}
