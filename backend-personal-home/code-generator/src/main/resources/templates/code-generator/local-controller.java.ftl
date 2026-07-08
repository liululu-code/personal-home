package ${controllerPackageName};

import ${responseClassPackageName}.${responseClassName};
import ${beanPackageName}.${beanClassName};
import ${pageResultPackageName}.${pageResultClassName};
import ${paginationReqVoPackageName}.${paginationReqVoClassName};
import ${reqVoPackageName}.${listReqVoClassName};
import ${reqVoPackageName}.${reqVoClassName};
import ${servicePackageName}.${serviceClassName};
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
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
@Api(tags = "${descriptionName} 控制器")
public class ${controllerClassName} {

    private final ${serviceClassName} ${serviceFieldName};

    @PostMapping("/save")
    @Operation(summary = "保存${descriptionName}", description = "保存${descriptionName}api")
    public ${responseClassName}<${beanClassName}> save(@RequestBody ${reqVoClassName} request) {
        return ${responseClassName}.${responseSuccessMethodName}(${serviceFieldName}.save(request));
    }

    @PostMapping("/list")
    @Operation(summary = "获取${descriptionName}列表", description = "获取${descriptionName}列表api")
    public ${responseClassName}<${pageResultClassName}<${beanClassName}>> list(@RequestBody ${paginationReqVoClassName}<${listReqVoClassName}> request) {
        return ${responseClassName}.${responseSuccessMethodName}(${serviceFieldName}.list(request));
    }

    @GetMapping("/detail")
    @Operation(summary = "获取${descriptionName}详情", description = "获取${descriptionName}详情api")
    public ${responseClassName}<${beanClassName}> detail(@RequestParam("id") String id) {
        return ${responseClassName}.${responseSuccessMethodName}(${serviceFieldName}.detail(id));
    }

    @PostMapping("/delete")
    @Operation(summary = "删除${descriptionName}", description = "删除${descriptionName}api")
    public ${responseClassName}<Boolean> delete(@RequestBody List<String> ids) {
        return ${responseClassName}.${responseSuccessMethodName}(${serviceFieldName}.delete(ids));
    }
}
