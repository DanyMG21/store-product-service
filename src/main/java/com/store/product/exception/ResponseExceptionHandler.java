package com.store.product.exception;


import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.log4j.Log4j2;

import com.store.product.model.Error;

@Log4j2
@ControllerAdvice //automaticamente se pueden interseptar excepciones
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request) {
		log.info(ex.getAllErrors());
		
		Error error = new Error();
		
		String message = ex.getAllErrors()
				.stream()
				.map(x->x.getDefaultMessage().concat(", "))
				.collect(Collectors.joining());
		
		error.setMessage(message);
		
		//return super.handleMethodArgumentNotValid(ex, headers, status, request);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

}
