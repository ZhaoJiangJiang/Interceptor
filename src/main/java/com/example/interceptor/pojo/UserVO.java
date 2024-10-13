package com.example.interceptor.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
public class UserVO {
    private String userName;
    private String password;
    private String token;
}
