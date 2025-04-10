package com.assessment.api.mortgage.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "mortgage")
@ConfigurationPropertiesScan
public class MortgagePropertyConfiguration {
    int incomeThreshold;
}
