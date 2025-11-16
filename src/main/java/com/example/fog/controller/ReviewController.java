package com.example.fog.controller;
import com.example.fog.dto.reivew.ReviewRequestDTO;
import com.example.fog.dto.reivew.ReviewResponseDto;
import com.example.fog.dto.response.ResponseDTO;
import com.example.fog.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/perfumes/{perfumeId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 등록", description = "특정 향수에 리뷰를 등록합니다.")
    @PostMapping
    public ResponseEntity<ResponseDTO<ReviewResponseDto>> addReview(
            @PathVariable Long perfumeId,
            @RequestParam Long userId,
            @RequestBody @Valid ReviewRequestDTO dto
    ) {
        return ResponseEntity.ok(reviewService.addReview(perfumeId, userId, dto));
    }

    @Operation(summary = "리뷰 수정", description = "특정 향수에 등록한 리뷰를 수정합니다.")
    @PatchMapping("/{reviewId}")
    public ResponseEntity<ResponseDTO<ReviewResponseDto>> updateReview(
            @PathVariable Long reviewId,
            @RequestParam Long userId,
            @RequestBody @Valid ReviewRequestDTO dto
    ) {
        return ResponseEntity.ok(reviewService.updateReview(reviewId, userId, dto));
    }

}