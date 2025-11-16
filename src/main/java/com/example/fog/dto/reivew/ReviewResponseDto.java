package com.example.fog.dto.reivew;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {

    private Integer rating;
    private String content;
    private String maskedUsername; // 아이디 앞 4글자 + ****
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static String maskUsername(String username) {
        if (username.length() <= 4) return username + "****";
        return username.substring(0, 4) + "****";
    }
}