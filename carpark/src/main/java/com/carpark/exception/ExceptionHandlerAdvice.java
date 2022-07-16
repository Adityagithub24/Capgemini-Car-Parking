package com.carpark.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(DeleteParkingException.class)
	public ResponseEntity<String> deleteEmployee(DeleteParkingException dex)
	{
		return  new ResponseEntity<>(dex.getMessage(),HttpStatus.NOT_FOUND);
	}
}
