package com.example.interceptor.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.interceptor.context.BaseContext;
import com.example.interceptor.properties.JwtProperties;
import com.example.interceptor.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(jwtProperties.getTokenName());
        log.info("token = {}", token);
        try {
            Claims claims = JwtUtil.parseJwt(jwtProperties.getSecretKey(), token);
            String userName = String.valueOf(claims.get("userName"));
            BaseContext.setCurrentUserName(userName);
            log.info("parseClaims = {}", claims);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            log.info("jwtParse exception: {}", e.getMessage());
            return false;
        }
    }

}
