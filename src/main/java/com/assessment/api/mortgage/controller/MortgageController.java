package com.assessment.api.mortgage.controller;


import com.assessment.api.mortgage.configuration.MortgagePropertyConfiguration;
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
        BigDecimal monthlyCosts = mortgageService.getMonthlyCosts(apiRequest);
        return ResponseEntity.ok()
                .body(MortgageResponse.builder().
                        eligible(true)
                        .monthlyCosts(monthlyCosts)
                        .build());
    }
}
