package com.example.fog.dto.reivew;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class ReviewRequestDTO {

    @NotNull
    @Min(1)
    @Max(5)
    private Integer score;

    @NotBlank
    private String content;
}