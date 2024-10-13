package com.example.interceptor.controller;

import com.example.interceptor.Result;
import com.example.interceptor.context.BaseContext;
import com.example.interceptor.pojo.User;
import com.example.interceptor.pojo.UserVO;
import com.example.interceptor.properties.JwtProperties;
import com.example.interceptor.util.JwtUtil;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class MyController {

    @Autowired
    private JwtProperties jwtProperties;

    @GetMapping
    public String testEndPoint() {
        String userName = BaseContext.getCurrentUserName();
        return "Token is valid - " + userName;
    }

    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody User user) {
        log.info("user = {} - {}", user.getUserName(), user.getPassword());
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", user.getUserName());
        String token = JwtUtil.createJwt(
                jwtProperties.getSecretKey(),
                jwtProperties.getExpirationTime(),
                claims);

        UserVO userVO = UserVO.builder()
                .userName(user.getUserName())
                .password(user.getPassword())
                .token(token)
                .build();
        return Result.success(userVO);
    }
}























