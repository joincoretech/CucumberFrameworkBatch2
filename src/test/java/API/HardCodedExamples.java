package API;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;


import static com.sun.webkit.perf.WCGraphicsPerfLogger.log;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HardCodedExamples {

    // note:
    // -- Given : prepare the request
    // -- When : making a request to send
    // -- Then : Verification/ Assertion

    static  String token="Bearer QpwL5tke4Pnpja7X4";

   // @Test
    public void sampleTest(){
        RequestSpecification prepareRequest= given().header("Authorization", token)
                .header("Content-Type", "application/json");

        Response response= prepareRequest.when().get("https://dreamhiringacademy.com/api/show_city.php");

        // we can use asString() method to change the data to json format
        System.out.println(response.asString());
    }

    @Test
    public void createCountry(){

        RequestSpecification prepareRequest=given().header("Authorization", token)
                .header("content-type", "application/json")
                .body(" { \"CountryId\": \"50\", \"Name\": \"USA\" }");

        Response response=prepareRequest.when().post("https://dreamhiringacademy.com/api/add_country.php");

        System.out.println(response.asString());
        response.prettyPrint();// this will print the response to console same as asString() method

         String countryAdded=response.jsonPath().getString("msg");
         System.out.println(countryAdded);

         // assertion:

        response.then().assertThat().statusCode(200);

        response.then().assertThat().body("msg", equalTo("Country added"));
        System.out.println("country added");

        response.then().assertThat().body("status", equalTo("success"));
    }

    @Test
    public void getCountries(){

        RequestSpecification prepareRequest=given().header("authorization", token)
                .header("content-type", "application/json");

        Response response=prepareRequest.when()
                .get("\thttps://dreamhiringacademy.com/api/show_country.php");

        System.out.println(response.asString());

        JsonPath js= new JsonPath(response.prettyPrint());

        int count= js.getInt("size()");
        System.out.println(count);

        for (int i=0; i<count; i++ ){

            String countryId=js.getString("CountryId["+i+"]");

            System.out.print(countryId+" ");

            if (countryId.contentEquals("50")){
                System.out.println();
                System.out.println(js.getString("["+i+"].Name"));
            }
        }

    }
}
