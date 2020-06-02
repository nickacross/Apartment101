package com.infy.apartment101.exception;

public class InvalidFieldException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	public InvalidFieldException(String msg) {
		this.setMessage(msg);
	}
	
	public String getMessage() {
		return msg;
	}

	public void setMessage(String msg) {
		this.msg = msg;
	}
}
