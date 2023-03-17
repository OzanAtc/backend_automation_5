package api.techglobal_assignment;

import api.pojo_classes.tech_global.CreateAStudent;
import com.github.javafaker.Faker;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ConfigReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class TechGlobal_Api_Assignment {

    Response response;

    static Logger logger = LogManager.getLogger(TechGlobal_Api_Assignment.class);
    Faker faker = new Faker();

    /*
    @BeforeTest
    public void setup() {
        RestAssured.baseURI = ConfigReader.getProperty("TechGlobalBaseURI");
    }

     */
    @Test
    public void addStudent() {

        CreateAStudent createAStudent = CreateAStudent
                .builder()
                .firstName("Thomas")
                .lastName("Larsson")
                .email(faker.internet().emailAddress())
                .dob("2021-05-08")
                .build();

        response =
                         RestAssured
                        .given().log().all()
                        .contentType(ContentType.JSON)
                        .body(createAStudent)
                        .when().post(ConfigReader.getProperty("TechGlobalBaseURI"))
                        .then().log().all()
                        .assertThat().statusCode(200)
                        .time(Matchers.lessThan(2000L))
                        .extract().response();

        String expectedFirstName = createAStudent.getFirstName();
        String actualFirstName = JsonPath.read(response.asString(), "firstName");
        logger.debug("The expected name is " + expectedFirstName + " is matching with " + actualFirstName);

        assertThat(
                "Checking the first name's equality",
                actualFirstName,
                is(expectedFirstName)
                );

        String expectedLastName = createAStudent.getLastName();
        String actualLastName = JsonPath.read(response.asString(), "lastName");
        String expected_DOB = createAStudent.getDob();
        String actual_DOB = JsonPath.read(response.asString(), "dob");

        logger.debug("The expected last name is " + expectedLastName + " is matching with " + actualLastName);

        assertThat(
                "Checking the last name's equality",
                actualLastName,
                is(expectedLastName)
        );

        logger.debug("The expected DOB is " + expected_DOB + " is matching with " + actual_DOB);
        assertThat(
                "Checking dob's equality",
                actual_DOB,
                is(expected_DOB)
        );


    }

}
