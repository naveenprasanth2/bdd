package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		// Execute only when the place id is null
		StepDefinitions st = new StepDefinitions();
		if (StepDefinitions.placeId == null) {
			st.add_place_payload_with("Shetty", "French", "Asia");
			st.user_calls_with_http_request("addPlaceAPI", "POST");
			st.verify_place_id_created_maps_to_using("Shetty", "getPlaceAPI");
		}
	}
}
