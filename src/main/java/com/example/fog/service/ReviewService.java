package com.example.fog.service;

import com.example.fog.code.ErrorCode;
import com.example.fog.code.ResponseCode;
import com.example.fog.dto.reivew.MyReviewResponseDto;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
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

    @Transactional(readOnly = true)
    public ResponseDTO<List<MyReviewResponseDto>> getMyReviews(Long userId, String order) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

        Sort sort = order.equals("old")
                ? Sort.by(Sort.Direction.ASC, "createdAt")
                : Sort.by(Sort.Direction.DESC, "createdAt");

        List<Review> reviews = reviewRepository.findByUser(user, sort);

        List<MyReviewResponseDto> result = reviews.stream()
                .map(r -> MyReviewResponseDto.builder()
                        .id(r.getId())
                        .rating(r.getRating())
                        .content(r.getContent())
                        .maskedUsername(r.getMaskedUsername())
                        .updatedDate(r.getUpdatedAt().toLocalDate().toString())
                        .userId(r.getUser().getId())
                        .build()
                ).toList();

        return new ResponseDTO<>(ResponseCode.SUCCESS_GET_MY_REVIEWS, result);
    }



}