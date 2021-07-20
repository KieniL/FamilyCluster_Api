package com.kienast.apiservice.controller;

import java.util.List;

import javax.validation.Valid;

import com.kienast.apiservice.config.IntializeLogInfo;
import com.kienast.apiservice.exception.NotAuthorizedException;
import com.kienast.apiservice.rest.api.AppApi;
import com.kienast.apiservice.rest.api.AppOfUserApi;
import com.kienast.apiservice.rest.api.model.ApplicationModel;
import com.kienast.apiservice.rest.api.model.ApplicationResponseModel;
import com.kienast.apiservice.rest.api.model.UpdateApplicationModel;
import com.kienast.apiservice.rest.api.model.UpdatedModel;
import com.kienast.apiservice.rest.api.model.VerifiedModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	// Used for WebTemplate
	@Autowired
	private WebClient.Builder webClientBuilder;

	@Value("${authURL}")
	private String authURL;

	@Value("${logging.level.com.kienast.apiservice}")
	private String loglevel;

	private static Logger logger = LogManager.getLogger(AppController.class.getName());

	@Override
	@Operation(description = "Add an application")
	public ResponseEntity<ApplicationModel> addApplication(String JWT, String xRequestID, String SOURCE_IP,
			@Valid ApplicationModel applicationModel) {

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);
		logger.info("API: Got Request (Add Applicaiton)");

		ApplicationModel applicationModelResponse = null;

		try {
			logger.info("API: Call Authentication Microservice");
			applicationModelResponse = webClientBuilder.build().post() // RequestMethod
					.uri(authURL + "/app").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).header("JWT", JWT)
					.header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP)
					.body(BodyInserters.fromObject(applicationModel)).retrieve() // run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
						return Mono.error(new NotAuthorizedException(String.format("Failed! %s", applicationModel.getAppname())));
					}).bodyToMono(ApplicationModel.class) // convert Response
					.block(); // do as Synchronous call
		} catch (Exception e) {
			logger.error("error occured: " + e.getMessage());
			throw e;
		}

		if (applicationModelResponse != null) {
			logger.info("API: Adding was succcessfull");
			return ResponseEntity.ok(applicationModelResponse);
		}

		logger.info("API: Adding was not succcessfull");
		return ResponseEntity.badRequest().body(null);

	}

	@Override
	@Operation(description = "get an application")
	public ResponseEntity<ApplicationModel> getApp(String appname, String JWT, String xRequestID, String SOURCE_IP) {
		ApplicationModel response = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);
		logger.info("API: Got Request (Get Applicaiton)");

		try {
			logger.info("API: Call Authentication Microservice");
			response = webClientBuilder.build().get() // RequestMethod
					.uri(authURL + "/app/" + appname).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.header("JWT", JWT).header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP).retrieve() // run
					.bodyToMono(ApplicationModel.class) // convert Response
					.block(); // do as Synchronous call
		} catch (Exception e) {
			logger.error("error occured: " + e.getMessage());
		}

		logger.info("API: Retrieval was succcessfull");
		return ResponseEntity.ok(response);

	}

	@Override
	@Operation(description = "update an application")
	public ResponseEntity<ApplicationModel> updateApplication(String JWT, String xRequestID, String SOURCE_IP,
			@Valid UpdateApplicationModel applicationModel) {
		ApplicationModel applicationModelResponse = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);
		logger.info("API: Got Request (Update Applicaiton)");

		try {
			logger.info("API: Call Authentication Microservice");
			applicationModelResponse = webClientBuilder.build().put() // RequestMethod
					.uri(authURL + "/app").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).header("JWT", JWT)
					.header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP)
					.body(BodyInserters.fromObject(applicationModel)).retrieve() // run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
						return Mono.error(new NotAuthorizedException(String.format("Failed! %s", applicationModel.getAppname())));
					}).bodyToMono(ApplicationModel.class) // convert Response
					.block(); // do as Synchronous call
		} catch (Exception e) {
			logger.error("error occured: " + e.getMessage());
			throw e;
		}

		if (applicationModelResponse != null) {
			logger.info("API: Update was succcessfull");
			return ResponseEntity.ok(applicationModelResponse);
		}

		logger.info("API: Update was not succcessfull");
		return ResponseEntity.badRequest().body(null);
	}

	@Override
	@Operation(description = "verify user for an application")
	public ResponseEntity<VerifiedModel> verifyUserForApp(String appname, String username, String JWT, String xRequestID,
			String SOURCE_IP) {
		VerifiedModel response = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);
		logger.info("API: Got Request (Verify User for an Applicaiton)");

		try {
			logger.info("API: Call Authentication Microservice");
			response = webClientBuilder.build().get() // RequestMethod
					.uri(authURL + "/app/" + appname + "/" + username).header("JWT", JWT).header("X-Request-ID", xRequestID)
					.header("SOURCE_IP", SOURCE_IP).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).retrieve() // run
					.bodyToMono(VerifiedModel.class) // convert Response
					.block(); // do as Synchronous call
		} catch (Exception e) {
			logger.error("error occured: " + e.getMessage());
		}

		logger.info("API: Verification was succcessfull");
		return ResponseEntity.ok(response);
	}

	@Override
	@Operation(description = "Add User to an app")
	public ResponseEntity<UpdatedModel> addUser2App(String appname, String username, String JWT, String xRequestID,
			String SOURCE_IP) {
		UpdatedModel updatedResponse = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);
		logger.info("API: Got Request (Add User to an Applicaiton)");

		try {
			logger.info("API: Call Authentication Microservice");
			updatedResponse = webClientBuilder.build().post() // RequestMethod
					.uri(authURL + "/app/" + appname + "/" + username)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).header("JWT", JWT)
					.header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP).retrieve() // run command
					.onStatus(HttpStatus::is5xxServerError, response -> {
						return Mono.error(
								new NotAuthorizedException(String.format("Failed to updated user %s in app %s", username, appname)));
					}).bodyToMono(UpdatedModel.class) // convert Response
					.block(); // do as UpdatedModel call
		} catch (Exception e) {
			logger.error("error occured: " + e.getMessage());
		}

		logger.info("API: Adding was succcessfull");
		return ResponseEntity.ok(updatedResponse);
	}

	@Override
	@Operation(description = "get all apps")
	public ResponseEntity<List<ApplicationResponseModel>> getApplications(String JWT, String xRequestID,
			String SOURCE_IP) {
		List<ApplicationResponseModel> response = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);
		logger.info("API: Got Request (Get Applicaitons)");

		try {
			logger.info("API: Call Authentication Microservice");
			response = webClientBuilder.build().get() // RequestMethod
					.uri(authURL + "/app").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).header("JWT", JWT)
					.header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP).retrieve() // run command
					.bodyToFlux(ApplicationResponseModel.class).collectList().block(); // convert Response
		} catch (Exception e) {
			logger.error("error occured: " + e.getMessage());
		}

		logger.info("API: Retrieval was succcessfull");
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<List<ApplicationModel>> getAppOfUser(String username, String JWT, String xRequestID,
			String SOURCE_IP) {
		List<ApplicationModel> response = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);
		logger.info("API: Got Request (Get Applicaitons of User)");

		try {
			logger.info("API: Call Authentication Microservice");
			response = webClientBuilder.build().get() // RequestMethod
					.uri(authURL + "/appOfUser/" + username).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.header("JWT", JWT).header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP).retrieve() // run
																																																						// command
					.bodyToFlux(ApplicationModel.class).collectList().block(); // convert Response
		} catch (Exception e) {
			logger.error("error occured: " + e.getMessage());
		}

		logger.info("API: Retrieval was succcessfull");
		return ResponseEntity.ok(response);
	}

}
