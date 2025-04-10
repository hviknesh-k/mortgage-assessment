package com.assessment.api.mortgage.dta;

import java.math.BigDecimal;
import java.util.Date;

public record InterestRateDto(int maturityPeriod, BigDecimal interestRate, Date lastUpdated) {
}
