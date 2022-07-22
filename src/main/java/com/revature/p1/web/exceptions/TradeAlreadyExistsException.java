package com.revature.p1.web.exceptions;

public class TradeAlreadyExistsException extends Exception {
	private Error code;

	public TradeAlreadyExistsException(String message, Throwable cause, Error code) {
		
		super(message, cause);
		this.setCode(code);
	}

	public Error getCode() {
		return code;
	}

	public void setCode(Error code) {
		this.code = code;
	}

}
