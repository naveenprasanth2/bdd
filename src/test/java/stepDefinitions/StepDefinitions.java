package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class StepDefinitions {
	RequestSpecification res;
	ResponseSpecification resSpec;
	RequestSpecification req;
	Response response;

	@Given("Add place payload")
	public void add_place_payload() {
		AddPlace add = new AddPlace();
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		add.setLocation(l);
		add.setAccuracy(50);
		add.setName("Frontline house");
		add.setPhone_number("(+91) 983 893 3937");
		add.setAddress("29, side layout, cohen 09");
		add.setTypes(Arrays.asList("shoe park", "shop"));
		add.setWebsite("http://google.com");
		add.setLanguage("French-IN");

//		ResponsePlace rp = given().log().all().baseUri("https://rahulshettyacademy.com").queryParam("key", "qaclick123")
//				.body(add).when().post("/maps/api/place/add/json").then().extract().as(ResponsePlace.class);
		req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
		res = given().spec(req).body(add);

		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	}

	@When("user calls {string} with POST http Request")
	public void user_calls_with_post_http_request(String string) {
		// Write code here that turns the phrase above into concrete actions
		response = given().spec(res).when().post("/maps/api/place/add/json").then().spec(resSpec).extract().response();
	}

	@Then("the API call is success with status code {string}")
	public void the_api_call_is_success_with_status_code(String statusCode) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		// Write code here that turns the phrase above into concrete actions
	String responseValue = response.asString();
	JsonPath js = new JsonPath(responseValue);
	assertEquals(js.getString(key), value);
	System.out.println(js.getString(key));
	}
}
