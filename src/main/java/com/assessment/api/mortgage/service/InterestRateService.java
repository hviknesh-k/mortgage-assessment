package com.assessment.api.mortgage.service;

import com.assessment.api.mortgage.dta.InterestRateDto;

import java.util.List;

public interface InterestRateService {
    List<InterestRateDto> getAllInterestRate();
    InterestRateDto getRateForMaturity(int interestRate);
}
