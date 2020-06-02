package com.infy.apartment101.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
//@ControllerAdvice
public class GlobalExceptionHandler {
	
//	@ExceptionHandler
//	public String handleInvalidFieldException(InvalidFieldException e) {
//		return e.getMessage();
//	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler
	public String handleUnauthorizedException(UnauthorizedException e) {
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public String handleException(Exception e) {
		return e.getMessage();
	}
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(value = {Exception.class})
//	public String handleException(String msg) {
//		//return "400 - There was a Bad Request";
//		//return msg;
//		return msg;
//	}
}