package com.tyss.surveyapp.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tyss.surveyapp.dto.AdminResponse;
import com.tyss.surveyapp.exceptions.AdminException;

@RestControllerAdvice
public class AdminControllerAdvice {

	@ExceptionHandler(AdminException.class)
	public AdminResponse handlerException(AdminException e) {
		AdminResponse adminResponse=new AdminResponse();
		adminResponse.setStatusCode(501);
		adminResponse.setMessage(e.getMessage());
		return adminResponse;
	}
}
