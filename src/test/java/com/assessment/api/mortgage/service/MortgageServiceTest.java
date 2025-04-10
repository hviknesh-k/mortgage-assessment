package com.assessment.api.mortgage.service;

import com.assessment.api.mortgage.exception.MortgageServiceException;
import com.assessment.api.mortgage.model.MortgageRequest;
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
import static org.junit.jupiter.api.Assertions.*;
import static stubs.MortgageRequestSamples.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class MortgageServiceTest {

    @Autowired
    private MortgageService mortgageService;

    @Test
    void test_mortgage_details_exception_scenario() {
        MortgageServiceException exception = assertThrows(MortgageServiceException.class, () -> mortgageService.getMonthlyCosts(MORTGAGE_REQUEST_VALID_MP_11));
        assertEquals(MISSING_MATURITY_MAPPING.getCode(), exception.getErrorCode().getCode());
    }

    @ParameterizedTest
    @MethodSource("provideMaturityRequest")
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



}
