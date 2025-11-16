package com.example.fog.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDTO {
    private Long id;
    private String token;
    private String username;
    private String password;
}
