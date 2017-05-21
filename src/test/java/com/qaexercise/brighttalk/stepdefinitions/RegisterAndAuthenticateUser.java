package com.qaexercise.brighttalk.stepdefinitions;

import com.qaexercise.brighttalk.base.TestBase;
import com.qaexercise.brighttalk.requestobjects.RealmCreate;
import com.qaexercise.brighttalk.responseobjects.RealmCreateAndGetResponse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.testng.asserts.SoftAssert;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Sachin on 20/05/2017.
 */
public class RegisterAndAuthenticateUser extends TestBase {

    private String expectedRealmName;
    private String expectedRealmDescription;
    private RealmCreateAndGetResponse realmCreateAndGetResponse;
    private String realmIdResponseFromPost;
    private String realmNameResponse;
    private String realmDescriptionResponse;
    private String realmKeyResponseFromPost;
    private String realmIdResponseFromGet;
    private String realmKeyResponseFromGet;




    @Given("^when I post a request to register and authenticate request using the \"([^\"]*)\" and \"([^\"]*)\"$")
    public void when_I_post_a_request_to_register_and_authenticate_request_using_the_and(String userNameValue, String userDescriptionValue) throws Throwable {
        expectedRealmName = userNameValue;
        expectedRealmDescription = userDescriptionValue;
        RealmCreate realm = new RealmCreate();
        realm.setName(userNameValue);
        realm.setDescription(userDescriptionValue);

        realmCreateAndGetResponse =given().log().all()
        .contentType("application/xml; charset=UTF-8")
        .body(realm)
        .when().post("/user/realm").then().statusCode(201).extract()
        .as(RealmCreateAndGetResponse.class);



    }


    @When("^I get a response for the create request$")
    public void i_get_a_response_for_the_create_request() throws Throwable {
        realmIdResponseFromPost = realmCreateAndGetResponse.getId();
        realmNameResponse = realmCreateAndGetResponse.getName();
        realmDescriptionResponse= realmCreateAndGetResponse.getDescription();
        realmKeyResponseFromPost= realmCreateAndGetResponse.getKey();
    }

    @And("^I validate the response is echoed with id and key$")
    public void i_validate_the_response_is_echoed_with_id_and_key() throws Throwable {

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(realmNameResponse, expectedRealmName,"Expected realm name and that echoed in realm create response does not match" );
        softAssert.assertEquals(realmDescriptionResponse, expectedRealmDescription, "Expected realm description and that echoed in realm create response does not match");
        softAssert.assertAll();


    }

    @And("^I get the realm based on the realm id created from above$")
    public void i_get_the_realm_based_on_the_realm_id_create_from_above() throws Throwable {
        realmCreateAndGetResponse =given()
        .when()
        .get("/user/realm/"+realmIdResponseFromPost).then().statusCode(200).extract()
        .as(RealmCreateAndGetResponse.class);


    }

    @And("^I validate the values of the realm$")
    public void i_validate_the_values_of_the_realm() throws Throwable {
        realmIdResponseFromGet = realmCreateAndGetResponse.getId();
        realmNameResponse = realmCreateAndGetResponse.getName();
        realmDescriptionResponse= realmCreateAndGetResponse.getDescription();
        realmKeyResponseFromGet= realmCreateAndGetResponse.getKey();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(realmIdResponseFromGet, realmIdResponseFromPost, "Realm id from post and get do not match" );
        softAssert.assertEquals(realmNameResponse, expectedRealmName,"Expected realm name and that echoed in realm get response do not match" );
        softAssert.assertEquals(realmDescriptionResponse, expectedRealmDescription, "Expected realm description and that echoed in realm get response do not match");
        softAssert.assertEquals(realmKeyResponseFromGet, realmKeyResponseFromPost, "Realm Key from post and get do not match");
        softAssert.assertAll();
    }

    @And("^I clean up by deleting the realm$")
    public void i_clean_up_by_deleting_the_realm() throws Throwable {

        given()
        .when()
        .delete("/user/realm/"+realmIdResponseFromPost)
        .then()
        .statusCode(204);


    }

}
