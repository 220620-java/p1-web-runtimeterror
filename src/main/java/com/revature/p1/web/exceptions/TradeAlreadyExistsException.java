package com.revature.p1.web.exceptions;

public class TradeAlreadyExistsException extends Exception {
	private Error code;

	public TradeAlreadyExistsException(String message, Throwable cause, Error code) {
		
		super(message, cause);
		this.code = code;
	}

}
