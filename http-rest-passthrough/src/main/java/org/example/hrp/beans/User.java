package org.example.hrp.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Viswa Ramamoorthy
 * 
 * Single class used to do transformation of source json
 * to destination json. i.e. Source and destination json names are present in this class
 * Used to show the mapping capability.
 * In a real application, it could be revisited to separate source and destination
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	private String id;
	private String name;
	private String userName;
	private String email; 
	private Address address;
	
	public String getId() {
		return id;
	}

    @JsonProperty(value = "id")
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @JsonProperty(value = "username")
	public String getUserName() {
		return userName;
	}

    @JsonProperty(value = "username")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
