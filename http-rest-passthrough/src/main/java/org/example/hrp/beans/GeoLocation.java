package org.example.hrp.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoLocation {
	
	private String latitue;
	private String longitude;

    @JsonProperty(value = "lat")
	public String getLatitue() {
		return latitue;
	}

    @JsonProperty(value = "lat")
	public void setLatitue(String latitue) {
		this.latitue = latitue;
	}

    @JsonProperty(value = "lng")
	public String getLongitude() {
		return longitude;
	}

    @JsonProperty(value = "lng")
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
