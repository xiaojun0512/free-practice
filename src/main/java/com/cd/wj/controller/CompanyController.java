package com.cd.wj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cd.wj.config.UserLoginToken;
import com.cd.wj.entity.Company;
import com.cd.wj.service.CompanyService;
import com.cd.wj.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("company")
@Api(tags = "公司信息接口管理")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("addCompany")
    @ApiOperation(value = "新增公司")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query",dataType = "string", name = "name", value = "名字", required = false)
//    })
    public Integer addCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @UserLoginToken
    @PostMapping("getCompanyByCondition")
    @ApiOperation(value = "获取公司信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",dataType = "string", name = "token", value = "token", required = true)
    })
    public CommonResult<Page<Company>> getCompanyByCondition(@RequestBody Company company){
        return CommonResult.success(companyService.getCompanyByCondition(company));
    }

    @GetMapping("getCompanyType")
    @ApiOperation(value = "获取公司性质")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",dataType = "int", name = "id", value = "id", required = true)
    })
    public Page<Company> getCompanyType(@RequestParam("id") Integer id){
        return companyService.getCompanyType(id);
    }
}
