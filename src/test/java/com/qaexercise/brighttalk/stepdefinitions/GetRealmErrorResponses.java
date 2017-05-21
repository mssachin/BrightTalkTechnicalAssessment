package com.qaexercise.brighttalk.stepdefinitions;

import com.qaexercise.brighttalk.base.TestBase;
import com.qaexercise.brighttalk.responseobjects.RealmErrorResponse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.asserts.SoftAssert;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Sachin on 21/05/2017.
 */
public class GetRealmErrorResponses extends TestBase {

    private RealmErrorResponse realmErrorResponse;
    private String actualErrorCode;
    private String actualErrorDescription;


    @Given("^I have made a get realm call using invalid realm \"([^\"]*)\"$")
    public void i_have_made_a_get_realm_call_using_invalid_realm(String id) throws Throwable {
        realmErrorResponse = given()
                .when()
                .get("/user/realm/" + id).then().statusCode(400).extract()
                .as(RealmErrorResponse.class);
    }

    @When("^I extract the response for the get call$")
    public void i_get_a_response_for_the_get_call() throws Throwable {
        actualErrorCode = realmErrorResponse.getCode();
        actualErrorDescription = realmErrorResponse.getMessage();
    }

    @Then("^I validate that the \"([^\"]*)\" and \"([^\"]*)\" is valid$")
    public void i_validate_that_the_and_says_realm_id_is_invalid(String expectedErrorCode, String expectedErrorDescription) throws Throwable {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualErrorCode, expectedErrorCode, "Expected and actual error code do not match");
        softAssert.assertEquals(actualErrorDescription, expectedErrorDescription, "Expected and actual error description do not match");
        softAssert.assertAll();

    }


    @Given("^I have made a get call with an \"([^\"]*)\" that does not exist ensured by deleting it$")
    public void i_have_made_a_get_call_with_an_that_does_not_exist(String id) throws Throwable {
        given()
                .when()
                .delete("/user/realm/" + id)
                .then()
                .statusCode(204);

        realmErrorResponse = given()
                .when()
                .get("/user/realm/" + id).then().statusCode(404).extract()
                .as(RealmErrorResponse.class);
    }


}