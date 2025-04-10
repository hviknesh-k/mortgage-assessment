package com.assessment.api.mortgage.mapper;

import com.assessment.api.mortgage.dta.InterestRateDto;
import com.assessment.api.mortgage.entity.InterestRateEntity;
import com.assessment.api.mortgage.model.InterestRatesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InterestRateMapper {

    InterestRateMapper INTEREST_RATE_MAPPER = Mappers.getMapper(InterestRateMapper.class);

    List<InterestRateDto> fromEntityToDto(final List<InterestRateEntity> interestRates);
    List<InterestRatesResponse> fromDtoToModel(final List<InterestRateDto> interestRate);


}
