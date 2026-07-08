package ${servicePackageName};

import ${beanPackageName}.${beanClassName};
import ${pageResultPackageName}.${pageResultClassName};
import ${paginationReqVoPackageName}.${paginationReqVoClassName};
import ${reqVoPackageName}.${listReqVoClassName};
import ${reqVoPackageName}.${reqVoClassName};

import java.util.List;

/**
 * @Description: ${descriptionName}
 * @author: ${author}
 * @date: ${date}
 */
public interface ${serviceClassName} {

    ${beanClassName} save(${reqVoClassName} request);

    ${pageResultClassName}<${beanClassName}> list(${paginationReqVoClassName}<${listReqVoClassName}> request);

    ${beanClassName} detail(String id);

    Boolean delete(List<String> ids);
}
