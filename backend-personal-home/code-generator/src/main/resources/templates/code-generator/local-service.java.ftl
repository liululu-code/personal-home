package ${servicePackageName};

import ${reqVoPackageName}.${reqVoClassName};
import ${resVoPackageName}.${resVoClassName};

import java.util.List;

/**
 * @Description: ${descriptionName}
 * @author: ${author}
 * @date: ${date}
 */
public interface ${serviceClassName} {

    ${resVoClassName} save(${reqVoClassName} request);

    List<${resVoClassName}> list();

    ${resVoClassName} detail(String id);

    Boolean delete(List<String> ids);
}
