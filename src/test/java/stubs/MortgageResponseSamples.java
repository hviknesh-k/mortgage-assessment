package stubs;

import com.assessment.api.mortgage.model.MortgageRequest;
import lombok.SneakyThrows;

import static stubs.FileUtils.getJsonFromFile;

public class MortgageResponseSamples {

    public static final String MORTGAGE_ELIGIBLE_RESPONSE = getJsonFromFile("payload/mortgageResponse/eligible_response.json");
    public static final String MORTGAGE_INELIGIBLE_RESPONSE = getJsonFromFile("payload/mortgageResponse/ineligible_response.json");
    public static final String MORTGAGE_ERROR_RESPONSE = getJsonFromFile("payload/mortgageResponse/error_scenario_invalid_mp.json");


    @SneakyThrows
    private static MortgageRequest getMortgageRequestFromFile(final String fileName) {
        return ObjectMapperUtil.defaultMapper().readValue(getJsonFromFile(fileName), MortgageRequest.class);
    }
}
