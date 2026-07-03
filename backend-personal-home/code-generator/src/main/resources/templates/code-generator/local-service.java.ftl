package ${servicePackageName};

import ${reqVoPackageName}.${reqVoClassName};
import ${resVoPackageName}.${resVoClassName};

import java.util.List;

public interface ${serviceClassName} {

    ${resVoClassName} save(${reqVoClassName} request);

    List<${resVoClassName}> list();

    ${resVoClassName} detail(String id);

    Boolean delete(List<String> ids);
}
