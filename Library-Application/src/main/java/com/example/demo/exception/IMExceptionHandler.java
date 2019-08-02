package com.example.demo.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RestControllerAdvice
public class IMExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(IMExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<?> handleExceptions(Exception ex, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage());
		logger.error("--Application was Error : "+ex.getMessage());
		// Log.info("ControllerAdvice -> ExceptionHandler : "+ex);
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.EXPECTATION_FAILED); 
		//return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR); 
	}

}
