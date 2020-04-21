Feature: Validating place API's 

@addplace
Scenario Outline: Verify if place is being successfully added using AdddplaceAPI 

	Given add place payload "<name>" "<language>" 
	When user calls "AddPlaceAPI" with "post" http request 
	Then the API call got success with status code 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP" 
	And verify place_id created maps to "<name>" using "GetPlaceAPI" 
	
	Examples: 
	
		|name|language|
		|rajeshwari|english|
		#		|raje|french|
		

@deleteplace
Scenario: Verify if the Delete place functionlity is working 

	Given DeletePlace Payload 
	When user calls "DeletePlaceAPI" with "Post" http request 
	Then the API call got success with status code 200 
	And "status" in response body is "OK" 		
		
		
 
 
	