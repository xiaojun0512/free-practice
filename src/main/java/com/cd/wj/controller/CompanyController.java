package com.cd.wj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cd.wj.entity.Company;
import com.cd.wj.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
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

    @PostMapping("getCompanyByCondition")
    @ApiOperation(value = "获取公司信息")
    public Page<Company> getCompanyByCondition(@RequestBody Company company){
        return companyService.getCompanyByCondition(company);
    }

    @GetMapping("getCompanyType")
    @ApiOperation(value = "获取公司性质")
    public Page<Company> getCompanyType(@RequestParam("id") String id){
        return companyService.getCompanyType(id);
    }
}
