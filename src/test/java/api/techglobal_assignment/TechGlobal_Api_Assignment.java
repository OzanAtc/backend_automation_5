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

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = ConfigReader.getProperty("TechGlobalBaseURI");
    }


    @Test
    public void addStudent() {

        CreateAStudent createAStudent = CreateAStudent
                .builder()
                .firstName("Thomas")
                .lastName("Larsson")
                .email(faker.internet().emailAddress())
                .dob("2020-05-08")
                .build();

        response =
                         RestAssured
                        .given().log().all()
                        .contentType(ContentType.JSON)
                        .body(createAStudent)
                        .when().post(ConfigReader.getProperty("TechGlobalBaseURI"))
                        .then().log().all()
                        .assertThat().statusCode(200)
                        .time(Matchers.lessThan(4000L))
                        .body("firstName", equalTo("Thomas"))
                        .extract().response();

        String expectedFirstName = createAStudent.getFirstName();
        String actualFirstName = JsonPath.read(response.asString(), "firstName");
        logger.debug("The expected name is " + expectedFirstName + " is matching with " + actualFirstName);

        assertThat(
                "A new user is created",
                actualFirstName,
                is(expectedFirstName)
                );




    }

}
