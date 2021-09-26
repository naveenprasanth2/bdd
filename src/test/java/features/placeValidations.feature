Feature: Validating place API's

@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
	Given Add place payload with "<name>" "<language>" "<address>"
	When user calls "addPlaceAPI" with "POST" http Request
	Then the API call is success with status code "200"
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"
	
Examples:
		|name				|language			|address				|
		|SummaHouse	|English			|enga veedu			|
#		|SummaHouse1	|English1			|enga veedu1	|

@DeletePlace
Scenario: Verify if delete place functionality is working
	
	Given DeletePlace payLoad
	When user calls "deletePlaceAPI" with "POST" http Request
	Then the API call is success with status code "200"
	And "status" in response body is "OK"