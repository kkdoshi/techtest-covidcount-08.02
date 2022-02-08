package com.collectapi.cucumber.steps;

import com.collectapi.casesinfo.CasesSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import static org.hamcrest.CoreMatchers.equalTo;


public class CovidCasesSteps {

    static ValidatableResponse response;

    @Steps
    CasesSteps casesSteps;

    @When("^user creates a get request by entering the Country Name as \"([^\"]*)\"$")
    public void userCreatesAGetRequestByEnteringTheCountryNameAs(String country) {
        response = casesSteps.getTotalCovidCasesByCountryNames(country);
    }

    @And("^user should be able to see the total cases of covid for UK$")
    public void userShouldBeAbleToSeeTheTotalCasesOfCovidForUK() {
        response.statusCode(200)
                .log().all();
    }

    @Then("^user verifies that the country is \"([^\"]*)\"$")
    public void userVerifiesThatTheCountryIs(String country)  {
        String expectedCases = response.extract()
                .path("result[0].country");
        System.out.println(expectedCases);
        System.out.println(country);
        Assert.assertThat(expectedCases, equalTo(country));
    }
}
