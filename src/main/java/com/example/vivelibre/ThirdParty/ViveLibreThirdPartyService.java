package com.example.vivelibre.ThirdParty;

import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.vivelibre.ThirdParty.Requests.AuthViveLibreRequest;
import com.example.vivelibre.ThirdParty.Requests.ThirdPartyRequest;
import com.example.vivelibre.ThirdParty.Requests.Auth.ViveLibreTokenRequestBody;
import com.example.vivelibre.ThirdParty.Requests.Auth.ViveLibreTokenResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ViveLibreThirdPartyService implements ThirdPartyService {
	
	@Autowired
	ThirdPartyClient httpClient;
	
	@Value( "${thirdparty.vivelibre.host}" )
	private String host;
	
	@Value( "${thirdparty.vivelibre.port}" )
	private String port;
	
	@Value( "${thirdparty.vivelibre.auth.username}" )
	private String username;
	
	@Value( "${thirdparty.vivelibre.auth.password}" )
	private String password;
	
	public ViveLibreThirdPartyService(ThirdPartyClient httpClient)
	{
		this.httpClient = httpClient;
	}
	
	public Optional<ViveLibreTokenResponseBody> getToken()
	{
		ViveLibreTokenRequestBody body = new ViveLibreTokenRequestBody(username, password);
		AuthViveLibreRequest request = new AuthViveLibreRequest(body);
		
		ObjectMapper objectMapper = new ObjectMapper();
		ViveLibreTokenResponseBody token = null;
		
		try {
			String jsonResponse = httpClient.getResponse(host + ":" + port + "/token", request);
			
			token = objectMapper.readValue(jsonResponse, new TypeReference<ViveLibreTokenResponseBody>(){});
		} catch (RestClientException e) {
			System.out.println("Error call: " + e.getMessage());
		} catch (ParseException e) {
			System.out.println("Error parse: " + e.getMessage());
		} catch (JsonMappingException e) {
			System.out.println("Error mapping: " + e.getMessage());
		} catch (JsonProcessingException e) {
			System.out.println("Error processing json: " + e.getMessage());
		}
		
		return Optional.ofNullable(token);
	}
}
