package com.assessment.api.mortgage.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class MortgageValidationException extends RuntimeException{

    private final ErrorCode errorCode;
    public MortgageValidationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}