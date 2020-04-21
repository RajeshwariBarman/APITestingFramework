package Resources;

import java.util.ArrayList;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addplacePayload(String name , String language)
	{
		AddPlace add_place = new pojo.AddPlace();
		add_place.setAccuracy(50);
		add_place.setLanguage(language);
		add_place.setName(name);
		add_place.setAddress("29, side layout, cohen 09");
		add_place.setPhone_number("(+91) 983 893 3937");
		add_place.setWebsite("http://google.com");
		ArrayList<String> my_list = new ArrayList<String>();
		my_list.add("shoe park");
		my_list.add("shop");
		add_place.setTypes(my_list);
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		add_place.setLocation(loc);
		return add_place;
	}
	
	public String deletePlacePayload(String place_id)
	{
		return "{\r\n" + 
				"    \"place_id\": \""+place_id+"\"\r\n" + 
				"}";
	}
}
