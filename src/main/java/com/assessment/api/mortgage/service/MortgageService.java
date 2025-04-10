package com.assessment.api.mortgage.service;

import com.assessment.api.mortgage.model.MortgageRequest;

import java.math.BigDecimal;

public interface MortgageService {

    BigDecimal getMonthlyCosts(MortgageRequest request);
}
