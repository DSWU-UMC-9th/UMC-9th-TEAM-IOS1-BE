package com.example.fog.controller;

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
}