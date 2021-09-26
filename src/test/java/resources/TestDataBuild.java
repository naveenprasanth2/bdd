package resources;
import java.util.Arrays;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayLoad(String name, String language, String Address ) {
		AddPlace add = new AddPlace();
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		add.setLocation(l);
		add.setAccuracy(50);
		add.setName(name);
		add.setPhone_number("(+91) 983 893 3937");
		add.setAddress(Address);
		add.setTypes(Arrays.asList("shoe park", "shop"));
		add.setWebsite("http://google.com");
		add.setLanguage(language);
		return add;
	} 
	
	public String deletePlacePayload(String placeId) {
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
}
