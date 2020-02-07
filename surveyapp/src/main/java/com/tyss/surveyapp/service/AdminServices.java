package com.tyss.surveyapp.service;

import java.util.List;

import com.tyss.surveyapp.dto.SurveyResponse;
import com.tyss.surveyapp.dto.Survey;

public interface AdminServices {

	
	public boolean addQuestions(Survey questions);
	
	public List<Survey> retrive();	
	
	public Survey retriveSurvey(String surveyName);
	
	public boolean removeSurvey(String surveyName);
	
	public boolean answerSurvey(SurveyResponse answer);
	
	public SurveyResponse getAnswered(String userEmail,String surveyName);
}
