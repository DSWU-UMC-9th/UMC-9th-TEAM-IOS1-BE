package com.example.fog.service;

import com.example.fog.code.ErrorCode;
import com.example.fog.code.ResponseCode;
import com.example.fog.dto.response.ErrorResponseDTO;
import com.example.fog.dto.response.ResponseDTO;
import com.example.fog.dto.user.LoginRequestDTO;
import com.example.fog.dto.user.LoginResponseDTO;
import com.example.fog.dto.user.RegisterDTO;
import com.example.fog.entity.User;
import com.example.fog.exception.GlobalException;
import com.example.fog.jwt.JWTUtil;
import com.example.fog.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    // 회원가입
    @Override
    public ResponseEntity<?> register(RegisterDTO dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        String UsernamePattern = "^[A-Za-z][A-Za-z0-9]{3,11}$";
        String PasswordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,15}$";

        // 아이디 형식이 맞지 않는 경우
        if(!username.matches(UsernamePattern)) {
            return ResponseEntity.status(ErrorCode.INVALID_USERNAME_FORMAT.getStatus().value())
                    .body(new ErrorResponseDTO(ErrorCode.INVALID_USERNAME_FORMAT, null));
        }

        // 비밀번호 형식이 맞지 않을 경우
        if(!password.matches(PasswordPattern)) {
            return ResponseEntity
                    .status(ErrorCode.INVALID_PASSWORD_FORMAT.getStatus().value())
                    .body(new ErrorResponseDTO(ErrorCode.INVALID_PASSWORD_FORMAT, null));
        }

        // Password != confirmPassword
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            return ResponseEntity.status(ErrorCode.PASSWORD_MISMATCH.getStatus().value())
                    .body(new ErrorResponseDTO(ErrorCode.PASSWORD_MISMATCH, null));
        }

        // 이미 존재하는 아이디일 경우
        if (userRepository.existsByUsername(dto.getUsername())) {
            return ResponseEntity.status(ErrorCode.DUPLICATE_USERNAME.getStatus().value())
                    .body(new ErrorResponseDTO(ErrorCode.DUPLICATE_USERNAME, null));
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        User user =
                User.builder()
                        .username(dto.getUsername())
                        .password(encodedPassword)
                        .build();

        userRepository.save(user);

        return ResponseEntity.status(ResponseCode.SUCCESS_REGISTER.getStatus().value())
                .body(new ResponseDTO<>(ResponseCode.SUCCESS_REGISTER, dto));
    }

    // 로그인
    @Override
    @Transactional
    public ResponseEntity<?> login(LoginRequestDTO dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        User user =
                userRepository
                        .findUserByUsername(username)
                        .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(ErrorCode.PASSWORD_NOT_CORRECT.getStatus().value())
                    .body(new ErrorResponseDTO(ErrorCode.PASSWORD_NOT_CORRECT, null));
        }

        String token = jwtUtil.createJWT(user.getUsername());

        LoginResponseDTO resp =
                LoginResponseDTO.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .token(token)
                        .build();

        return ResponseEntity.status(ResponseCode.SUCCESS_LOGIN.getStatus().value())
                .body(new ResponseDTO<>(ResponseCode.SUCCESS_LOGIN, resp));
    }
}
