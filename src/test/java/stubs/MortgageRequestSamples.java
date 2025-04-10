package stubs;

import com.assessment.api.mortgage.model.MortgageRequest;
import lombok.SneakyThrows;

import static stubs.FileUtils.getJsonFromFile;

public class MortgageRequestSamples {

    public static final MortgageRequest MORTGAGE_REQUEST_VALID_MP_10 = getMortgageRequestFromFile("payload/mortgageRequest/validRequest.json");
    public static final MortgageRequest MORTGAGE_REQUEST_VALID_MP_15= getMortgageRequestFromFile("payload/mortgageRequest/validRequest_mp_15.json");
    public static final MortgageRequest MORTGAGE_REQUEST_VALID_MP_30 = getMortgageRequestFromFile("payload/mortgageRequest/validRequest_mp_30.json");
    public static final MortgageRequest MORTGAGE_REQUEST_VALID_MP_11 = getMortgageRequestFromFile("payload/mortgageRequest/validRequest_mp_11.json");
    public static final MortgageRequest MORTGAGE_REQUEST_NOT_ELIGIBLE = getMortgageRequestFromFile("payload/mortgageRequest/validRequest_not_eligible_for_mortgage.json");

    public static final String MORTGAGE_JSON_VALID_REQUEST = getJsonFromFile("payload/mortgageRequest/validRequest.json");
    public static final String MORTGAGE_JSON_NOT_ELIGIBLE = getJsonFromFile("payload/mortgageRequest/validRequest_not_eligible_for_mortgage.json");
    public static final String MORTGAGE_JSON_INVALID_MP = getJsonFromFile("payload/mortgageRequest/invalidMaturityPeriod.json");

    @SneakyThrows
    private static MortgageRequest getMortgageRequestFromFile(final String fileName) {
        return ObjectMapperUtil.defaultMapper().readValue(getJsonFromFile(fileName), MortgageRequest.class);
    }
}
