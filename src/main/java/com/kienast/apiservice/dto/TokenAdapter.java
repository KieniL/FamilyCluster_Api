package com.kienast.apiservice.dto;

import com.kienast.apiservice.model.Token;
import com.kienast.apiservice.rest.api.model.TokenModel;

public class TokenAdapter {
	private String token;
	private String username;

	public TokenAdapter(Token token, String username) {
		this.token = token.getToken();
		this.username = username;
	}

	public TokenModel createJson() {
		return new TokenModel().token(token).username(username);
	}
}
