package com.assessment.api.mortgage.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "interest_rates")
public class InterestRateEntity {
    @Id
    @Column(name = "maturity_period")
    private int maturityPeriod;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "last_updated")
    private Date lastUpdated;

}
