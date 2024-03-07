package resources;

import pojoClasses.AddPlace;
import pojoClasses.DeletePlace;
import pojoClasses.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String language, String address ) {
        AddPlace placeBodyData = new AddPlace();

        placeBodyData.setAccuracy(50);
        placeBodyData.setAddress(address);
        placeBodyData.setLanguage(language);
        placeBodyData.setPhone_number("(+91) 983 893 3937");
        placeBodyData.setWebsite("https://rahulshettyacademy.com");
        placeBodyData.setName(name);
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        placeBodyData.setTypes(myList);
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        placeBodyData.setLocation(l);

        return placeBodyData;
    }

    public DeletePlace deletePlacePayload(String placeId)
    {
        DeletePlace deletePlace = new DeletePlace();

        deletePlace.setPlace_id(placeId);

        return deletePlace;
//        return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";

    }

}

