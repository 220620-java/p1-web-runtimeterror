package com.revature.p1.web.exceptions;

public class UsernameAlreadyExistsException extends Exception {
	private Error code;

	public UsernameAlreadyExistsException(String message, Throwable cause, Error code) {
		
		super(message, cause);
		this.setCode(code);
	}
	//for commit

	public Error getCode() {
		return code;
	}

	public void setCode(Error code) {
		this.code = code;
	}
}
