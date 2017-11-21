package org.example.hrp.beans;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class UserListSerializer extends JsonSerializer<UserList> {

	@Override
	public void serialize(UserList value, JsonGenerator gen,
			SerializerProvider serializers) throws IOException,
			JsonProcessingException {
		gen.writeStartArray();
		if (value != null && value.getUsers() != null) {
			for (User user : value.getUsers()) {
				gen.writeObject(user);
			}
		}
		gen.writeEndArray();
	}

}
