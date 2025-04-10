package com.assessment.api.mortgage.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode
{

    INVALID_INPUT("ERR_001", "Invalid input provided", HttpStatus.BAD_REQUEST),
    DATA_NOT_FOUND("ERR_002", "No data found", HttpStatus.NOT_FOUND),
    SERVER_ERROR("ERR_003", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    MISSING_MATURITY_MAPPING("ERR_004", "No data for provided maturity period", HttpStatus.NOT_FOUND),
    HIGH_MORTGAGE_REQUEST("ERR_005", "Loan amount is higher than value of home", HttpStatus.BAD_REQUEST),
    INSUFFICIENT_INCOME("ERR_006", "Income criteria is not satisfied", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

}
