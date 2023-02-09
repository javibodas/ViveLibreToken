package com.example.vivelibre.ThirdParty.Requests;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

import com.example.vivelibre.ThirdParty.Requests.Auth.AuthViveLibreRequestBody;
import com.example.vivelibre.ThirdParty.Requests.Auth.ViveLibreTokenRequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthViveLibreRequest implements ThirdPartyRequest {
	
	private AuthViveLibreRequestBody body;
	
	
	public AuthViveLibreRequest(AuthViveLibreRequestBody body) 
	{
		this.body = body;
	}

	@Override
	public String getBody() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(body);
	}

	@Override
	public MultiValueMap<String, String> getHeaders() {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		
	    return headers;
	}

}
