package com.assessment.api.mortgage.service.impl;

import com.assessment.api.mortgage.dta.InterestRateDto;
import com.assessment.api.mortgage.entity.InterestRateEntity;
import com.assessment.api.mortgage.exception.ErrorCode;
import com.assessment.api.mortgage.exception.MortgageServiceException;
import com.assessment.api.mortgage.mapper.InterestRateMapper;
import com.assessment.api.mortgage.repository.InterestRateRepository;
import com.assessment.api.mortgage.service.InterestRateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class InterestRateServiceImpl implements InterestRateService {

    InterestRateRepository interestRateRepository;

    @Override
    public List<InterestRateDto> getAllInterestRate() {
        List<InterestRateEntity> rates = interestRateRepository.findAll();
        if (rates.isEmpty()) {
            throw new MortgageServiceException(ErrorCode.DATA_NOT_FOUND);
        }
        //From entity to dto
        return InterestRateMapper.INTEREST_RATE_MAPPER.fromEntityToDto(rates);
    }

    @Override
    public InterestRateDto getRateForMaturity(int maturity) {
        return interestRateRepository.findByMaturityPeriod(maturity)
                .orElseThrow(() -> new MortgageServiceException(ErrorCode.MISSING_MATURITY_MAPPING));
    }
}
