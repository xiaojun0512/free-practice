package com.cd.wj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .excludePathPatterns("/user/login","/doc.html","/swagger-resources")
                .addPathPatterns("/**");
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor(){
        return new AuthenticationInterceptor();
    }
}
