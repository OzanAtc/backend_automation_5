package api.tdd;

import api.pojo_classes.go_rest.CreateGoRestUser;
import api.pojo_classes.go_rest.UpdateGoRestUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ConfigReader;
import org.hamcrest.Matchers;

import static org.hamcrest.core.IsEqual.equalTo;

public class GoRest {


    Response response;
    /**
     * ObjectMapper is a class coming from fasterxml to convert Java Object to JSON
     */


    ObjectMapper objectMapper = new ObjectMapper();
    Faker faker = new Faker();

    int goRestID;

    @BeforeTest
    public void beforeTest() {
        System.out.println("Starting the API test");
        // By having RestAssured URI set implicitly in to rest assured
        // We just add path to the post call
        RestAssured.baseURI = ConfigReader.getProperty("GoRestBaseURI");

    }

    @Test
    public void goRestCrud() throws JsonProcessingException {

        // Creating a POJO (Bean) object  - it's a bean object not Pojo, tell them in interview
        CreateGoRestUser createGoRestUser = new CreateGoRestUser();

        // assigned the values to the attributes.
        createGoRestUser.setName("Tech Global");
        createGoRestUser.setGender("Male");
        createGoRestUser.setEmail(faker.internet().emailAddress());
        createGoRestUser.setStatus("active");

        System.out.println("======= Creating the user with POST request ==========");

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("GoRestToken"))
                .body(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(createGoRestUser))
                .when().post("/public/v2/users")
                .then().log().all()
                //validating the status code with Rest Assured
                .and().assertThat().statusCode(201)
                .time(Matchers.lessThan(2000L))
                // validating the response time is less than the specified one
                // validating the value from the body with hamcrest
                .body("name", equalTo("Tech Global"))
                // validating the response content type
                .contentType(ContentType.JSON)
                .extract().response();

        System.out.println("======= Fetching the user with GET request =======");

        goRestID = response.jsonPath().getInt("id");

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("GoRestToken"))
                .when().get("/public/v2/users/" + goRestID)
                .then().log().all()
                //validating the status code with Rest Assured
                .and().assertThat().statusCode(200)
                .time(Matchers.lessThan(2000L))
                // validating the response time is less than the specified one
                // validating the value from the body with hamcrest
                .body("name", equalTo("Tech Global"))
                // validating the response content type
                .contentType(ContentType.JSON)
                .extract().response();

        System.out.println("========== Updating the user with PUT request ==========");

        UpdateGoRestUser updateGoRestUser = new UpdateGoRestUser();
        updateGoRestUser.setName("TechGlo");
        updateGoRestUser.setEmail(faker.internet().emailAddress());


        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("GoRestToken"))
                .body(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(updateGoRestUser))
                .when().put("/public/v2/users/" + goRestID)
                .then().log().all()
                //validating the status code with Rest Assured
                .and().assertThat().statusCode(200)
                .time(Matchers.lessThan(4000L))
                // validating the response time is less than the specified one
                // validating the value from the body with hamcrest
                .body("name", equalTo("TechGlo"))
                // validating the response content type
                .contentType(ContentType.JSON)
                .extract().response();

        System.out.println("========= Deleting the user with DELETE request ==========");

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("GoRestToken"))
                .when().delete("/public/v2/users/"+ goRestID)
                .then().log().all()
                .and().assertThat().statusCode(204)
                .time(Matchers.lessThan(2000L))
                .extract().response();

    }
}
