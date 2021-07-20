package com.kienast.apiservice.dto;

import com.kienast.apiservice.model.Token;
import com.kienast.apiservice.rest.api.model.JWTTokenModel;

public class TokenAdapter {
	private String token;
	private String username;

	public TokenAdapter(Token token, String username) {
		this.token = token.getToken();
		this.username = username;
	}

	public JWTTokenModel createJson() {
		return new JWTTokenModel().jwt(token).username(username);
	}
}
