package com.ims.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.exceptions.CustomException;

@EnableWebMvc
@ControllerAdvice
public class GlobalControllerExceptionHandler {
	
	   @ExceptionHandler(CustomException.class)
	   public ResponseEntity<String> handleErrors(CustomException customException) {
		      String errorMessage = customException.getMessage();
		      return new ResponseEntity<String>(errorMessage,HttpStatus.OK);
	   }

}
