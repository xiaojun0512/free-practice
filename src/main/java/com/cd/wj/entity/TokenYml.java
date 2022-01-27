package com.cd.wj.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 配置在静态方法中使用yml配置参数
 */
@Component
public class TokenYml {
    private static long timeOut;

    @Value("${token.time_out}")
    private long time_out;

    public static long getTimeOut() {
        return timeOut;
    }

    //被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次
    @PostConstruct
    public void initTimeOut(){
        timeOut = this.time_out;
    }

}
