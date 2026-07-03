package ${packageName}.service;

import ${packageName}.service.bean.${UpperPojoName}Bean;
import com.dscomm.ems.datasource.common.model.PageResult;
import com.dscomm.ems.datasource.common.model.PaginationParamBean;
import java.util.List;

/**
* @Description: ${name}
* @author: ${author}
* @date: ${date}
*/
public interface ${UpperPojoName}Service {

    /**
    * @Description: ${name}列表查询
    * @author: ${author}
    * @date: ${date}
    */
    PageResult<${UpperPojoName}Bean> find${UpperPojoName}Condition(PaginationParamBean<${UpperPojoName}Bean> inputInfo);

    /**
    * @Description: ${name}详情查询
    * @author: ${author}
    * @date: ${date}
    */
    ${UpperPojoName}Bean get${UpperPojoName}ById(String id);

    /**
    * @Description: ${name}保存
    * @author: ${author}
    * @date: ${date}
    */
    ${UpperPojoName}Bean save${UpperPojoName}(${UpperPojoName}Bean inputInfo);

    /**
    * @Description: ${name}删除
    * @author: ${author}
    * @date: ${date}
    */
    Boolean remove${UpperPojoName}(List<String> ids);

}