package com.example.fog.dto.reivew;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class ReviewRequestDTO {

    @NotNull
    @Min(1)
    @Max(5)
    @Digits(integer = 1, fraction = 0, message = "별점은 소수점 없이 1~5의 정수여야 합니다.")
    private Integer score;

    @NotBlank
    private String content;
}