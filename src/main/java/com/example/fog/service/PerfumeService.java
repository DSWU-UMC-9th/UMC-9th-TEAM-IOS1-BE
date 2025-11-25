package com.example.fog.service;

import com.example.fog.code.ErrorCode;
import com.example.fog.code.ResponseCode;
import com.example.fog.dto.perfume.PerfumeRecommendResponseDTO;
import com.example.fog.dto.perfume.PerfumeSummaryResponseDTO;
import com.example.fog.dto.perfume.PerfumeWithReviewsResponseDto;
import com.example.fog.dto.reivew.ReviewResponseDto;
import com.example.fog.dto.response.ResponseDTO;
import com.example.fog.entity.Perfume;
import com.example.fog.entity.Review;
import com.example.fog.exception.GlobalException;
import com.example.fog.repository.PerfumeRepository;
import com.example.fog.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerfumeService {

    private final PerfumeRepository perfumeRepository;
    private final ReviewRepository reviewRepository;

    public ResponseDTO<PerfumeWithReviewsResponseDto> getPerfumeWithReviews(Long perfumeId) {
        Perfume perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_PERFUME));

        //List<Review> reviews = reviewRepository.findByPerfume(perfume, Sort.by(Sort.Direction.DESC, "createdAt"));
        List<Review> reviews = reviewRepository.findByPerfumeWithUser(perfume);

        List<ReviewResponseDto> reviewDtos = reviews.stream().map(r -> ReviewResponseDto.builder()
                .id(r.getId())
                .rating(r.getRating())
                .content(r.getContent())
                .maskedUsername(ReviewResponseDto.maskUsername(r.getUser().getUsername()))
                .updatedDate(r.getUpdatedAt().toLocalDate().toString())
                .build()
        ).collect(Collectors.toList());

        Double averageRating = reviews.isEmpty() ? 0.0 :
                BigDecimal.valueOf(reviews.stream().mapToInt(Review::getRating).average().orElse(0))
                        .setScale(1, RoundingMode.HALF_UP)
                        .doubleValue();
        PerfumeWithReviewsResponseDto responseDto = PerfumeWithReviewsResponseDto.builder()
                .id(perfume.getId())
                .name(perfume.getName())
                .brand(perfume.getBrand())
                .price(perfume.getPrice())
                .description(perfume.getDescription())
                .imageUrl(perfume.getImageUrl())
                .reviewCount(reviewDtos.size())
                .averageRating(averageRating)
                .reviews(reviewDtos)
                .build();

        return new ResponseDTO<>(ResponseCode.SUCCESS_GET_PERFUME, responseDto);
    }

    public ResponseDTO<List<PerfumeSummaryResponseDTO>> getPerfumesSortedByRating(String sort) {

        List<PerfumeSummaryResponseDTO> perfumeDtos = perfumeRepository.findPerfumeRating();

        perfumeDtos.sort(sort.equals("avgRateAsc") ?
                Comparator.comparing(PerfumeSummaryResponseDTO::getAverageRating) :
                Comparator.comparing(PerfumeSummaryResponseDTO::getAverageRating).reversed()
        );

        return new ResponseDTO<>(ResponseCode.SUCCESS_GET_SORTED_PERFUMES, perfumeDtos);
    }

    public ResponseDTO<List<PerfumeRecommendResponseDTO>> getRecommendPerfumes() {

        LocalDateTime today = LocalDate.now().atStartOfDay();

        List<PerfumeRecommendResponseDTO> recommendDtos =
                perfumeRepository.findTodayTopPerfumes(today).stream()
                        .limit(3)
                        .toList();

        return new ResponseDTO<>(ResponseCode.SUCCESS_GET_RECOMMEND_PERFUMES, recommendDtos);
    }
}