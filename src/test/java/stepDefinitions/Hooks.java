package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {

        stepDefinition m = new stepDefinition();
        if (stepDefinition.place_id == null) {

            m.addPlacePayloadWithNameLanguageAddress("CoffeeShop", "French", "1 Pacific Hwy");
            m.user_calls_with_post_http_request("AddPlaceAPI", "POST");
            m.verifyPlace_IdCreatedMapsToUsing("CoffeeShop", "getPlaceAPI");
        }
    }

}
