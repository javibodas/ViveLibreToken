package com.example.vivelibre.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vivelibre.ThirdParty.ViveLibreThirdPartyService;
import com.example.vivelibre.ThirdParty.Requests.Auth.ViveLibreTokenResponseBody;

@RestController()
public class AuthController {
	
	@Autowired
	ViveLibreThirdPartyService viveLibreThirdPartyService;
	
	@GetMapping(value="/get-token", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
	public ResponseEntity<ViveLibreTokenResponseBody> getToken()
	{
		Optional<ViveLibreTokenResponseBody> token = viveLibreThirdPartyService.getToken();
		
		if (token.isPresent()) {
			return new ResponseEntity<ViveLibreTokenResponseBody>(token.get(),HttpStatus.OK);
		} else {
			return new ResponseEntity<ViveLibreTokenResponseBody>(new ViveLibreTokenResponseBody("heh"), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

}
