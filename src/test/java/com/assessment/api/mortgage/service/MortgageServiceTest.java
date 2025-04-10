package com.assessment.api.mortgage.service;

import com.assessment.api.mortgage.exception.ErrorCode;
import com.assessment.api.mortgage.exception.MortgageServiceException;
import com.assessment.api.mortgage.exception.MortgageValidationException;
import com.assessment.api.mortgage.model.MortgageRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.assessment.api.mortgage.exception.ErrorCode.MISSING_MATURITY_MAPPING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static stubs.MortgageRequestSamples.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
class MortgageServiceTest {

    @Autowired
    private MortgageService mortgageService;

    @Test
    @DisplayName("MortgageService : Verify when invalid maturity period is provided")
    void test_mortgage_details_exception_scenario() {
        MortgageServiceException exception = assertThrows(MortgageServiceException.class, () -> mortgageService.getMonthlyCosts(MORTGAGE_REQUEST_VALID_MP_11));
        assertEquals(MISSING_MATURITY_MAPPING.getCode(), exception.getErrorCode().getCode());
    }

    @ParameterizedTest
    @MethodSource("provideMaturityRequest")
    @DisplayName("MortgageService : Verify monthyl costs calcualtion for provided maturity periods")
    void test_fetch_eligible_mortgage_details_scenario(MortgageRequest request, boolean eligibility, BigDecimal expectedMonthlyCosts) {
        BigDecimal monthlyCosts = mortgageService.getMonthlyCosts(request);
        assertEquals(0, expectedMonthlyCosts.compareTo(monthlyCosts));
    }

    private static Stream<Arguments> provideMaturityRequest() {
        return Stream.of(
                Arguments.of(MORTGAGE_REQUEST_VALID_MP_10, true, BigDecimal.valueOf(3437.05)),
                Arguments.of(MORTGAGE_REQUEST_VALID_MP_15, true, BigDecimal.valueOf(2499.27)),
                Arguments.of(MORTGAGE_REQUEST_VALID_MP_30, true, BigDecimal.valueOf(1185.36)));
    }

    @ParameterizedTest
    @MethodSource("provideValidationCases")
    @DisplayName("MortgageService : Verify business validation and invalid inputs")
    void test_mortgage_details_exception_scenario(String testCase, MortgageRequest request, String expectedErrorCode) {
        log.info("Case {} ", testCase);
        MortgageValidationException exception = assertThrows(MortgageValidationException.class, () -> mortgageService.getMonthlyCosts(request));
        assertEquals(expectedErrorCode, exception.getErrorCode().getCode());
    }

    private static Stream<Arguments> provideValidationCases() {
        return Stream.of(
                Arguments.of("Income is not sufficient" ,MORTGAGE_REQUEST_NOT_ELIGIBLE_LOW_INCOME, ErrorCode.INSUFFICIENT_INCOME.getCode()),
                Arguments.of("Request amount higher than home value", MORTGAGE_REQUEST_NOT_ELIGIBLE_HIGH_LOAN, ErrorCode.HIGH_MORTGAGE_REQUEST.getCode()));
    }
}
