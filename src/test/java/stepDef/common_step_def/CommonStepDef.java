package stepDef.common_step_def;

import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static utils.Hooks.response;

public class CommonStepDef {

    //this class is common for all test scenarios because we validating the status code

    private static Logger logger = LogManager.getLogger(CommonStepDef.class);

    int actualStatusCode;
    @Given("Validate that status code is {int}")
    public void validate_that_status_code_is(Integer expectedStatusCode) {

        logger.info("Getting the status code from the response");
        actualStatusCode = response.statusCode();



        assertThat(
                "Validating the status code",
                actualStatusCode,
                is(expectedStatusCode)
        );
    }
}