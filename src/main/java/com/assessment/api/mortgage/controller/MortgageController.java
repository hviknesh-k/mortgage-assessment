package com.assessment.api.mortgage.controller;


import com.assessment.api.mortgage.configuration.MortgagePropertyConfiguration;
import com.assessment.api.mortgage.exception.ErrorCode;
import com.assessment.api.mortgage.exception.MortgageServiceException;
import com.assessment.api.mortgage.exception.MortgageValidationException;
import com.assessment.api.mortgage.model.MortgageRequest;
import com.assessment.api.mortgage.model.MortgageResponse;
import com.assessment.api.mortgage.service.MortgageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MortgageController {

    MortgageService mortgageService;
    private final MortgagePropertyConfiguration configuration;

    /*
    API endpoint to calculate monthly costs and eligibility for mortgage
     */
    @PostMapping("/mortgage-check")
    ResponseEntity<MortgageResponse> getMortgageDetails(@Validated @RequestBody MortgageRequest apiRequest) {
        performValidation(apiRequest);
        BigDecimal monthlyCosts = mortgageService.getMonthlyCosts(apiRequest);
        return ResponseEntity.ok()
                .body(MortgageResponse.builder().
                        eligible(true)
                        .monthlyCosts(monthlyCosts)
                        .build());
    }

    /**
     * Method to perform additional business validations on request
     * @param request - Incoming api request
     */
    private void performValidation(MortgageRequest request) {
        if (request.getLoanValue().compareTo(request.getHomeValue()) > 0) {
            throw new MortgageValidationException(ErrorCode.HIGH_MORTGAGE_REQUEST);
        }
        if (request.getLoanValue()
                .compareTo(request.getIncome().multiply(BigDecimal.valueOf(configuration.getIncomeThreshold()))) > 0) {
            throw new MortgageValidationException(ErrorCode.INSUFFICIENT_INCOME);
        }
    }

}
