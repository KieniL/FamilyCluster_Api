package com.kienast.apiservice.controller;

import java.util.List;

import javax.validation.Valid;

import com.kienast.apiservice.config.IntializeLogInfo;
import com.kienast.apiservice.dto.TokenAdapter;
import com.kienast.apiservice.exception.NotAuthorizedException;
import com.kienast.apiservice.model.Token;
import com.kienast.apiservice.rest.api.AuthApi;
import com.kienast.apiservice.rest.api.model.AuthenticationModel;
import com.kienast.apiservice.rest.api.model.ChangedModel;
import com.kienast.apiservice.rest.api.model.JWTTokenModel;
import com.kienast.apiservice.rest.api.model.LoginModel;
import com.kienast.apiservice.rest.api.model.PasswordModel;
import com.kienast.apiservice.rest.api.model.ResettedModel;
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

	// Used for WebTemplate
	@Autowired
	private WebClient.Builder webClientBuilder;

	@Value("${authURL}")
	private String authURL;

	@Value("${logging.level.com.kienast.apiservice}")
	private String loglevel;

	@Override
	@Operation(description = "Register a customer")
	public ResponseEntity<JWTTokenModel> register(String JWT, String xRequestID, String SOURCE_IP,
			LoginModel loginModel) {
		Token tokenResponse = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);

		try {
			tokenResponse = webClientBuilder.build().patch() // RequestMethod
					.uri(authURL + "/auth").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).header("JWT", JWT)
					.header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP).body(BodyInserters.fromObject(loginModel))
					.retrieve() // run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
						return Mono.error(new NotAuthorizedException(String.format("Failed! %s", loginModel.getUsername())));
					}).bodyToMono(Token.class) // convert Response
					.block(); // do as Synchronous call
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}

		if (tokenResponse != null) {
			JWTTokenModel response = new TokenAdapter(tokenResponse, loginModel.getUsername()).createJson();
			return ResponseEntity.ok(response);
		}

		return ResponseEntity.badRequest().body(null);
	}

	@Override
	@Operation(description = "Verify JWT")
	public ResponseEntity<TokenVerifiyResponseModel> verifyToken(String JWT, String xRequestID, String SOURCE_IP,
			@Valid JWTTokenModel tokenModel) {
		TokenVerifiyResponseModel tokenVerifiyResponseModel = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);

		try {
			tokenVerifiyResponseModel = webClientBuilder.build().put() // RequestMethod
					.uri(authURL + "/auth").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).header("JWT", JWT)
					.header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP).body(BodyInserters.fromObject(tokenModel))
					.retrieve() // run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
						return Mono.error(new NotAuthorizedException(String.format("Failed! %s", JWT)));
					}).bodyToMono(TokenVerifiyResponseModel.class) // convert Response
					.block(); // do as Synchronous call
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}

		if (tokenVerifiyResponseModel != null) {
			return ResponseEntity.ok(tokenVerifiyResponseModel);
		}

		return ResponseEntity.badRequest().body(null);
	}

	@Override
	@Operation(description = "Authenticate a customer")
	public ResponseEntity<AuthenticationModel> authenticate(String xRequestID, String SOURCE_IP,
			@Valid LoginModel loginModel) {
		AuthenticationModel authenticationModel = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);

		try {
			authenticationModel = webClientBuilder.build().post() // RequestMethod
					.uri(authURL + "/auth").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP).body(BodyInserters.fromObject(loginModel))
					.retrieve() // run command
					.onStatus(HttpStatus::is4xxClientError, UserModelresponse -> {
						return Mono.error(new NotAuthorizedException(String.format("Failed! %s", loginModel.getUsername())));
					}).bodyToMono(AuthenticationModel.class) // convert Response
					.block(); // do as Synchronous call
		} catch (Exception e) {
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
	public ResponseEntity<ResettedModel> resetMfa(String JWT, String xRequestID, String SOURCE_IP, String username,
			@Valid JWTTokenModel tokenModel) {
		ResettedModel resettedResponse = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);

		try {
			resettedResponse = webClientBuilder.build().post() // RequestMethod
					.uri(authURL + "/auth/" + username).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.header("JWT", JWT).header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP)
					.body(BodyInserters.fromObject(tokenModel)).retrieve() // run commandUserModel
					.onStatus(HttpStatus::is4xxClientError, response -> {
						return Mono.error(new NotAuthorizedException(String.format("Failed! %s", username)));
					}).bodyToMono(ResettedModel.class) // convert Response
					.block(); // do as Synchronous call
		} catch (Exception e) {
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
	public ResponseEntity<ChangedModel> changePassword(String JWT, String xRequestID, String SOURCE_IP, String username,
			@Valid PasswordModel passwordModel) {
		ChangedModel changedResponse = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);

		try {
			changedResponse = webClientBuilder.build().put() // RequestMethodUserModel
					.uri(authURL + "/auth/" + username).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.header("JWT", JWT).header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP)
					.body(BodyInserters.fromObject(passwordModel)).retrieve() // run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
						return Mono.error(new NotAuthorizedException(String.format("Failed! %s", username)));
					}).bodyToMono(ChangedModel.class) // convert Response
					.block(); // do as Synchronous call
		} catch (Exception e) {
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
	public ResponseEntity<List<UserModel>> getUsers(String JWT, String xRequestID, String SOURCE_IP) {
		List<UserModel> users = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);

		try {
			users = webClientBuilder.build().get() // RequestMethod
					.uri(authURL + "/auth/").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).header("JWT", JWT)
					.header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP).retrieve() // run command
					.bodyToFlux(UserModel.class).collectList().block(); // convert Response
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return ResponseEntity.ok(users);
	}

}
