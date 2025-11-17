package com.example.fog.controller;

import com.example.fog.code.ResponseCode;
import com.example.fog.dto.response.ResponseDTO;
import com.example.fog.dto.user.RegisterDTO;
import com.example.fog.dto.user.LoginRequestDTO;
import com.example.fog.dto.user.UserInfoResponseDTO;
import com.example.fog.entity.User;
import com.example.fog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal(); // JWTFilter에서 User를 넣어둠
    }

    @Operation(summary = "회원가입", description = "회원가입")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO dto) {
        return userService.register(dto);
    }

    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO dto) {
        return userService.login(dto);
    }

    @Operation(summary = "내 정보 조회", description = "로그인한 사용자의 정보 반환")
    @GetMapping("/me")
    public ResponseEntity<ResponseDTO<UserInfoResponseDTO>> getMyInfo() {

        User user = getLoggedInUser();

        return ResponseEntity.ok(
                new ResponseDTO<>(ResponseCode.SUCCESS_LOGIN,
                        userService.getMyInfo(user.getUsername()))
        );
    }

}
