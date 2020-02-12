package com.tyss.surveyapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.surveyapp.dao.AdminDao;
import com.tyss.surveyapp.dto.Question;
import com.tyss.surveyapp.dto.Survey;
import com.tyss.surveyapp.dto.SurveyResponse;
import com.tyss.surveyapp.exceptions.AdminException;

@Service
public class AdminServicesImpl implements AdminServices {

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
		System.out.println(answer);
		@SuppressWarnings("unused")
		Survey survey = adminDao.retriveSurvey(answer.getSurveyName());
		SurveyResponse surveyResponse = adminDao.getAnswered(answer.getUserEmail(), answer.getSurveyName());
		if (surveyResponse == null) {
			return adminDao.answerSurvey(answer);
		}
		throw new AdminException("Response already exists cannot submit response again");
	}

	@Override
	public SurveyResponse getAnswered(String userEmail, String surveyName) {

		return adminDao.getAnswered(userEmail, surveyName);
	}

	@Override
	public boolean removeAnswered(int id) {

		return adminDao.removeAnswered(id);
	}

	@Override
	public Question getQuestion(int id) {
		
		return adminDao.getQuestion(id);
	}

}
