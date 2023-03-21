package stepDef.api_step_def.go_rest;

import api.pojo_classes.go_rest.CreateGoRestUserWithLombok;
import com.github.javafaker.Faker;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ConfigReader;
import utils.Hooks;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static utils.Hooks.*;


public class GoRestStepDef {

    private static Logger logger = LogManager.getLogger(GoRestStepDef.class);
    Faker faker = new Faker();

    int actualId;

    @Given("Create an user with {string}, email, {string}, {string}, {string}")
    public void createAnUserWithEmail(String expectedName, String expectedGender, String expectedStatus, String urlPath) {

        CreateGoRestUserWithLombok createGoRestUserWithLombok = CreateGoRestUserWithLombok
                .builder()
                .name(expectedName)
                .gender(expectedGender)
                .email(faker.internet().emailAddress())
                .status(expectedStatus)
                .build();

        response = RestAssured
                .given().log().all()
                .header("Authorization", ConfigReader.getProperty(token))
                .contentType(ContentType.JSON)
                .body(createGoRestUserWithLombok)
                .when().post(goRestBaseUrl + urlPath)
                .then().log().all()
                .extract().response();


    }

    @When("I make a GET request with the {string} with id")
    public void iMakeAGETRequestWithTheWithId(String urlPath) {



                         response = RestAssured
                        .given().log().all()
                        .header("Authorization", ConfigReader.getProperty(token))
                        .contentType(ContentType.JSON)
                        .when().get(goRestBaseUrl + urlPath + "/" + actualId)
                        .then().log().all()
                        .extract().response();
    }

    @When("I make a PUT request with following data with {string}")
    public void iMakeAPUTRequestWithFollowingDataWith(String urlPath, Map<String, String> tata) {

        CreateGoRestUserWithLombok createGoRestUserWithLombok = CreateGoRestUserWithLombok
                .builder()
                .name(tata.get("name"))
                .gender(tata.get("gender"))
                .email(response.jsonPath().getString("email"))
                .status(response.jsonPath().getString("status"))
                .build();


        response =
                RestAssured
                        .given().log().all()
                        .header("Authorization", ConfigReader.getProperty(token))
                        .contentType(ContentType.JSON)
                        .body(createGoRestUserWithLombok)
                        .when().put(goRestBaseUrl + urlPath + "/" + actualId)
                        .then().log().all()
                        .extract().response();


    }

    @When("I make a PATCH request with following data {string} with id")
    public void iMakeAPATCHRequestWithFollowingDataWithId(String urlPath, Map<String, String> data) {

        CreateGoRestUserWithLombok createGoRestUserWithLombok = CreateGoRestUserWithLombok
                .builder()
                .name(data.get("name"))
                .build();


        response =
                RestAssured
                        .given().log().all()
                        .header("Authorization",ConfigReader.getProperty(token))
                        .contentType(ContentType.JSON)
                        .body(createGoRestUserWithLombok)
                        .when().patch(goRestBaseUrl + urlPath + "/" + actualId)
                        .then().log().all()
                        .extract().response();


    }

    @When("I make a DELETE request with the {string} with id")
    public void iMakeADELETERequestWithTheWithId(String urlPath) {

        response =
                RestAssured
                        .given().log().all()
                        .header("Authorization",ConfigReader.getProperty(token))
                        .contentType(ContentType.JSON)
                        .when().delete(goRestBaseUrl + urlPath + "/" + actualId)
                        .then().log().all()
                        .extract().response();
    }
}
