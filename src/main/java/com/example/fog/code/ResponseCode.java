package com.example.fog.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ResponseCode {
    SUCCESS_REGISTER(HttpStatus.CREATED, "회원가입을 성공했습니다."),
    SUCCESS_LOGIN(HttpStatus.OK,"로그인을 성공했습니다."),

    SUCCESS_REGISTER_REVIEW(HttpStatus.OK,"리뷰 등록을 성공했습니다."),
    SUCCESS_UPDATE_REVIEW(HttpStatus.OK,"리뷰 수정을 성공했습니다."),
    SUCCESS_GET_PERFUME(HttpStatus.OK, "향수 정보 및 리뷰 조회를 성공했습니다."),
    SUCCESS_GET_MY_REVIEWS(HttpStatus.OK, "내가 작성한 리뷰 조회 성공")
    ;
    private final HttpStatus status;
    private final String message;
}