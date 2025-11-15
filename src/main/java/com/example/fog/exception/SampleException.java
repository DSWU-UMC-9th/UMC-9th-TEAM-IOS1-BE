package com.example.fog.exception;

import com.example.fog.code.ErrorCode;

public class SampleException extends GlobalException{
    public SampleException(ErrorCode code) {
        super(code);
    }
}
