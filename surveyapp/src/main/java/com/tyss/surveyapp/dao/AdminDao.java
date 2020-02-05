package com.tyss.surveyapp.dao;

import java.util.List;

import com.tyss.surveyapp.dto.Questions;

public interface AdminDao {

	public boolean addQuestions(Questions questions);
	
	public List<Questions> retrive();
	
	public Questions retriveSurvey(String surveyName);
	
	public boolean removeSurvey(String surveyName);
	
}
