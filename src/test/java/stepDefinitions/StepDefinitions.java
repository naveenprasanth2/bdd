package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.ResponsePlace;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	ResponsePlace rp;
	TestDataBuild data = new TestDataBuild();
	AddPlace ap = new AddPlace();
	static String placeId;
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String Address) throws IOException {

		res = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, language, Address));
	}

	@When("user calls {string} with {string} http Request")
	public void user_calls_with_http_request(String resource, String method) {
		// Write code here that turns the phrase above into concrete actions
		APIResources apiResponse = APIResources.valueOf(resource);
		String value = apiResponse.getResource(); // calling custom getResource method inside Enum class
		if (method.equalsIgnoreCase("POST")) {
			response = res.when().post(value);
		}else if (method.equalsIgnoreCase("GET")) {
			response = res.when().get(value);
		}else if (method.equalsIgnoreCase("PUT")) {
			response = res.when().put(value);
		}else if (method.equalsIgnoreCase("DELETE")) {
			response = res.when().post(value);
		}
	}

	@Then("the API call is success with status code {string}")
	public void the_api_call_is_success_with_status_code(String statusCode) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(Integer.parseInt(statusCode), response.getStatusCode());
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(getJsonPath(response, key), value);
	}
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		placeId = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}
	@Given("DeletePlace payLoad")
	public void delete_place_pay_load() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
	}
}
