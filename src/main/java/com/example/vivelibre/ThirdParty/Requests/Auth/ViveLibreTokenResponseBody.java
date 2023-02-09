package com.example.vivelibre.ThirdParty.Requests.Auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ViveLibreTokenResponseBody implements AuthViveLibreResponseBody {
	
	private String token;
	
	@JsonCreator
	public ViveLibreTokenResponseBody(@JsonProperty("token") String token)
	{
		this.token = token;
	}

	public String getAuthViveLibre() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
