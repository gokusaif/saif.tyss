package com.tyss.surveyapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
//
	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse addQuestions(@RequestBody Questions questions) {
		AdminResponse adminResponse = new AdminResponse();
		if (adminServices.addQuestions(questions)) {
			adminResponse.setStatusCode(201);
			adminResponse.setMessage("Success");
		} else {
			adminResponse.setStatusCode(406);
			adminResponse.setMessage("Failure survey name already exists");
		}
		return adminResponse;
	}

	@GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse retrieveSurveys() {
		AdminResponse adminResponse = new AdminResponse();
		List<Questions> list = adminServices.retrive();
		if( list.size() > 0  ) {
			adminResponse.setStatusCode(201);
			adminResponse.setMessage("Success");
			adminResponse.setSurveys(list);			
		} else {
			adminResponse.setStatusCode(406);
			adminResponse.setMessage("Surveys does not exist");
		}
		return adminResponse;
	}
	
	@GetMapping(path = "/get/{surveyName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse retrieveSurvey(@PathVariable String surveyName ) {
		AdminResponse adminResponse = new AdminResponse();
		Questions survey = adminServices.retriveSurvey(surveyName);
		if(survey != null) {
			adminResponse.setStatusCode(201);
			adminResponse.setMessage("Success");
			adminResponse.setSurvey(survey);			
		} else {
			adminResponse.setStatusCode(406);
			adminResponse.setMessage("Survey does not exist");
		}
		return adminResponse;
	}
	
	@DeleteMapping(path = "/delete/{surveyName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse deleteSurvey(@PathVariable String surveyName ) {
		AdminResponse adminResponse = new AdminResponse();
		if(adminServices.removeSurvey(surveyName)) {
			adminResponse.setStatusCode(201);
			adminResponse.setMessage("Survey Deleted");
		} else {
			adminResponse.setStatusCode(406);
			adminResponse.setMessage("Survey does not exist");
		}
		return adminResponse;
	}
	
	


}
