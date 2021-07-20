package com.kienast.apiservice.controller;

import javax.validation.Valid;

import com.kienast.apiservice.config.IntializeLogInfo;
import com.kienast.apiservice.exception.NotAuthorizedException;
import com.kienast.apiservice.rest.api.MfaApi;
import com.kienast.apiservice.rest.api.model.JWTTokenModel;
import com.kienast.apiservice.rest.api.model.MFATokenVerificationModel;
import com.kienast.apiservice.rest.api.model.QRCodeModel;
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
public class MfaController implements MfaApi {

	// Used for WebTemplate
	@Autowired
	private WebClient.Builder webClientBuilder;

	@Value("${authURL}")
	private String authURL;

	@Value("${logging.level.com.kienast.apiservice}")
	private String loglevel;

	@Override
	@Operation(description = "setup MFA")
	public ResponseEntity<QRCodeModel> mfaSetup(String JWT, String xRequestID, String SOURCE_IP,
			@Valid JWTTokenModel tokenModel) {
		QRCodeModel qrResponse = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);

		try {
			qrResponse = webClientBuilder.build().post() // RequestMethod
					.uri(authURL + "/mfa/setup").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.header("JWT", JWT).header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP)
					.body(BodyInserters.fromObject(tokenModel)).retrieve() // run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
						return Mono.error(new NotAuthorizedException(String.format("Failed! %s", tokenModel.getUsername())));
					}).bodyToMono(QRCodeModel.class) // convert Response
					.block(); // do as Synchronous call
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}

		if (qrResponse != null) {
			return ResponseEntity.ok(qrResponse);
		}

		return ResponseEntity.badRequest().body(null);
	}

	@Override
	@Operation(description = "verify MFA")
	public ResponseEntity<VerifiedModel> mfaVerify(String JWT, String xRequestID, String SOURCE_IP,
			@Valid MFATokenVerificationModel mfATokenVerificationModel) {
		VerifiedModel mfaVerificationResponse = null;

		IntializeLogInfo.initializeLogInfo(xRequestID, SOURCE_IP, "", loglevel);

		try {
			mfaVerificationResponse = webClientBuilder.build().post() // RequestMethod
					.uri(authURL + "/mfa/verify").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.header("JWT", JWT).header("X-Request-ID", xRequestID).header("SOURCE_IP", SOURCE_IP)
					.body(BodyInserters.fromObject(mfATokenVerificationModel)).retrieve() // run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
						return Mono.error(
								new NotAuthorizedException(String.format("Failed! %s", mfATokenVerificationModel.getUsername())));
					}).bodyToMono(VerifiedModel.class) // convert Response
					.block(); // do as Synchronous call
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}

		if (mfaVerificationResponse != null) {
			return ResponseEntity.ok(mfaVerificationResponse);
		}

		return ResponseEntity.badRequest().body(null);
	}

}
