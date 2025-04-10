package com.assessment.api.mortgage.service.impl;

import com.assessment.api.mortgage.configuration.MortgagePropertyConfiguration;
import com.assessment.api.mortgage.dta.InterestRateDto;
import com.assessment.api.mortgage.exception.ErrorCode;
import com.assessment.api.mortgage.exception.MortgageServiceException;
import com.assessment.api.mortgage.exception.MortgageValidationException;
import com.assessment.api.mortgage.model.MortgageRequest;
import com.assessment.api.mortgage.service.InterestRateService;
import com.assessment.api.mortgage.service.MortgageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
@RequiredArgsConstructor
public class MortgageServiceImpl implements MortgageService {

    private static final int MONTHS_IN_YEAR = 12;
    private static final int ROUND_OFF_12 = 12;
    private final InterestRateService interestRateService;
    private final MortgagePropertyConfiguration configuration;

    /**
     * Method to calculate eligibility and monthly costs for mortgage
     *
     * @param request - Incoming API request
     * @return Calculated monthly costs
     */
    @Override
    public BigDecimal getMonthlyCosts(final MortgageRequest request) {
        performValidation(request);
        InterestRateDto interestRateEntity = interestRateService.getRateForMaturity(request.getMaturityPeriod());
        if (null == interestRateEntity.interestRate()) {
            log.error("No interest rates configured for maturity {}", request.getMaturityPeriod());
            throw new MortgageServiceException(ErrorCode.SERVER_ERROR);
        }
        BigDecimal interestRatePerMonth = interestRateEntity.interestRate().divide(BigDecimal.valueOf(100), ROUND_OFF_12, RoundingMode.HALF_UP);
        BigDecimal numerator = ratePerMonth(interestRatePerMonth).multiply(calculateRatePower(interestRatePerMonth, request.getMaturityPeriod()));
        BigDecimal denominator = calculateRatePower(interestRatePerMonth, request.getMaturityPeriod()).subtract(BigDecimal.ONE);
        BigDecimal ratio = numerator.divide(denominator, ROUND_OFF_12, RoundingMode.HALF_UP);
        BigDecimal monthlyCosts = request.getLoanValue().multiply(ratio).setScale(2, RoundingMode.HALF_UP);
        return monthlyCosts;
    }

    private static BigDecimal ratePerMonth(final BigDecimal rate) {
        return rate.divide(BigDecimal.valueOf(MONTHS_IN_YEAR), ROUND_OFF_12, RoundingMode.HALF_UP);
    }

    private static BigDecimal calculateRatePower(final BigDecimal rate, final int maturityPeriod) {
        BigDecimal onePlus = BigDecimal.ONE.add(ratePerMonth(rate));
        return onePlus.pow(maturityPeriod * MONTHS_IN_YEAR);
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
