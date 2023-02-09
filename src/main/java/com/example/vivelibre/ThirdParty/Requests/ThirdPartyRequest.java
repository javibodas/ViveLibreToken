package com.example.vivelibre.ThirdParty.Requests;

import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ThirdPartyRequest {
	String getBody() throws JsonProcessingException;
	MultiValueMap<String, String> getHeaders();
}
