package com.example.fog.service;

import com.example.fog.code.ErrorCode;
import com.example.fog.code.ResponseCode;
import com.example.fog.dto.reivew.ReviewRequestDTO;
import com.example.fog.dto.reivew.ReviewResponseDto;
import com.example.fog.dto.response.ResponseDTO;
import com.example.fog.entity.Perfume;
import com.example.fog.entity.Review;
import com.example.fog.entity.User;
import com.example.fog.exception.GlobalException;
import com.example.fog.repository.PerfumeRepository;
import com.example.fog.repository.ReviewRepository;
import com.example.fog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PerfumeRepository perfumeRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseDTO<ReviewResponseDto> addReview(Long perfumeId, Long userId, ReviewRequestDTO dto) {

        Perfume perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new GlobalException(ErrorCode.NOT_FOUND_PERFUME));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

        Review review = Review.builder()
                .perfume(perfume)
                .user(user)
                .rating(dto.getScore())
                .content(dto.getContent())
                .maskedUsername(user.getUsername().substring(0, 4) + "****")
                .build();

        Review saved = reviewRepository.save(review);

        ReviewResponseDto responseDto = ReviewResponseDto.builder()
                .id(review.getId())
                .rating(saved.getRating())
                .content(saved.getContent())
                .maskedUsername(saved.getMaskedUsername())
                .updatedDate(saved.getUpdatedAt().toLocalDate().toString())
                .build();

        return new ResponseDTO<>(ResponseCode.SUCCESS_REGISTER_REVIEW, responseDto);
    }

    public ResponseDTO<ReviewResponseDto> updateReview(Long reviewId, Long userId, ReviewRequestDTO dto) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new GlobalException(ErrorCode.REVIEW_NOT_FOUND));

        if (!review.getUser().getId().equals(userId)) {
            throw new GlobalException(ErrorCode.REVIEW_NOT_AUTHOR);
        }

        review.setRating(dto.getScore());
        review.setContent(dto.getContent());
        reviewRepository.save(review);

        ReviewResponseDto responseDto = ReviewResponseDto.builder()
                .id(review.getId())
                .rating(review.getRating())
                .content(review.getContent())
                .maskedUsername(ReviewResponseDto.maskUsername(review.getUser().getUsername()))
                .updatedDate(review.getUpdatedAt().toLocalDate().toString())
                .build();

        return new ResponseDTO<>(ResponseCode.SUCCESS_UPDATE_REVIEW, responseDto);
    }

}