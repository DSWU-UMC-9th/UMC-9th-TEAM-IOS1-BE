package com.example.fog.dto.perfume;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfumeSummaryResponseDTO {

    private Long id;
    private String name;
    private String imageUrl;

    private Double averageRating;
    private Long reviewCount;
}
