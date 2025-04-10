package com.assessment.api.mortgage.service;

import com.assessment.api.mortgage.dta.InterestRateDto;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InterestRateServiceTest {

    @Autowired
    private InterestRateService interestRateService;

    @Test
    public void test_get_all_interest_rates() {
        List<InterestRateDto> interestRates = interestRateService.getAllInterestRate();
        assertEquals(4, interestRates.size());
    }

    @ParameterizedTest
    @MethodSource("provideMaturityPeriod")
    void test_fetch_single_interest_rates(int maturityPeriod, BigDecimal expectedInterestRate) {
        InterestRateDto interestRate = interestRateService.getRateForMaturity(maturityPeriod);
        assertEquals(0, expectedInterestRate.compareTo(interestRate.interestRate()));
    }

    private static Stream<Arguments> provideMaturityPeriod() {
        return Stream.of(
                Arguments.of(10, BigDecimal.valueOf(6.70)),
                Arguments.of(15, BigDecimal.valueOf(5.80)),
                Arguments.of(20, BigDecimal.valueOf(4.00)),
                Arguments.of(30, BigDecimal.valueOf(2.50))
        );
    }



}
