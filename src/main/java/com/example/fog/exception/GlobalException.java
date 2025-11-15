package com.example.fog.exception;

import com.example.fog.code.ErrorCode;
import com.example.fog.dto.response.ErrorResponseDTO;

public class GlobalException extends RuntimeException {
    private ErrorCode code;

    public GlobalException(ErrorCode code) {
        this.code = code;
    }

    public ErrorResponseDTO getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}
