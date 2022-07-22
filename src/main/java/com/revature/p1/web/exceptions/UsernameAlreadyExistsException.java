package com.revature.p1.web.exceptions;

import java.sql.SQLException;

public class UsernameAlreadyExistsException extends Exception {
	
	private Error code;

	public UsernameAlreadyExistsException(String message, Throwable cause, Error code) {
		
		super(message, cause);
		this.code = code;
	}
	//for commit
}
