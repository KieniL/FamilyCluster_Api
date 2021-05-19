package com.kienast.apiservice.controller;

import java.util.List;

import javax.validation.Valid;

import com.kienast.apiservice.dto.TokenAdapter;
import com.kienast.apiservice.exception.NotAuthorizedException;
import com.kienast.apiservice.model.Token;
import com.kienast.apiservice.rest.api.AuthApi;
import com.kienast.apiservice.rest.api.model.AuthenticationModel;
import com.kienast.apiservice.rest.api.model.ChangedModel;
import com.kienast.apiservice.rest.api.model.LoginModel;
import com.kienast.apiservice.rest.api.model.PasswordModel;
import com.kienast.apiservice.rest.api.model.ResettedModel;
import com.kienast.apiservice.rest.api.model.TokenModel;
import com.kienast.apiservice.rest.api.model.TokenVerifiyResponseModel;
import com.kienast.apiservice.rest.api.model.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Mono;


@RestController
public class AuthController implements AuthApi {
	
	//Used for WebTemplate
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Value("${authURL}")
	private String authURL;

	@Override
	@Operation(description = "Register a customer")
	public ResponseEntity<TokenModel> register(LoginModel loginModel) {
		Token tokenResponse = null;
		
		try {
			tokenResponse = webClientBuilder.build()
					.patch() //RequestMethod
					.uri(authURL+"/auth")
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(BodyInserters.fromObject(loginModel))
					.retrieve() //run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
	                    return Mono.error(new NotAuthorizedException(
	                            String.format("Failed! %s", loginModel.getUsername())
	                    ));
	                })
					.bodyToMono(Token.class) //convert Response
					.block(); //do as Synchronous call
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}

		
		if (tokenResponse != null) {
			TokenModel response = new TokenAdapter(tokenResponse, loginModel.getUsername()).createJson();
			return ResponseEntity.ok(response);
		}
		
		return ResponseEntity.badRequest().body(null);
	}
	@Override
	@Operation(description = "Verify JWT")
	public ResponseEntity<TokenVerifiyResponseModel> verifyToken(@Valid TokenModel tokenModel) {
		TokenVerifiyResponseModel tokenVerifiyResponseModel = null;
		
		try {
			tokenVerifiyResponseModel = webClientBuilder.build()
					.put() //RequestMethod
					.uri(authURL+"/auth")
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(BodyInserters.fromObject(tokenModel))
					.retrieve() //run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
	                    return Mono.error(new NotAuthorizedException(
	                            String.format("Failed! %s", tokenModel.getToken())
	                    ));
	                })
					.bodyToMono(TokenVerifiyResponseModel.class) //convert Response
					.block(); //do as Synchronous call
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		if(tokenVerifiyResponseModel != null) {
			return ResponseEntity.ok(tokenVerifiyResponseModel);
		}

		
		return ResponseEntity.badRequest().body(null);
	}

	@Override
	@Operation(description = "Authenticate a customer")
	public ResponseEntity<AuthenticationModel> authenticate(@Valid LoginModel loginModel) {
		AuthenticationModel authenticationModel = null;
		try {
			authenticationModel = webClientBuilder.build()
					.post() //RequestMethod
					.uri(authURL+"/auth")
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(BodyInserters.fromObject(loginModel))
					.retrieve() //run command
					.onStatus(HttpStatus::is4xxClientError, UserModelresponse -> {
	                    return Mono.error(new NotAuthorizedException(
	                            String.format("Failed! %s", loginModel.getUsername())
	                    ));
	                })
					.bodyToMono(AuthenticationModel.class) //convert Response
					.block(); //do as Synchronous call
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}

		
		if (authenticationModel != null) {
			return ResponseEntity.ok(authenticationModel);
		}
		return ResponseEntity.badRequest().body(null);
	}

	@Override
	@Operation(description = "Reset Mfa")
	public ResponseEntity<ResettedModel> resetMfa(String username, @Valid TokenModel tokenModel) {
		ResettedModel resettedResponse = null;
		
		try {
			resettedResponse = webClientBuilder.build()
					.post() //RequestMethod
					.uri(authURL+"/auth/" + username)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(BodyInserters.fromObject(tokenModel))
					.retrieve() //run commandUserModel
					.onStatus(HttpStatus::is4xxClientError, response -> {
	                    return Mono.error(new NotAuthorizedException(
	                            String.format("Failed! %s", username)
	                    ));
	                })
					.bodyToMono(ResettedModel.class) //convert Response
					.block(); //do as Synchronous call
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		if (resettedResponse != null) {
			return ResponseEntity.ok(resettedResponse);
		}
		
		return ResponseEntity.status(403).body(null);
	}

	@Override
	@Operation(description = "Change Password")
	public ResponseEntity<ChangedModel> changePassword(String username, @Valid PasswordModel passwordModel) {
		ChangedModel changedResponse = null;
		
		try {
			changedResponse = webClientBuilder.build()
					.put() //RequestMethodUserModel
					.uri(authURL+"/auth/" + username)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(BodyInserters.fromObject(passwordModel))
					.retrieve() //run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
	                    return Mono.error(new NotAuthorizedException(
	                            String.format("Failed! %s", username)
	                    ));	
	                })
					.bodyToMono(ChangedModel.class) //convert Response
					.block(); //do as Synchronous call
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		if (changedResponse != null) {
			return ResponseEntity.ok(changedResponse);
		}
		
		return ResponseEntity.status(403).body(null);
	}

	@Override
	@Operation(description = "Get all Users")
	public ResponseEntity<List<UserModel>> getUsers() {
		List<UserModel> users = null;
		
		try {
			users = webClientBuilder.build()
					.get() //RequestMethod
					.uri(authURL+"/auth/")
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.retrieve() //run command
					.bodyToFlux(UserModel.class).collectList().block(); //convert Response
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		return ResponseEntity.ok(users);
	}


}
