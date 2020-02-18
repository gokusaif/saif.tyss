package com.onebill.customermanagement.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.onebill.customermanagement.dto.Response;
import com.onebill.customermanagement.exceptions.CustomException;

@RestControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler(CustomException.class)
	public Response handelException(CustomException e) {
		Response response = new Response();
		response.setStatusCode(501);
		response.setMessage(e.getMessage());
		return response;
	}

}
