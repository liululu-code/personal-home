package ${controllerPackageName};

import common.lll44556.top.util.R;
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

@RestController
@AllArgsConstructor
@RequestMapping("/api/${lowerBaseClassName}")
public class ${controllerClassName} {

    private final ${serviceClassName} ${serviceFieldName};

    @PostMapping("/save")
    public R<${resVoClassName}> save(@RequestBody ${reqVoClassName} request) {
        return R.ok(${serviceFieldName}.save(request));
    }

    @GetMapping("/list")
    public R<List<${resVoClassName}>> list() {
        return R.ok(${serviceFieldName}.list());
    }

    @GetMapping("/detail")
    public R<${resVoClassName}> detail(@RequestParam("id") String id) {
        return R.ok(${serviceFieldName}.detail(id));
    }

    @PostMapping("/delete")
    public R<Boolean> delete(@RequestBody List<String> ids) {
        return R.ok(${serviceFieldName}.delete(ids));
    }
}
