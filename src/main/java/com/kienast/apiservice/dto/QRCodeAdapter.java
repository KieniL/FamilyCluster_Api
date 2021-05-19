package com.kienast.apiservice.dto;

import com.kienast.apiservice.model.QRCode;
import com.kienast.apiservice.rest.api.model.QRCodeModel;

public class QRCodeAdapter {
	private String qrCode;

	public QRCodeAdapter(QRCode qrCodeC) {
		this.qrCode = qrCodeC.getQrCode();
	}

	public QRCodeModel createJson() {
		return new  QRCodeModel().qrcode(qrCode);
	}
}
