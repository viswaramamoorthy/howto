package org.example.hrp.test;

import org.example.hrp.beans.Address;
import org.example.hrp.beans.GeoLocation;
import org.example.hrp.beans.User;

public class TestUtil {
	public static User prepareUser() {
		User user = new User();
		Address address = new Address();
		GeoLocation geoLocation = new GeoLocation();
	
		address.setStreet("Kulas Light");
		address.setCity("Gwenborough");
		address.setSuite("Apt. 556");
		address.setZipcode("92998-3874");
		
		geoLocation.setLongitude("1");
		geoLocation.setLatitue("10");
		address.setGeoLocation(geoLocation);
		
		user.setId("id123");
		user.setEmail("test@example.org");
		user.setName("a user");
		user.setUserName("auser");
		user.setAddress(address);

		return user;
	}
}
