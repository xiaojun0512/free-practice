package com.cd.wj.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cd.wj.entity.TokenYml;
import com.cd.wj.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class TokenUtil {

    /**
     * token生成
     * @param user
     * @return
     */
    public static String getToken(User user) {
        Date start = new Date();
        long current = System.currentTimeMillis() + TokenYml.getTimeOut();
        Date end = new Date(current);
        String token = "";
        token = JWT.create().withAudience(user.getId()) //将userId存入token
                .withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));   //以用户密码作为token的密钥
        return token;
    }

    /**
     * 根据token获取userId
     * @return
     */
    public static String getTokenUserId() {
        //从http请求头中取出token
        String token = getRequest().getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        return userId;
    }

    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }
}
