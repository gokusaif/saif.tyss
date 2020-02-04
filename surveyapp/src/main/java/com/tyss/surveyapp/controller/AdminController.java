package com.tyss.surveyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.surveyapp.dto.AdminResponse;
import com.tyss.surveyapp.dto.Questions;
import com.tyss.surveyapp.service.AdminServices;

@RestController
public class AdminController {
	
	@Autowired
	AdminServices adminServices;
	
	@PostMapping(path = "/add",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse addQuestions(@RequestBody Questions questions) {
		AdminResponse adminResponse = new AdminResponse();
		if(adminServices.addQuestions(questions)) {
			adminResponse.setStatusCode(201);
			adminResponse.setMessage("Success");
		} else {
			adminResponse.setStatusCode(406);
			adminResponse.setMessage("Failure");
		}
		return adminResponse;			
	}

}
