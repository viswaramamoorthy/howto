package org.example.hrp.beans;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

/**
 * @author Viswa Ramamoorthy
 *
 */
public class OtherResponseMessage {
	
	private Map<String, Object> message;

	@JsonAnyGetter
	public Map<String, Object> getMessage() {
		return message;
	}

	public void setMessage(Map<String, Object> message) {
		this.message = message;
	}
	
	public static OtherResponseMessage prepareResponse(String key, Object value) {
		OtherResponseMessage response = new OtherResponseMessage();
		Map<String, Object> map = new HashMap<>();
		map.put(key, value);
		response.setMessage(map);
		return response;
	}
}
