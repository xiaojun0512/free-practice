package com.cd.wj.config;

import com.cd.wj.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class MyException {

    @ExceptionHandler(Exception.class)
    public CommonResult<Object> handlerException(Exception e) {
        e.printStackTrace();
        log.info("开始打印错误信息了");
        log.error("报错信息如下："+e.getMessage());
        return CommonResult.error(e.getMessage());
    }
}
