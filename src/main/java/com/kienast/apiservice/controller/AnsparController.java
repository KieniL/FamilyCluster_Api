package com.kienast.apiservice.controller;

import java.util.List;

import javax.validation.Valid;

import com.kienast.apiservice.exception.BadRequestException;
import com.kienast.apiservice.rest.api.AnsparenApi;
import com.kienast.apiservice.rest.api.model.AnsparEntryModel;
import com.kienast.apiservice.rest.api.model.CategoryResponseModel;

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
public class AnsparController implements AnsparenApi{
	
	//Used for WebTemplate
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Value("${ansparURL}")
	private String ansparURL;

	@Override
	@Operation(description = "addEntry")
	public ResponseEntity<AnsparEntryModel> addEntry(@Valid AnsparEntryModel ansparEntryModel) {
		AnsparEntryModel entryResponse = null;
		
		try {
			entryResponse = webClientBuilder.build()
					.post() //RequestMethod
					.uri(ansparURL+"/ansparen")
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(BodyInserters.fromObject(ansparEntryModel))
					.retrieve() //run command
					.onStatus(HttpStatus::is4xxClientError, response -> {
			            return Mono.error(new BadRequestException(
			                    String.format("Failed! %s", ansparEntryModel.getDescription())
			            ));
			        })
					.bodyToMono(AnsparEntryModel.class) //convert Response
					.block(); //do as Synchronous call
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		if (entryResponse != null) {
			return ResponseEntity.ok(entryResponse);
		}
		
		return ResponseEntity.badRequest().body(null);
	}

	@Override
	@Operation(description = "get Entries")
	public ResponseEntity<List<CategoryResponseModel>> getCategories() {
		List<CategoryResponseModel> response = null;
		
		try {
			response = webClientBuilder.build()
					.get() //RequestMethod
					.uri(ansparURL+"/ansparen")
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.retrieve() //run command
					.bodyToFlux(CategoryResponseModel.class).collectList().block(); //convert Response
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		return ResponseEntity.ok(response);
	}

	@Override
	@Operation(description = "get Entry by categoryName")
	public ResponseEntity<CategoryResponseModel> getCategory(String description) {
		CategoryResponseModel response = null;
		
		try {
			response = webClientBuilder.build()
					.get() //RequestMethod
					.uri(ansparURL+"/ansparen/" + description)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.retrieve() //run command
					.bodyToMono(CategoryResponseModel.class)
					.block();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		return ResponseEntity.ok(response);
	}

}
