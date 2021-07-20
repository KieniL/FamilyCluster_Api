package com.kienast.apiservice.controller;

import java.util.List;

import javax.validation.Valid;

import com.kienast.apiservice.config.IntializeLogInfo;
import com.kienast.apiservice.exception.BadRequestException;
import com.kienast.apiservice.rest.api.CertApi;
import com.kienast.apiservice.rest.api.model.CertificationModel;

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

import reactor.core.publisher.Mono;

@RestController
public class CertificationController implements CertApi {

	// Used for WebTemplate
	@Autowired
	private WebClient.Builder webClientBuilder;

	@Value("${certURL}")
	private String certURL;

	@Value("${logging.level.com.kienast.apiservice}")
	private String loglevel;

	private static Logger logger = LogManager.getLogger(CertificationController.class.getName());

	@Override
	public ResponseEntity<CertificationModel> addCertifaction(String JWT, String xRequestID, String SOURCE_IP,
			@Valid CertificationModel certificationModel) {
		CertificationModel entryResponse = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);
		logger.info("Got Request (Add Certification)");

		try {
			logger.info("Call Certification Microservice");
			entryResponse = webClientBuilder.build().post() // RequestMethod
					.uri(certURL + "/cert").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).header("JWT", JWT)
					.header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP)
					.body(BodyInserters.fromObject(certificationModel)).retrieve() // run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
						return Mono.error(new BadRequestException(String.format("Failed! %s", certificationModel.getShortname())));
					}).bodyToMono(CertificationModel.class) // convert Response
					.block(); // do as Synchronous call
		} catch (Exception e) {
			logger.error("Error occured: " + e.getMessage());
			throw e;
		}

		if (entryResponse != null) {
			logger.info("Adding was scuccessfull");
			return ResponseEntity.ok(entryResponse);
		}

		logger.info("Adding was not scuccessfull");
		return ResponseEntity.badRequest().body(null);
	}

	@Override
	public ResponseEntity<CertificationModel> getCertification(String JWT, String xRequestID, String SOURCE_IP,
			String shortname) {
		CertificationModel response = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);
		logger.info("Got Request (get Certification) for " + shortname);

		try {
			logger.info("Call Certification Microservice");
			response = webClientBuilder.build().get() // RequestMethod
					.uri(certURL + "/cert/" + shortname).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.header("JWT", JWT).header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP).retrieve() // run
																																																						// command
					.bodyToMono(CertificationModel.class).block();
		} catch (Exception e) {
			logger.error("Error occured: " + e.getMessage());
		}

		logger.info("Retrieval was scuccessfull");
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<List<CertificationModel>> getCertifications(String JWT, String xRequestID, String SOURCE_IP) {
		List<CertificationModel> response = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);
		logger.info("Got Request (get Certifications)");

		try {
			logger.info("Call Certification Microservice");
			response = webClientBuilder.build().get() // RequestMethod
					.uri(certURL + "/cert").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).header("JWT", JWT)
					.header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP).retrieve() // run command
					.bodyToFlux(CertificationModel.class).collectList().block(); // convert Response
		} catch (Exception e) {
			logger.error("Error occured: " + e.getMessage());
		}

		logger.info("Retrieval was scuccessfull");
		return ResponseEntity.ok(response);
	}

}
