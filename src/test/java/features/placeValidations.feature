Feature: Validating Place APIs
  @AddPlace @Regression
  Scenario Outline: Verify if place is being successfully added using AddPlace API

    Given Add Place Payload with "<name>", "<language>", "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then The API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"



  Examples:
    |  name           |  language    |     address               |
    | Frontier house  | French-IN    | 29, side layout, cohen 09 |
    | Beach house     | Spanish      | Sea Cross Center          |


  @DeletePlace @Regression
  Scenario: Verify if Delete Place functionality is working

    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "DELETE" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"