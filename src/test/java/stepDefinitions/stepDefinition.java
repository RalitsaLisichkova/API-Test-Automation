package stepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojoClasses.DeletePlace;
import resources.APIResources;
import resources.Specifications;
import resources.TestDataBuild;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.junit.Assert.assertEquals;

public class stepDefinition extends Specifications {


    TestDataBuild data = new TestDataBuild();
    RequestSpecification finalRequest;
    Response response;
    ResponseSpecification finalResponse;

    static String place_id;
    JsonPath js;

    APIResources resourceAPI;


    @Given("Add Place Payload with {string}, {string}, {string}")
    public void addPlacePayloadWithNameLanguageAddress(String name, String language, String address) throws IOException {

        finalRequest = given().spec(requestSpecification()).body(data.addPlacePayload(name , language, address));
    }



    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource , String method) {

        resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());

        finalResponse = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if(method.equalsIgnoreCase("POST"))
            response = finalRequest.when().post(resourceAPI.getResource());
        else if(method.equalsIgnoreCase("GET"))
            response = finalRequest.when().get(resourceAPI.getResource());
        else if(method.equalsIgnoreCase("DELETE"))
            response = finalRequest.when().delete(resourceAPI.getResource());


    }


    @Then("The API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(int expectedCode) {

        assertEquals(response.getStatusCode(),expectedCode);

    }

    @And("{string} in response body is {string}")
    public void in_response_body_is(String key, String expectedValue) {

        String responseAsString =  response.asString();

        js = new JsonPath(responseAsString);

        assertEquals(js.get(key).toString(),expectedValue);
        System.out.println(responseAsString);
    }


    @And("verify place_Id created maps to {string} using {string}")
    public void verifyPlace_IdCreatedMapsToUsing(String expectedName, String resource) throws IOException {

        place_id = getJsonPath(response,"place_id");
        finalRequest = given().spec(requestSpecification()).queryParam("place_id", place_id);

        user_calls_with_post_http_request(resource, "GET");

        String actualName = getJsonPath(response, "name");
        assertEquals(expectedName, actualName);



    }

    @Given("DeletePlace Payload")
    public void deleteplacePayload() throws IOException {

        String placeId = place_id;

        finalRequest = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));

    }

    @Then("the API call got success with status code {int}")
    public void theAPICallGotSuccessWithStatusCode(int expectedStatusCode) {

        assertEquals(response.getStatusCode(),expectedStatusCode);
    }
}