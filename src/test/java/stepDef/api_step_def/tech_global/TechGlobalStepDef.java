package stepDef.api_step_def.tech_global;

import api.pojo_classes.tech_global.Students;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static utils.Hooks.response;
import static utils.Hooks.techGlobalBaseUrl;

public class TechGlobalStepDef {

    private static Logger logger = LogManager.getLogger(TechGlobalStepDef.class);

    Faker faker = new Faker();

    @Given("Create an user with {string}, {string}, email, and {string}, and {string}")
    public void createAnUserWithEmailAndAnd(String expectedFirstName, String expectedLastName, String expectedDob, String expectedURL) {

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
            .when().post(techGlobalBaseUrl + expectedURL)
            .then().log().all()
            .extract().response();


    }
}
