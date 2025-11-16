package com.example.fog.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Perfume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // 향수 이름

    private String brand; // 브랜드

    @Column(nullable = false)
    private String price; // 가격 + 용량 (ex: "512,000 (100ML)")

    private String description; // 설명

    private String imageUrl; // 사진 URL

    @OneToMany(mappedBy = "perfume", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Review> reviews;
}