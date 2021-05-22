package com.kienast.apiservice.controller;

import java.util.List;

import javax.validation.Valid;

import com.kienast.apiservice.exception.NotAuthorizedException;
import com.kienast.apiservice.rest.api.AppApi;
import com.kienast.apiservice.rest.api.AppOfUserApi;
import com.kienast.apiservice.rest.api.model.ApplicationModel;
import com.kienast.apiservice.rest.api.model.ApplicationResponseModel;
import com.kienast.apiservice.rest.api.model.ApplicationWithoutJwtModel;
import com.kienast.apiservice.rest.api.model.UpdateApplicationModel;
import com.kienast.apiservice.rest.api.model.UpdatedModel;
import com.kienast.apiservice.rest.api.model.VerifiedModel;

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
public class AppController implements AppApi, AppOfUserApi {
	
	//Used for WebTemplate
		@Autowired
		private WebClient.Builder webClientBuilder;
		
		@Value("${authURL}")
		private String authURL;

	@Override
	@Operation(description = "Add an application")
	public ResponseEntity<ApplicationModel> addApplication(@Valid ApplicationModel applicationModel) {
		
		ApplicationModel applicationModelResponse = null;
		
		try {
			applicationModelResponse = webClientBuilder.build()
					.post() //RequestMethod
					.uri(authURL+"/app")
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(BodyInserters.fromObject(applicationModel))
					.retrieve() //run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
			            return Mono.error(new NotAuthorizedException(
			                    String.format("Failed! %s", applicationModel.getAppname())
			            ));
			        })
					.bodyToMono(ApplicationModel.class) //convert Response
					.block(); //do as Synchronous call
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		if (applicationModelResponse != null) {
			return ResponseEntity.ok(applicationModelResponse);
		}
		
		return ResponseEntity.badRequest().body(null);
		
		
	}

	@Override
	@Operation(description = "get an application")
	public ResponseEntity<ApplicationWithoutJwtModel> getApp(String appname) {
		ApplicationWithoutJwtModel response = null;
		
		try {
			response = webClientBuilder.build()
					.get() //RequestMethod
					.uri(authURL+"/app/" + appname)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.retrieve() //run command
					.bodyToMono(ApplicationWithoutJwtModel.class) //convert Response
					.block(); //do as Synchronous call
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		

		return ResponseEntity.ok(response);

	}

	@Override
	@Operation(description = "update an application")
	public ResponseEntity<ApplicationModel> updateApplication(@Valid UpdateApplicationModel applicationModel) {
		ApplicationModel applicationModelResponse = null;
		
		try {
			applicationModelResponse = webClientBuilder.build()
					.put() //RequestMethod
					.uri(authURL+"/app")
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(BodyInserters.fromObject(applicationModel))
					.retrieve() //run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
			            return Mono.error(new NotAuthorizedException(
			                    String.format("Failed! %s", applicationModel.getAppname())
			            ));
			        })
					.bodyToMono(ApplicationModel.class) //convert Response
					.block(); //do as Synchronous call
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		if (applicationModelResponse != null) {
			return ResponseEntity.ok(applicationModelResponse);
		}
		
		return ResponseEntity.badRequest().body(null);
	}

	@Override
	@Operation(description = "verify user for an application")
	public ResponseEntity<VerifiedModel> verifyUserForApp(String appname, String username) {
		VerifiedModel response = null;
		
		try {
			response = webClientBuilder.build()
					.get() //RequestMethod
					.uri(authURL+"/app/" + appname + "/" + username)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.retrieve() //run command
					.bodyToMono(VerifiedModel.class) //convert Response
					.block(); //do as Synchronous call
		}catch(Exception e) {
			e.printStackTrace();
		}
		

		return ResponseEntity.ok(response);
	}
	
	@Override
	@Operation(description = "Add User to an app")
	public ResponseEntity<UpdatedModel> addUser2App(String appname, String username) {
		UpdatedModel response = null;
		
		try {
			response = webClientBuilder.build()
					.post() //RequestMethod
					.uri(authURL+"/app/" + appname + "/" + username)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.retrieve() //run command
					.bodyToMono(UpdatedModel.class) //convert Response
					.block(); //do as UpdatedModel call
		}catch(Exception e) {
			e.printStackTrace();
		}
		

		return ResponseEntity.ok(response);
	}

	@Override
	@Operation(description = "get all apps")
	public ResponseEntity<List<ApplicationResponseModel>> getApplications() {
		List<ApplicationResponseModel> response = null;
		
		try {
			response = webClientBuilder.build()
					.get() //RequestMethod
					.uri(authURL+"/app")
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.retrieve() //run command
					.bodyToFlux(ApplicationResponseModel.class).collectList().block(); //convert Response
		}catch(Exception e) {
			e.printStackTrace();
		}
		

		return ResponseEntity.ok(response);	
	}

	@Override
	public ResponseEntity<List<ApplicationWithoutJwtModel>> getAppOfUser(String username) {
		List<ApplicationWithoutJwtModel> response = null;
		
		try {
			response = webClientBuilder.build()
					.get() //RequestMethod
					.uri(authURL+"/appOfUser/" + username)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.retrieve() //run command
					.bodyToFlux(ApplicationWithoutJwtModel.class).collectList().block(); //convert Response
		}catch(Exception e) {
			e.printStackTrace();
		}
		

		return ResponseEntity.ok(response);	
	}

	

}
