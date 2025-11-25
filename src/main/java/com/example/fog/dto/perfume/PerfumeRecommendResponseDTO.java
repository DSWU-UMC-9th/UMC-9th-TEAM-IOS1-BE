package com.example.fog.dto.perfume;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfumeRecommendResponseDTO {

    private Long id;
    private String name;
    private String imageUrl;
    private Long todayReviewCount;
}
