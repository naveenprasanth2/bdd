Feature: Validating place API's

Scenario: Verify if place is being successfully added using AddPlaceAPI
	Given Add place payload
	When user calls "addPlaceAPI" with POST http Request
	Then the API call is success with status code "200"
	And "status" in response body is "OK"
	And "scope" in response body is "APP