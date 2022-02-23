package com.cd.wj.controller;


import com.cd.wj.config.UserLoginToken;
import com.cd.wj.entity.User;
import com.cd.wj.service.UserService;
import com.cd.wj.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author xj
 * @since 2022-01-25
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    @ApiOperation(value = "登陆")
    public CommonResult<String> login(@RequestBody User user) {
        return CommonResult.success(userService.login(user));
    }

    @UserLoginToken
    @PostMapping("addUser")
    @ApiOperation(value = "新增用户")
    public CommonResult<String> addUser(@RequestBody User user) {
        return CommonResult.success(userService.addUser(user));
    }

    @PostMapping("saveUserList")
    @ApiOperation(value = "批量新增用户")
    public CommonResult<String> saveUserList() {
        return CommonResult.success(userService.saveUserList());
    }
}
