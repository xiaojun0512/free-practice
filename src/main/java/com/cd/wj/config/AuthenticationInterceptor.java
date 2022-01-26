package com.cd.wj.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cd.wj.entity.User;
import com.cd.wj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * token
 * 拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从http请求头中取出token
        String token = request.getHeader("token");
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有PassToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
//        检查有没有需要用户权限的注解：如果方法上面有@UserLoginToken注解则,需要验证token
        if (method.isAnnotationPresent(UserLoginToken.class)){
            if (token == null) {
                throw new Exception("token为空,请重新登陆");
            }
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                //执行认证
                //获取token中的userId
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new Exception("401");
                }
                User user = userMapper.selectById(userId);
                if (user == null) {
                    throw new Exception("用户不存在,请重新登陆");
                }
                //验证token
                JWTVerifier build = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    build.verify(token);
                }catch (JWTVerificationException e) {
                    e.printStackTrace();
                    throw new RuntimeException("401");
                }
                return true;
            }
        }

        //验证所有方法token
        /*if (token == null) {
            throw new Exception("token为空,请重新登陆");
        } else {
            //执行认证
            //获取token中的userId
            String userId;
            try {
                userId = JWT.decode(token).getAudience().get(0);
            } catch (JWTDecodeException j) {
                throw new Exception("401");
            }
            User user = userMapper.selectById(userId);
            if (user == null) {
                throw new Exception("用户不存在,请重新登陆");
            }
            //验证token
            JWTVerifier build = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            try {
                build.verify(token);
            } catch (JWTVerificationException e) {
                e.printStackTrace();
                throw new RuntimeException("401");
            }
        }*/
        return true;
    }

}
