package com.cd.wj.config;

import com.cd.wj.entity.SystemOperationLog;
import com.cd.wj.mapper.SystemOperationLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author xj
 */
@Aspect
@Component
@Slf4j
public class MyLogAop {

    private final SystemOperationLogMapper logMapper;

    public MyLogAop(SystemOperationLogMapper logMapper) {
        this.logMapper = logMapper;
    }


    @Pointcut("execution(public * com.cd.wj.controller.*.*(..))")
    public void controllerLog(){
    }

    @Pointcut("execution(public * com.cd.wj.service.impl.*.*(..))")
    public void serviceLog(){
    }

    /**
     * 在方法执行之前执行
     */
    @Before("controllerLog()")
    public void doBeforeController(){
        log.info("controller方法执行前");
    }

    /**
     * 在方法执行之后执行
     */
    @After("controllerLog()")
    public void doAfterController(){
        log.info("controller方法执行后");
    }
    /**
     * 在方法执行之前执行
     */
    @Before("serviceLog()")
    public void doBeforeService(){
        log.info("service方法执行前");
    }

    /**
     * 在方法执行之后执行
     */
    @After("serviceLog()")
    public void doAfterService(){
        log.info("service方法执行后");
    }

    @Before("controllerLog()")
    public void doBeforeController(JoinPoint joinPoint){
        log.info("http请求前");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null){
            HttpServletRequest request = attributes.getRequest();
            log.info("url={}",request.getRequestURI());
            log.info("method={}",request.getMethod());
            log.info("ip={}",request.getRemoteAddr());
            //类方法
            log.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+
                    "."+joinPoint.getSignature().getName());
            log.info("arg={}", Arrays.toString(joinPoint.getArgs()));

            SystemOperationLog operationLog = new SystemOperationLog();
            operationLog.setCreateTime(LocalDateTime.now());
            operationLog.setUrl(request.getRequestURI());
            operationLog.setIp(request.getRemoteAddr());
            operationLog.setMethod(request.getMethod());
            logMapper.insert(operationLog);
        }
    }

    @AfterReturning(returning = "object",pointcut = "controllerLog()")
    public void doAfterReturning(Object object){
        log.info("result={}",object);
    }

}
