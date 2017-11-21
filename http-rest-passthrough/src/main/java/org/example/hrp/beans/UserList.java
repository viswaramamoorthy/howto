package org.example.hrp.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Viswa Ramamoorthy
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(using=UserListSerializer.class)
public class UserList {
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
