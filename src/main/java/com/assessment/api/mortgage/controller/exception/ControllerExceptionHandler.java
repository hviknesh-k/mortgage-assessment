package com.assessment.api.mortgage.controller.exception;

import com.assessment.api.mortgage.exception.ErrorCode;
import com.assessment.api.mortgage.exception.MortgageServiceException;
import com.assessment.api.mortgage.exception.MortgageValidationException;
import com.assessment.api.mortgage.model.Error;
import com.assessment.api.mortgage.model.MortgageResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MortgageServiceException.class)
    public ResponseEntity<Error> handleApplicationException(MortgageServiceException exception) {
        log.error("MortgageServiceException : {}", exception.getErrorCode().getMessage());
        return buildErrorResponse(exception.getErrorCode());
    }

    @ExceptionHandler(MortgageValidationException.class)
    public ResponseEntity<MortgageResponse> handleMortgageValidationException(MortgageValidationException exception) {
        log.error("MortgageValidationException : {}", exception.getErrorCode().getMessage());
        return buildMortgageIneligibleResponse(exception.getErrorCode());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Error> constraintViolationException(ConstraintViolationException exception) {
        log.error("Validation Exception : {}", exception.getMessage());
        return buildErrorResponse(ErrorCode.INVALID_INPUT);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("Invalid Method Argument Exception : {}", exception.getMessage());
        return buildErrorResponse(ErrorCode.INVALID_INPUT);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleUncaughtException(Exception exception) {
        log.error("Uncaught Exception : {}", exception.getMessage());
        return buildErrorResponse(ErrorCode.SERVER_ERROR);
    }

    private ResponseEntity<Error> buildErrorResponse(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus().value())
                .body(getErrorBuilder(errorCode).build());
    }

    private ResponseEntity<MortgageResponse> buildMortgageIneligibleResponse(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus().value())
                .body(MortgageResponse.builder().eligible(false).build());
    }

    private Error.ErrorBuilder getErrorBuilder(ErrorCode errorCode) {
        return Error.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage());
    }
}
