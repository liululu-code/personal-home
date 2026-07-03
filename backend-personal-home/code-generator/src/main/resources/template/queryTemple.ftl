package ${packageName}.service.nativequery;

import ${packageName}.dal.entity.${UpperPojoName}Entity;
import ${packageName}.service.bean.${UpperPojoName}Bean;
import com.dscomm.ems.datasource.common.model.PaginationParamBean;

import java.util.List;

/**
* @Description: ${name}
* @author: ${author}
* @date: ${date}
*/
public interface ${UpperPojoName}NativeQuery {

    /**
     * @Description: 查询${name}列表
     * @author: ${author}
     * @date: ${date}
     */
    List<${UpperPojoName}Entity> find${UpperPojoName}Condition(PaginationParamBean<${UpperPojoName}Bean> inputInfo);

    /**
     * @Description: 查询${name}数
     * @author: ${author}
     * @date: ${date}
     */
    Integer find${UpperPojoName}Total(PaginationParamBean<${UpperPojoName}Bean> inputInfo);


}
