package org.example.hrp.beans;

/**
 * @author Viswa Ramamoorthy
 *
 */
public class Constants {
	
	//Route constants
	public static final String ROUTE_NAME = "direct:users";
	
	//Destination URIs
	public static final String LIST_TARGET_URI = "https://jsonplaceholder.typicode.com/users?bridgeEndpoint={0}";
	
	//Rest content type
	public static final String CONTENT_TYPE_JSON = "application/json";
	
	//Headers/parameters used by camel routes 
	public static final String bridgeEndpointParam = "bridgeEndpoint";
	public static final String filterParam = "username";
}
