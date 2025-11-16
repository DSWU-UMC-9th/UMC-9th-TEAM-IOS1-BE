package com.example.fog.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequestDTO {
    private String username;
    private String password;
}
