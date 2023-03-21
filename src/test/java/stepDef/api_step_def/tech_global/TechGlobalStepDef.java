package stepDef.api_step_def.techGlobal;

import api.pojo_classes.tech_global.Students;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static utils.Hooks.response;
import static utils.Hooks.techGlobalBaseUrl;

public class TechGlobalStepDef {

    private static Logger logger = LogManager.getLogger(TechGlobalStepDef.class);

    Faker faker = new Faker();

    int actualId;

    @Given("Create an user with {string}, {string}, email, {string} and {string}")
    public void createAnUserWithEmailAnd(String expectedFirstName, String expectedLastName, String expectedDob, String urlPath) {

        Students students = Students
                .builder()
                .firstName(expectedFirstName)
                .lastName(expectedLastName)
                .email(faker.internet().emailAddress())
                .dob(expectedDob)
                .build();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(students)
                .when().post(techGlobalBaseUrl + urlPath)
                .then().log().all()
                .extract().response();
    }

    @And("I make a GET request with {string} with id")
    public void iMakeAGETRequestWithWithId(String urlPath) {

        logger.info("Sending a GET request with student id");

        // 3  actualId = response.jsonPath().getInt("id");
        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .when().get(techGlobalBaseUrl + urlPath + "/" + actualId)
                .then().log().all()
                .extract().response();
    }


    @And("I make a PUT request with following data and with {string}")
    public void iMakeAPUTRequestWithFollowingDataAndWith(String urlPath, Map<String, String> data) {

        Students students = Students
                .builder()
                .firstName(data.get("firstName"))
                .lastName(data.get("lastName"))
                .email(response.jsonPath().getString("email"))
                .dob(response.jsonPath().getString("dob"))
                .build();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(students)
                .when().put(techGlobalBaseUrl + urlPath+ "/"+ actualId)
                .then().log().all()
                .extract().response();
    }

    @And("I make a PATCH request with following data and with {string}")
    public void iMakeAPATCHRequestWithFollowingDataAndWith(String urlPath, Map<String, String> data) {

        Students students = Students
                .builder()
                .firstName(data.get("firstName"))
                .lastName(data.get("lastName"))
                .build();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(students)
                .when().patch(techGlobalBaseUrl + urlPath+ "/"+ actualId)
                .then().log().all()
                .extract().response();



    }

    @And("I make a DELETE request with {string} with id")
    public void iMakeADELETERequestWithWithId(String urlPath) {

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .when().delete(techGlobalBaseUrl + urlPath+ "/" + actualId)
                .then().log().all()
                .extract().response();


    }
}