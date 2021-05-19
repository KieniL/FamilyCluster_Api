package com.kienast.apiservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason = "Bad Request")
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String name;
	

	public BadRequestException(String name) {
		this.name = name;
	}
	
	

	public String getName() {
		return name;
	}

}

