package com.assessment.api.mortgage.controller;

import com.assessment.api.mortgage.mapper.InterestRateMapper;
import com.assessment.api.mortgage.model.InterestRatesResponse;
import com.assessment.api.mortgage.service.InterestRateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class InterestRateController {

    @Autowired
    InterestRateService interestRateService;

    /*
    API endpoint to fetch all existing interest rates from database
     */
    @GetMapping("/interest-rates")
    public ResponseEntity<List<InterestRatesResponse>> getAllInterestRates() {
        List<InterestRatesResponse> interestRates = InterestRateMapper.INTEREST_RATE_MAPPER.fromDtoToModel(interestRateService.getAllInterestRate());
        return ResponseEntity.ok().body(interestRates);
    }
}
