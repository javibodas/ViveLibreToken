package com.example.vivelibre.ThirdParty;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.example.vivelibre.ThirdParty.Requests.ThirdPartyRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class ThirdPartyClient {
	
	private RestTemplate restTemplate;
	
	private JSONParser parser;
	
	
	public ThirdPartyClient()
	{
		this.restTemplate = new RestTemplate();
		this.parser = new JSONParser();
		
	}

	public String getResponse(String url, ThirdPartyRequest request) throws RestClientException, JsonProcessingException, ParseException
	{
		ResponseEntity<String> jsonResponse = this.postCall(url, request);
		
		JSONObject jsonObject = (JSONObject) this.parser.parse(jsonResponse.getBody());
		
		return jsonObject.toJSONString();
	}
	
	private ResponseEntity<String> postCall(String url, ThirdPartyRequest request) throws RestClientException, JsonProcessingException {
		return this.restTemplate.postForEntity(url, new HttpEntity<String>(request.getBody(), request.getHeaders()),  String.class);
	}
	
}
