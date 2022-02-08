package com.collectapi.casesinfo;


import com.collectapi.constants.EndPoints;
import com.collectapi.utils.PropertyReader;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class CasesSteps {

    String apiKey = PropertyReader.getInstance().getProperty("apikey");

    @Step("")
    public ValidatableResponse getTotalCovidCasesByCountryNames(String country){
        return SerenityRest.given().log().all()
                .header("authorization", apiKey)
                .contentType(ContentType.JSON)
                .queryParams("country", country)
                .when()
                .get(EndPoints.GET_COVID_DATA_FOR_ALL_COUNTRIES)
                .then();
    }
}
