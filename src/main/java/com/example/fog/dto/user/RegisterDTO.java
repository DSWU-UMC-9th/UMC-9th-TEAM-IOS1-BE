package com.example.fog.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterDTO {
    private String username;
    private String password;
    private String confirmPassword;
}
