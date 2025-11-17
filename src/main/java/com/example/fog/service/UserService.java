package com.example.fog.service;

import com.example.fog.dto.user.LoginRequestDTO;
import com.example.fog.dto.user.RegisterDTO;
import com.example.fog.dto.user.UserInfoResponseDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> register(RegisterDTO dto);
    ResponseEntity<?> login(LoginRequestDTO dto);
    UserInfoResponseDTO getMyInfo(String username);
}
