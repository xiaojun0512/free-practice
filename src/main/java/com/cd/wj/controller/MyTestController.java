package com.cd.wj.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@Api(tags = "测试")
public class MyTestController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("getServerPort")
    @ApiOperation(value = "获取系统端口号")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",dataType = "string", name = "name", value = "名字", required = false)
    })
    public String getServerPort(@RequestParam("name") String name) {
        return serverPort;
    }
}
