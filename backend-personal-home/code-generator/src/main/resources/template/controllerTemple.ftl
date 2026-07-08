package ${packageName}.web.controller;

import com.dscomm.ems.datasource.common.model.PageResult;
import com.dscomm.ems.datasource.common.model.PaginationParamBean;
import com.dscomm.ems.datasource.common.model.PaginationReqVO;
import com.dscomm.ems.framework.common.result.Result;
import ${packageName}.web.vo.req.${UpperPojoName}QueryReqVO;
import ${packageName}.web.vo.req.${UpperPojoName}SaveReqVO;
import ${packageName}.web.vo.res.${UpperPojoName}ResVO;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;
import ${packageName}.service.${UpperPojoName}Service;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import ${packageName}.converts.${UpperPojoName}Convert;
import ${packageName}.service.bean.${UpperPojoName}Bean;
import java.util.List;

/**
* @Description: ${name}的controller层
* @author: ${author}
* @date: ${date}
*/
@RestController
@RequestMapping("/api/${LowerPojoName}")
@Slf4j
@Tag(name = "${name}", description = "${name}相关API")
public class ${UpperPojoName}Controller {

    private ${UpperPojoName}Service ${LowerPojoName}Service;

    public ${UpperPojoName}Controller(${UpperPojoName}Service ${LowerPojoName}Service) {
        this.${LowerPojoName}Service = ${LowerPojoName}Service;
    }


    @PostMapping("/save${UpperPojoName}")
    @Operation(summary = "保存${name}", description = "新增${name}api")
    public Result<${UpperPojoName}ResVO> save${UpperPojoName}(@Valid @RequestBody ${UpperPojoName}SaveReqVO inputInfo) {
        ${UpperPojoName}Bean reqBean = ${UpperPojoName}Convert.INSTANCE.convertSaveReqVOToBean(inputInfo);
        ${UpperPojoName}Bean resBean = ${LowerPojoName}Service.save${UpperPojoName}(reqBean);
        ${UpperPojoName}ResVO resVo  = ${UpperPojoName}Convert.INSTANCE.convertBeanToResVO(resBean);
        return Result.success(resVo);
    }

    @PostMapping("/get${UpperPojoName}List")
    @Operation(summary = "获取${name}列表", description = "获取${name}列表api")
    public Result<PageResult<${UpperPojoName}ResVO>> get${UpperPojoName}List(@RequestBody PaginationReqVO<${UpperPojoName}QueryReqVO> inputInfo) {
        PaginationParamBean<${UpperPojoName}Bean> paramBean = new PaginationParamBean<>();
        paramBean.setPagination(inputInfo.getPagination());
        paramBean.setParams(${UpperPojoName}Convert.INSTANCE.convertQueryReqVOToBean(inputInfo.getParams()));
        paramBean.setWhetherPagination(inputInfo.getWhetherPagination());
        PageResult<${UpperPojoName}Bean> pageBeanResult = ${LowerPojoName}Service.find${UpperPojoName}Condition(paramBean);
        PageResult<${UpperPojoName}ResVO> resultVo = new PageResult<>();
        resultVo.setRecords(${UpperPojoName}Convert.INSTANCE.convertListBeanToResVO(pageBeanResult.getRecords()));
        return Result.success(resultVo);
    }


    @GetMapping("/get${UpperPojoName}ById")
    @Operation(summary = "获取${name}详情", description = "获取${name}详情api")
    public Result<${UpperPojoName}ResVO> get${UpperPojoName}ById(@RequestParam("id") String id) {
        ${UpperPojoName}Bean resBean = ${LowerPojoName}Service.get${UpperPojoName}ById(id);
        ${UpperPojoName}ResVO resVo = ${UpperPojoName}Convert.INSTANCE.convertBeanToResVO(resBean);
        return Result.success(resVo);
    }


    @PostMapping("/delete${UpperPojoName}")
    @Operation(summary = "删除${name}", description = "删除${name}api")
    public Result<Boolean> delete${UpperPojoName}(@RequestBody List<String> ids) {
        Boolean res = ${LowerPojoName}Service.remove${UpperPojoName}(ids);
        return Result.success(res);
    }

}











