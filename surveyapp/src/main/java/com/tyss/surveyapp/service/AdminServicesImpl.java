package com.tyss.surveyapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.surveyapp.dao.AdminDao;
import com.tyss.surveyapp.dto.Questions;

@Service
public class AdminServicesImpl implements AdminServices{
	
	@Autowired
	AdminDao adminDao;

	@Override
	public boolean addQuestions(Questions questions) {
		
		return adminDao.addQuestions(questions);
	}

	@Override
	public List<Questions> retrive() {
		
		return adminDao.retrive();
	}

	@Override
	public Questions retriveSurvey(String surveyName) {
		
		return adminDao.retriveSurvey(surveyName);
	}

	@Override
	public boolean removeSurvey(String surveyName) {
		
		return adminDao.removeSurvey(surveyName);
	}



	
}
