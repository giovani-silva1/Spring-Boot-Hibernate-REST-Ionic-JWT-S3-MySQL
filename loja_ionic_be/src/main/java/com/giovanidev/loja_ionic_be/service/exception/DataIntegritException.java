package com.giovanidev.loja_ionic_be.service.exception;

public class DataIntegritException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataIntegritException(String msg) {
		super(msg);
	}

	public DataIntegritException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
