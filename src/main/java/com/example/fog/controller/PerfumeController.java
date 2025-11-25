package com.example.fog.controller;

import com.example.fog.dto.perfume.PerfumeRecommendResponseDTO;
import com.example.fog.dto.perfume.PerfumeSummaryResponseDTO;
import com.example.fog.dto.perfume.PerfumeWithReviewsResponseDto;
import com.example.fog.dto.response.ResponseDTO;
import com.example.fog.service.PerfumeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfumes")
@RequiredArgsConstructor
public class PerfumeController {

    private final PerfumeService perfumeService;

    @Operation(summary = "향수 상세 조회", description = "향수 정보와 리뷰 조회 (리뷰 최신순)")
    @GetMapping("/{perfumeId}")
    public ResponseEntity<ResponseDTO<PerfumeWithReviewsResponseDto>> getPerfume(
            @PathVariable Long perfumeId
    ) {
        return ResponseEntity.ok(perfumeService.getPerfumeWithReviews(perfumeId));
    }

    @Operation(summary = "향수 목록 조회 (필터링)",
            description = "정렬: 평균 별점 높은 순(avgRateDesc), 낮은 순(avgRateAsc)")
    @GetMapping("/sorted")
    public ResponseEntity<ResponseDTO<List<PerfumeSummaryResponseDTO>>> getSortedPerfumes(
            @RequestParam(defaultValue = "avgRateDesc") String sort
    ) {
        return ResponseEntity.ok(perfumeService.getPerfumesSortedByRating(sort));
    }

    @Operation(summary = "추천 향수 표시", description = "하루 동안 새로운 리뷰 많은 top 3 (초기화 밤 12시)")
    @GetMapping("/recommendations")
    public ResponseEntity<ResponseDTO<List<PerfumeRecommendResponseDTO>>> getRecommendPerfumes() {
        return ResponseEntity.ok(perfumeService.getRecommendPerfumes());
    }
}