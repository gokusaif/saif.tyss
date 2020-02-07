package com.tyss.surveyapp.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
 

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tyss.surveyapp.dao.AdminDao;
import com.tyss.surveyapp.dto.Question;
import com.tyss.surveyapp.dto.Survey;

@SpringBootTest
class AdminServicesTest {

	@InjectMocks
	AdminServicesImpl services;

	@Mock
	AdminDao dao;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testAddQuestions() {
		List<String> options = new ArrayList<String>();

		options.add("Red");
		options.add("Red");
		options.add("Red");
		options.add("Red");

		Question question = new Question();
		question.setQuestionName("what is our name?");
		question.setQuestionType("MCQ");
		question.setOptions(options);

		Survey survey = new Survey();
		survey.setSurveyName("Names");
		survey.setQuestions(Arrays.asList(question));

		dao.addQuestions(survey);

		verify(dao).addQuestions(survey);

	}
	
	@Test
	void testRetrive() {
		List<String> options = new ArrayList<String>();

		options.add("Red");
		options.add("Red");
		options.add("Red");
		options.add("Red");

		Question question = new Question();
		question.setQuestionName("what is our name?");
		question.setQuestionType("MCQ");
		question.setOptions(options);

		Survey survey = new Survey();
		survey.setSurveyName("Names");
		survey.setQuestions(Arrays.asList(question));
		
		
		dao.retrive();
		verify(dao,times(1)).retrive();
	}

	@Test
	void testRetriveSurvey() {
		List<String> options = new ArrayList<String>();

		options.add("Red");
		options.add("Red");
		options.add("Red");
		options.add("Red");

		Question question = new Question();
		question.setQuestionName("what is our name?");
		question.setQuestionType("MCQ");
		question.setOptions(options);
		question.setQuestionId(1);

		Survey survey = new Survey();
		survey.setSurveyName("Names");
		survey.setQuestions(Arrays.asList(question));
		when(dao.retrive()).thenReturn(Arrays.asList(survey));
		
		
		List<Survey> list=services.retrive();
		assertEquals(1, list.size());
		verify(dao,times(1)).retrive();
	}

//	@Test
//	void testRemoveSurvey() {
//		try {
//			boolean flag = services.removeSurvey("Names");
//			assertEquals(flag, true);
//		} catch (Exception e) {
//			assertThrows(AdminException.class, () -> {
//				services.removeSurvey("Names");
//			});
//		}
//	}
//
//	@Test
//	void testAnswerSurvey() {
//		
//	}
//
//	@Test
//	void testGetAnswered() {
//		fail("Not yet implemented");
//	}

}
