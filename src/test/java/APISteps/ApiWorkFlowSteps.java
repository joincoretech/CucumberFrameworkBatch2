package APISteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.Argument;
import io.restassured.specification.RequestSpecification;
import utils.ApiConstants;
import utils.ApiPayLoad;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiWorkFlowSteps {

    RequestSpecification request;
    Response response;
    static String Employee_id;

    @Given("prepare a request to add employee")
    public void prepare_a_request_to_add_employee() {
        request = given().header(ApiConstants.content_type, ApiConstants.application_json)
                .header(ApiConstants.header_Authorization, GenerateTokenSteps.token)
                .body(ApiPayLoad.createEmployeePayload());
    }

    @When("a post call is made to create employee")
    public void a_post_call_is_made_to_create_employee() {
        response = request.when().post(ApiConstants.create_employee_uri);
    }

    @Then("validate the status code is {int}")
    public void validate_the_status_code_is(Integer code) {
        response.then().assertThat().statusCode(code);
    }

    @Then("validate the message {string} and value {string}")
    public void validate_the_message_and_value(String message, String employeeAdded) {

        response.then().assertThat().body(message, equalTo(employeeAdded));
    }

    @Then("add the employee id value to an variable {string}")
    public void add_the_employee_id_value_to_an_variable(String empId) {

       Employee_id=empId;
    }

    @Given("prepare request to get all the employee")
    public void prepare_request_to_get_all_the_employee() {
       request=given().header(ApiConstants.content_type, ApiConstants.application_json)
               .header(ApiConstants.header_Authorization, GenerateTokenSteps.token);
    }

    @When("make a get call to retrieve all the employee")
    public void make_a_get_call_to_retrieve_all_the_employee() {
       response=request.when().get(ApiConstants.get_employee_uri);
    }

    @Then("validate the status code {int}")
    public void validate_the_status_code(Integer statusCode) {
       response.then().assertThat().statusCode(statusCode);
    }

    @Then("validate the created employee is present in the response")
    public void validate_the_created_employee_is_present_in_the_response() {
        String allEmployee=response.prettyPrint();
        JsonPath js=new JsonPath(allEmployee);

        int count=js.getInt("data.size()");
        System.out.println(count);

        for (int i=0; i<count; i++ ){

            String empID=js.getString("data["+i+"].EmpId");

            if (empID.contentEquals(Employee_id)){
                System.out.println("employee id "+ Employee_id+ " is present in the response body");
                String name=js.getString("data["+i+"].FirstName");

                System.out.println(name);
              //  response.then().assertThat().body(name, equalTo("Azizullah"));
                break;
            }
        }
    }

    @Given("prepare request for delete the employee")
    public void prepare_request_for_delete_the_employee() {
       request=given().header(ApiConstants.content_type, ApiConstants.application_json)
               .header(ApiConstants.header_Authorization, GenerateTokenSteps.token)
               .body(ApiPayLoad.deleteEmployeePayload());
    }



    @When("make a call to delete the employee")
    public void make_a_call_to_delete_the_employee() {
        response=request.when().delete(ApiConstants.delete_employee_uri);
    }


}