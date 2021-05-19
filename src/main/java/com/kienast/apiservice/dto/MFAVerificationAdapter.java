package com.kienast.apiservice.dto;

import com.kienast.apiservice.model.MfAVerificationMessage;
import com.kienast.apiservice.rest.api.model.VerifiedModel;

public class MFAVerificationAdapter {
	private String verificationMessage;

	public MFAVerificationAdapter(MfAVerificationMessage mfaVerificaitonMessage) {
		this.verificationMessage = mfaVerificaitonMessage.getVerificationMessage();
	}

	public VerifiedModel createJson() {
		return new  VerifiedModel().verificationMessage(verificationMessage);
	}
}
