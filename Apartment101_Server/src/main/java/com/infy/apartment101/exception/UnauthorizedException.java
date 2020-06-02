package com.infy.apartment101.exception;

public class UnauthorizedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	public UnauthorizedException(String msg) {
		this.setMessage(msg);
	}
	
	public String getMessage() {
		return msg;
	}

	public void setMessage(String msg) {
		this.msg = msg;
	}
}
