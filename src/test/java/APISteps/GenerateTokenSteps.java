package APISteps;

import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {

    public static String token;
    @Given("token is generated")
    public void token_is_generated() {

        RequestSpecification generateTokenRequest= given().header("content-type", "application/json")
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}");

        Response response=generateTokenRequest.when().post("https://reqres.in/api/register");
        response.prettyPrint();

        token="Bearer " +response.jsonPath().getString("token");
    }
}
