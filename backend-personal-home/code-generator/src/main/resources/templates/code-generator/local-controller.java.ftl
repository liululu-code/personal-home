package ${controllerPackageName};

import ${responseClassPackageName}.${responseClassName};
import ${reqVoPackageName}.${reqVoClassName};
import ${resVoPackageName}.${resVoClassName};
import ${servicePackageName}.${serviceClassName};
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: ${descriptionName} 控制器
 * @author: ${author}
 * @date: ${date}
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/${lowerBaseClassName}")
public class ${controllerClassName} {

    private final ${serviceClassName} ${serviceFieldName};

    @PostMapping("/save")
    public ${responseClassName}<${resVoClassName}> save(@RequestBody ${reqVoClassName} request) {
        return ${responseClassName}.${responseSuccessMethodName}(${serviceFieldName}.save(request));
    }

    @GetMapping("/list")
    public ${responseClassName}<List<${resVoClassName}>> list() {
        return ${responseClassName}.${responseSuccessMethodName}(${serviceFieldName}.list());
    }

    @GetMapping("/detail")
    public ${responseClassName}<${resVoClassName}> detail(@RequestParam("id") String id) {
        return ${responseClassName}.${responseSuccessMethodName}(${serviceFieldName}.detail(id));
    }

    @PostMapping("/delete")
    public ${responseClassName}<Boolean> delete(@RequestBody List<String> ids) {
        return ${responseClassName}.${responseSuccessMethodName}(${serviceFieldName}.delete(ids));
    }
}
