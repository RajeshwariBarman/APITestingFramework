package stepDefinations;

import static io.restassured.RestAssured.given;

import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
import java.io.IOException;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class placeValidationStepDef extends Utils {

	RequestSpecification res;
	ResponseSpecification resp_spec;
	Response resp;
	TestDataBuild data = new TestDataBuild();
	JsonPath js;
	static String placeID;

	@Given("add place payload {string} {string}")
	public void add_place_payload(String name, String language) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecification()).body(data.addplacePayload(name, language));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		// Write code here that turns the phrase above into concrete actions
		APIResources resourceapi = APIResources.valueOf(resource);
		resp_spec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json").build();
		if (method.equalsIgnoreCase("POST"))
			resp = res.when().post(resourceapi.getResource());
		else if (method.equalsIgnoreCase("delete"))
			resp = res.when().delete(resourceapi.getResource());
		else if (method.equalsIgnoreCase("get"))
			resp = res.when().get(resourceapi.getResource());
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(resp.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String expkey, String expvalue) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(getJsonPath(resp, expkey), expvalue);

	}

	@Then("verify place_id created maps to {string} using {string}") 
	public void verify_place_id_created_maps_to_using(String expname, String resource) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		placeID  = getJsonPath(resp, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", placeID);
		user_calls_with_http_request(resource, "get");
		assertEquals(expname, getJsonPath(resp,"name"));
	}
	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    res = given().spec(requestSpecification()).body(data.deletePlacePayload(placeID));
	}
	
}
