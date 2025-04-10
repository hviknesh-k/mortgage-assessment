package com.assessment.api.mortgage.repository;

import com.assessment.api.mortgage.dta.InterestRateDto;
import com.assessment.api.mortgage.entity.InterestRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterestRateRepository extends JpaRepository<InterestRateEntity, Long> {
    Optional<InterestRateDto> findByMaturityPeriod(int maturityPeriod);
}
