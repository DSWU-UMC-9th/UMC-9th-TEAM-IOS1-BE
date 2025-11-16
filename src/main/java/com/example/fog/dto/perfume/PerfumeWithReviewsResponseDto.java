package com.example.fog.dto.perfume;

import com.example.fog.dto.reivew.ReviewResponseDto;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfumeWithReviewsResponseDto {

    private Long id;
    private String name;
    private String brand;
    private String price;
    private String description;
    private String imageUrl;

    private Integer reviewCount;
    private Double averageRating;
    private List<ReviewResponseDto> reviews;
}