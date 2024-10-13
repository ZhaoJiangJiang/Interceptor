package com.example.interceptor.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "interceptor.jwt")
public class JwtProperties {

    private String secretKey;
    private long expirationTime;
    private String tokenName;
}
