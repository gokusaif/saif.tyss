package com.tyss.surveyapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.surveyapp.dao.AdminDao;
import com.tyss.surveyapp.dto.Survey;
import com.tyss.surveyapp.dto.SurveyResponse;

@Service
public class AdminServicesImpl implements AdminServices{
	
	@Autowired
	AdminDao adminDao;

	@Override
	public boolean addQuestions(Survey questions) {
		
		return adminDao.addQuestions(questions);
	}

	@Override
	public List<Survey> retrive() {
		
		return adminDao.retrive();
	}

	@Override
	public Survey retriveSurvey(String surveyName) {
		
		return adminDao.retriveSurvey(surveyName);
	}

	@Override
	public boolean removeSurvey(String surveyName) {
		
		return adminDao.removeSurvey(surveyName);
	}

	@Override
	public boolean answerSurvey(SurveyResponse answer) {
		@SuppressWarnings("unused")
		Survey survey = adminDao.retriveSurvey(answer.getSurveyName());
		return adminDao.answerSurvey(answer);
	}

	@Override
	public SurveyResponse getAnswered(String userEmail,String surveyName) {
		
		return adminDao.getAnswered(userEmail,surveyName);
	}



	
}
