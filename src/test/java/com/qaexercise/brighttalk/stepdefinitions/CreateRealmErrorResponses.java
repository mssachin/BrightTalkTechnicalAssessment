package com.qaexercise.brighttalk.stepdefinitions;

import com.qaexercise.brighttalk.base.TestBase;
import com.qaexercise.brighttalk.pageelements.RealmErrorResponseElements;
import com.qaexercise.brighttalk.pageelements.RealmRequestElements;
import com.qaexercise.brighttalk.requestobjects.RealmCreate;
import com.qaexercise.brighttalk.responseobjects.RealmCreateAndGetResponse;
import com.qaexercise.brighttalk.responseobjects.RealmErrorResponse;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.asserts.SoftAssert;
import sun.security.krb5.Realm;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Sachin on 21/05/2017.
 */
public class CreateRealmErrorResponses extends TestBase {

    private List<RealmRequestElements> originalRealmRequestNoNameTest;
    private List<RealmErrorResponseElements> realmErrorResponseElements;
    private List<RealmRequestElements> originalRealmRequestDuplicateTest;
    private List<RealmRequestElements> duplicateRealmRequest;
    private RealmCreateAndGetResponse realmCreateAndGetResponse;
    private Realm realm;
    private String noNameTestName;
    private String noNameTestDescription;
    private RealmErrorResponse realmErrorResponse;
    private String responseErrorCodeExpected;
    private String responseErrorDescriptionExpected;

    @Given("^I have not supplied realm name in the request as below and make a create call$")
    public void i_have_not_supplied_realm_name_in_the_request_as_below_and_make_a_create_call(DataTable realmDetails) throws Throwable {
            originalRealmRequestNoNameTest = realmDetails.asList(RealmRequestElements.class);
        for (int i = 0; i< originalRealmRequestNoNameTest.size(); i++){
            RealmRequestElements realmRequestElements = originalRealmRequestNoNameTest.get(i);
            noNameTestName = realmRequestElements.getName();
            noNameTestDescription= realmRequestElements.getDescription();
        }
        RealmCreate realm = new RealmCreate();
        realm.setName(noNameTestName);
        realm.setDescription(noNameTestDescription);
        realmErrorResponse = given().log().all()
                .contentType("application/xml; charset=UTF-8")
                .body(realm)
                .when().post("/user/realm").then().statusCode(400).extract()
                .as(RealmErrorResponse.class);
    }


    @Then("^I validate that the error code and description is as below$")
    public void i_validate_that_the_error_code_and_description_is_as_below(DataTable realmErrorDetails) throws Throwable {
        realmErrorResponseElements = realmErrorDetails.asList(RealmErrorResponseElements.class);

        for(int i =0; i<realmErrorResponseElements.size();i++){
            RealmErrorResponseElements tempErrorResponseElement= realmErrorResponseElements.get(i);
            responseErrorCodeExpected = tempErrorResponseElement.getCode();
            responseErrorDescriptionExpected = tempErrorResponseElement.getMessage();
        }

        SoftAssert softAssert = new SoftAssert();
        String actualErrorCode = realmErrorResponse.getCode();
        System.out.println ("Error Code for the call is "+actualErrorCode);
        String actualErrorMessage = realmErrorResponse.getMessage();
        System.out.println ("Error Message for the call is "+actualErrorMessage);


        softAssert.assertEquals(actualErrorCode, responseErrorCodeExpected, "Expected and actual error codes do not match");
        softAssert.assertEquals(actualErrorMessage, responseErrorDescriptionExpected, "Expected and actual error descriptions do not match");
        softAssert.assertAll();


    }

    @Given("^I have made a call to create a realm as below$")
    public void i_have_made_a_call_to_create_a_realm_as_below(DataTable realmDetails) throws Throwable {
        originalRealmRequestDuplicateTest = realmDetails.asList(RealmRequestElements.class);
        String nameForDuplicateTestRequest= null;
        String descriptionForDuplicateTestRequest= null;
        for (int i = 0; i< originalRealmRequestDuplicateTest.size(); i++){
            RealmRequestElements realmRequestElements = originalRealmRequestDuplicateTest.get(i);
            nameForDuplicateTestRequest = realmRequestElements.getName();
            descriptionForDuplicateTestRequest= realmRequestElements.getDescription();
        }
        RealmCreate realm = new RealmCreate();
        realm.setName(nameForDuplicateTestRequest);
        realm.setDescription(descriptionForDuplicateTestRequest);

                realmCreateAndGetResponse =given().log().all()
                .contentType("application/xml; charset=UTF-8")
                .body(realm)
                .when().post("/user/realm").then().statusCode(201).extract()
                .as(RealmCreateAndGetResponse.class);


    }

    @When("^I make a create call with an existing realm name created from above$")
    public void i_make_a_create_call_with_an_existing_realm_name_created_from_above(DataTable duplicateRealmDetails) throws Throwable {
        duplicateRealmRequest = duplicateRealmDetails.asList(RealmRequestElements.class);
        String duplicateRequestName= null;
        String duplicateRequestDescription= null;
        for (int i = 0; i< duplicateRealmRequest.size(); i++){
            RealmRequestElements realmRequestElements = duplicateRealmRequest.get(i);
            duplicateRequestName = realmRequestElements.getName();
            duplicateRequestDescription= realmRequestElements.getDescription();
        }
        RealmCreate realm = new RealmCreate();
        realm.setName(duplicateRequestName);
        realm.setDescription(duplicateRequestDescription);

        realmErrorResponse = given().log().all()
                .contentType("application/xml; charset=UTF-8")
                .body(realm)
                .when().post("/user/realm").then().statusCode(400).extract()
                .as(RealmErrorResponse.class);

    }


    @Given("^I have supplied more than allowed chars in realm name and make a post call$")
    public void i_have_supplied_more_than_allowed_chars_in_realm_name(DataTable realmDetails) throws Throwable {

        List<RealmRequestElements> realmNameIllegal = realmDetails.asList(RealmRequestElements.class);
        String longName= null;
        String correctDescription= null;
        for (int i = 0; i< realmNameIllegal.size(); i++){
            RealmRequestElements realmRequestElements = realmNameIllegal.get(i);
            longName = realmRequestElements.getName();
            correctDescription= realmRequestElements.getDescription();
        }
        RealmCreate realm = new RealmCreate();
        realm.setName(longName);
        realm.setDescription(correctDescription);

        realmErrorResponse = given().log().all()
                .contentType("application/xml; charset=UTF-8")
                .body(realm)
                .when().post("/user/realm").then().statusCode(400).extract()
                .as(RealmErrorResponse.class);

    }


    @Given("^I have supplied excess characters in realm description and make a post call$")
    public void i_have_supplied_more_than_allowed_chars_in_realm_description(DataTable realmDetails) throws Throwable {

        List<RealmRequestElements> realmNameIllegal = realmDetails.asList(RealmRequestElements.class);
        String correctName= null;
        String longDescription= null;
        for (int i = 0; i< realmNameIllegal.size(); i++){
            RealmRequestElements realmRequestElements = realmNameIllegal.get(i);
            correctName = realmRequestElements.getName();
            longDescription= realmRequestElements.getDescription();
        }
        RealmCreate realm = new RealmCreate();
        realm.setName(correctName);
        realm.setDescription(longDescription);

        realmErrorResponse = given().log().all()
                .contentType("application/xml; charset=UTF-8")
                .body(realm)
                .when().post("/user/realm").then().statusCode(400).extract()
                .as(RealmErrorResponse.class);

    }


    @And("^I clean up by deleting the realm by id$")
    public void i_clean_up_by_deleting_the_realm() throws Throwable {

                given()
                .when()
                .delete("/user/realm/"+realmCreateAndGetResponse.getId())
                .then()
                .statusCode(204  );


    }

}
