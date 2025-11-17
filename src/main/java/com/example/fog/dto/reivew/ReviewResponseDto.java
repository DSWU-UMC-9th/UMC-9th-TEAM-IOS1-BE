package com.example.fog.dto.reivew;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {

    private Long id;
    private Integer rating;
    private String content;
    private String maskedUsername;
    private String updatedDate;

    public static String maskUsername(String username) {
        if (username.length() <= 4) return username + "****";
        return username.substring(0, 4) + "****";
    }
}