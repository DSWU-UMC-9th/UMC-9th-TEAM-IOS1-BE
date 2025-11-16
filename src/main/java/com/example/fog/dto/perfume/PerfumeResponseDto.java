package com.example.fog.dto.perfume;

import com.example.fog.dto.reivew.ReviewResponseDto;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfumeResponseDto {

    private String name;
    private String brand;
    private String price;
    private String description;
    private String imageUrl;
    private List<ReviewResponseDto> reviews;
}