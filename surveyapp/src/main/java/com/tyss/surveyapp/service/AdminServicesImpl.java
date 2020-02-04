package com.tyss.surveyapp.service;

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

}
