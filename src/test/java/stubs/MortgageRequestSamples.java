package stubs;

import com.assessment.api.mortgage.model.MortgageRequest;
import lombok.SneakyThrows;

import static stubs.FileUtils.getJsonFromFile;

public class MortgageRequestSamples {

    public static final MortgageRequest MORTGAGE_REQUEST_VALID_MP_10 = getMortgageRequestFromFile("payload/mortgageRequest/validRequest.json");
    public static final MortgageRequest MORTGAGE_REQUEST_VALID_MP_15= getMortgageRequestFromFile("payload/mortgageRequest/validRequest_mp_15.json");
    public static final MortgageRequest MORTGAGE_REQUEST_VALID_MP_30 = getMortgageRequestFromFile("payload/mortgageRequest/validRequest_mp_30.json");
    public static final MortgageRequest MORTGAGE_REQUEST_VALID_MP_11 = getMortgageRequestFromFile("payload/mortgageRequest/validRequest_mp_11.json");
    public static final MortgageRequest MORTGAGE_REQUEST_NOT_ELIGIBLE_LOW_INCOME = getMortgageRequestFromFile("payload/mortgageRequest/invalidIncome.json");
    public static final MortgageRequest MORTGAGE_REQUEST_NOT_ELIGIBLE_HIGH_LOAN = getMortgageRequestFromFile("payload/mortgageRequest/validRequest_loan_higher_than_home.json");
    public static final MortgageRequest MORTGAGE_REQUEST_NOT_ELIGIBLE_INVALID_LOAN_AMOUNT = getMortgageRequestFromFile("payload/mortgageRequest/invalidLoanAmount.json");


    public static final String MORTGAGE_JSON_VALID_REQUEST = getJsonFromFile("payload/mortgageRequest/validRequest.json");
    public static final String MORTGAGE_JSON_NOT_ELIGIBLE_LOW_INCOME = getJsonFromFile("payload/mortgageRequest/validRequest_low_income.json");
    public static final String MORTGAGE_JSON_NOT_ELIGIBLE_HIGH_LOAN = getJsonFromFile("payload/mortgageRequest/validRequest_loan_higher_than_home.json");
    public static final String MORTGAGE_JSON_NOT_ELIGIBLE_INVALID_LOAN_AMOUNT = getJsonFromFile("payload/mortgageRequest/invalidLoanAmount.json");
    public static final String MORTGAGE_JSON_INVALID_MP = getJsonFromFile("payload/mortgageRequest/invalidMaturityPeriod.json");

    @SneakyThrows
    private static MortgageRequest getMortgageRequestFromFile(final String fileName) {
        return ObjectMapperUtil.defaultMapper().readValue(getJsonFromFile(fileName), MortgageRequest.class);
    }
}
