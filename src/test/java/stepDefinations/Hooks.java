package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@deleteplace")
	public void beforeScenario() throws IOException
	{
		// execute this only when place id is null
		//this is test123
		//this is test 10
		// this is master 
		placeValidationStepDef def = new placeValidationStepDef();
		if(placeValidationStepDef.placeID == null)
		{
			def.add_place_payload("Raje", "7fhf");
			def.user_calls_with_http_request("AddPlaceAPI", "post");
			def.verify_place_id_created_maps_to_using("Raje","GetPlaceAPI");
		}

	}

}
