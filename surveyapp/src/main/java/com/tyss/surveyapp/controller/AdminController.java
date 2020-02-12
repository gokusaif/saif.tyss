package com.tyss.surveyapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.surveyapp.dto.AdminResponse;
import com.tyss.surveyapp.dto.Question;
import com.tyss.surveyapp.dto.Survey;
import com.tyss.surveyapp.dto.SurveyResponse;
import com.tyss.surveyapp.service.AdminServices;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	AdminServices adminServices;

	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse addQuestions(@RequestBody Survey questions) {
		AdminResponse adminResponse = new AdminResponse();
		if (adminServices.addQuestions(questions)) {
			adminResponse.setStatusCode(201);
			adminResponse.setMessage("Success");
		}
		return adminResponse;
	}

	@GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse retrieveSurveys() {
		AdminResponse adminResponse = new AdminResponse();
		List<Survey> list = adminServices.retrive();
		if (list.size() > 0) {
			adminResponse.setStatusCode(201);
			adminResponse.setMessage("Success");
			adminResponse.setSurveys(list);
			adminResponse.setCount(list.size());
			adminResponse.setOffset(0);
			if (list.size() > 1) {
				adminResponse.setLimit(list.size() - 1);
			}
			adminResponse.setLimit(list.size());
		} else {
			adminResponse.setStatusCode(406);
			adminResponse.setMessage("Surveys does not exist");
		}
		return adminResponse;
	}

	@GetMapping(path = "/get/{surveyName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse retrieveSurvey(@PathVariable String surveyName) {
		AdminResponse adminResponse = new AdminResponse();
		Survey survey = adminServices.retriveSurvey(surveyName);
		if (survey != null) {
			adminResponse.setStatusCode(201);
			adminResponse.setMessage("Success");
			adminResponse.setSurvey(survey);
			List<Question> questions = survey.getQuestions();
			adminResponse.setCount(questions.size());
			adminResponse.setOffset(0);
			if (questions.size() > 1) {
				adminResponse.setLimit(questions.size() - 1);
			}
			adminResponse.setLimit(questions.size());
		}
		return adminResponse;
	}

	@DeleteMapping(path = "/delete/{surveyName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse deleteSurvey(@PathVariable String surveyName) {
		AdminResponse adminResponse = new AdminResponse();
		if (adminServices.removeSurvey(surveyName)) {
			adminResponse.setStatusCode(201);
			adminResponse.setMessage("Survey Deleted");
		}
		return adminResponse;
	}

	@PostMapping(path = "/answer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse answerSurvey(@RequestBody SurveyResponse answer) {
		AdminResponse adminResponse = new AdminResponse();
		if (adminServices.answerSurvey(answer)) {
			adminResponse.setStatusCode(201);
			adminResponse.setMessage("Survey Submitted");
		}
		return adminResponse;
	}

	@GetMapping(path = "/answer/{userEmail}/{surveyName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse getAnswered(@PathVariable String userEmail, @PathVariable String surveyName) {
		AdminResponse adminResponse = new AdminResponse();
		SurveyResponse answered = adminServices.getAnswered(userEmail, surveyName);
		if (answered != null) {
			adminResponse.setStatusCode(201);
			adminResponse.setMessage("Survey reply");
			adminResponse.setAnswered(answered);
		} else {
			adminResponse.setStatusCode(405);
			adminResponse.setMessage("Answered survey not found");
		}
		return adminResponse;
	}

	@DeleteMapping(path = "/answer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse deleteAnswered(@PathVariable int id) {
		AdminResponse adminResponse = new AdminResponse();
		if (adminServices.removeAnswered(id)) {
			adminResponse.setStatusCode(201);
			adminResponse.setMessage("Deleted");
		}
		return adminResponse;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(path = "/getquestion/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getQuestion(@PathVariable int id) {
		Question question = adminServices.getQuestion(id);
		if (question != null) {
			return new ResponseEntity<Question>(question, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity("Question not found", HttpStatus.BAD_REQUEST);
//		return question.getOptions();
	}

}
