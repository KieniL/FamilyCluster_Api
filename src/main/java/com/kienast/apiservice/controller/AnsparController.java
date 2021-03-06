package com.kienast.apiservice.controller;

import java.util.List;

import javax.validation.Valid;

import com.kienast.apiservice.config.IntializeLogInfo;
import com.kienast.apiservice.exception.BadRequestException;
import com.kienast.apiservice.exception.NotAuthorizedException;
import com.kienast.apiservice.model.TokenVerificationResponse;
import com.kienast.apiservice.rest.api.AnsparenApi;
import com.kienast.apiservice.rest.api.model.AnsparEntryModel;
import com.kienast.apiservice.rest.api.model.CategoryResponseModel;

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
public class AnsparController implements AnsparenApi {

	// Used for WebTemplate
	@Autowired
	private WebClient.Builder webClientBuilder;

	@Value("${ansparURL}")
	private String ansparURL;

	@Value("${logging.level.com.kienast.apiservice}")
	private String loglevel;

	@Value("${authURL}")
	private String authURL;

	private static Logger logger = LogManager.getLogger(AnsparController.class.getName());

	@Override
	@Operation(description = "addEntry")
	public ResponseEntity<AnsparEntryModel> addEntry(String JWT, String xRequestID, String SOURCE_IP,
			@Valid AnsparEntryModel ansparEntryModel) {
		AnsparEntryModel entryResponse = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);
		logger.info("API: Got Request (Add Entry)");

		try {
			TokenVerificationResponse tokenResponse = new TokenVerificationResponse();

			logger.info("Call Authentication Microservice for JWT Verification");
			tokenResponse = webClientBuilder.build().post() // RequestMethod
					.uri(authURL + "/jwt").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).header("JWT", JWT)
					.header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP).retrieve() // run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
						return Mono.error(new NotAuthorizedException(String.format("Failed JWT Verification")));
					}).bodyToMono(TokenVerificationResponse.class) // convert Response
					.block(); // do as Synchronous call

			IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, tokenResponse.getUserId(), loglevel);
			logger.info("Added userId to log");

		} catch (Exception e) {
			logger.error("Error on verifiying jwt");
			throw new NotAuthorizedException(JWT);
		}

		try {
			logger.info("API: Call AnsparMicroservice");
			entryResponse = webClientBuilder.build().post() // RequestMethod
					.uri(ansparURL + "/ansparen").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.header("JWT", JWT).header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP)
					.body(BodyInserters.fromObject(ansparEntryModel)).retrieve() // run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
						return Mono.error(new BadRequestException(String.format("Failed! %s", ansparEntryModel.getDescription())));
					}).bodyToMono(AnsparEntryModel.class) // convert Response
					.block(); // do as Synchronous call
		} catch (Exception e) {
			logger.error("Error occured: " + e.getMessage());
			throw e;
		}

		if (entryResponse != null) {
			logger.info("API: Adding was successfull");
			return ResponseEntity.ok(entryResponse);
		}

		logger.debug("API: Adding was not successfull");
		return ResponseEntity.badRequest().body(null);
	}

	@Override
	@Operation(description = "get Entries")
	public ResponseEntity<List<CategoryResponseModel>> getCategories(String JWT, String xRequestID, String SOURCE_IP) {
		List<CategoryResponseModel> categoryResponse = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);
		logger.info("API: Got Request (Get Entries)");

		try {
			TokenVerificationResponse tokenResponse = new TokenVerificationResponse();

			logger.info("Call Authentication Microservice for JWT Verification");
			tokenResponse = webClientBuilder.build().post() // RequestMethod
					.uri(authURL + "/jwt").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).header("JWT", JWT)
					.header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP).retrieve() // run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
						return Mono.error(new NotAuthorizedException(String.format("Failed JWT Verification")));
					}).bodyToMono(TokenVerificationResponse.class) // convert Response
					.block(); // do as Synchronous call

			IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, tokenResponse.getUserId(), loglevel);
			logger.info("Added userId to log");

			logger.info("API: Call AnsparMicroservice");
			categoryResponse = webClientBuilder.build().get() // RequestMethod
					.uri(ansparURL + "/ansparen").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.header("JWT", JWT).header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP).retrieve() // run
					.bodyToFlux(CategoryResponseModel.class).collectList().block(); // convert Response

		} catch (Exception e) {
			logger.error("Error occured: " + e.getMessage());
		}

		logger.info("API: Retrieval was successfull");
		return ResponseEntity.ok(categoryResponse);
	}

	@Override
	@Operation(description = "get Entry by categoryName")
	public ResponseEntity<CategoryResponseModel> getCategory(String description, String JWT, String xRequestID,
			String SOURCE_IP) {
		CategoryResponseModel categoryResponse = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);
		logger.info("API: Got Request (Get Entry) for category " + description);

		try {
			TokenVerificationResponse tokenResponse = new TokenVerificationResponse();

			logger.info("Call Authentication Microservice for JWT Verification");
			tokenResponse = webClientBuilder.build().post() // RequestMethod
					.uri(authURL + "/jwt").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).header("JWT", JWT)
					.header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP).retrieve() // run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
						return Mono.error(new NotAuthorizedException(String.format("Failed JWT Verification")));
					}).bodyToMono(TokenVerificationResponse.class) // convert Response
					.block(); // do as Synchronous call

			IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, tokenResponse.getUserId(), loglevel);
			logger.info("Added userId to log");

			logger.info("API: Call AnsparMicroservice");
			categoryResponse = webClientBuilder.build().get() // RequestMethod
					.uri(ansparURL + "/ansparen/" + description)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).header("JWT", JWT)
					.header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP).retrieve() // run command
					.bodyToMono(CategoryResponseModel.class).block();

		} catch (Exception e) {
			logger.error("Error occured: " + e.getMessage());
		}

		logger.info("API: Retrieval was successfull");
		return ResponseEntity.ok(categoryResponse);
	}

}
