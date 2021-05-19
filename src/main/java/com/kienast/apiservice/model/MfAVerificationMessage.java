package com.kienast.apiservice.model;

public class MfAVerificationMessage {

	private String verificationMessage;
	
	public MfAVerificationMessage() {
		
	}
	
	public MfAVerificationMessage(String verificationMessage) {
		this.verificationMessage = verificationMessage;
	}
	
	
	public String getVerificationMessage() {
		return verificationMessage;
	}
	public void setVerificationMessage(String verificationMessage) {
		this.verificationMessage = verificationMessage;
	}

	
}
