package com.tyss.surveyapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tyss.surveyapp.dto.Questions;
import com.tyss.surveyapp.exceptions.AdminException;

@Repository
public class AdminDaoImpl implements AdminDao {

	@PersistenceUnit
	EntityManagerFactory factory;

	@Override
	public boolean addQuestions(Questions questions) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.persist(questions);
			transaction.commit();
			return true;
		} catch (Exception e) {
			throw new AdminException("Survey Name is already exists");
		}

	}

	@Override
	public List<Questions> retrive() {

		EntityManager manager = factory.createEntityManager();
		String jpql = "from Questions";
		TypedQuery<Questions> query = manager.createQuery(jpql, Questions.class);
		return query.getResultList();
	}

	@Override
	public Questions retriveSurvey(String surveyName) {
		EntityManager manager = factory.createEntityManager();
		Questions survey = null;
		try {
			survey = manager.find(Questions.class, surveyName);
			return survey;
		} catch (Exception e) {
			throw new AdminException("Surveyname is not found");
		}
		
	}

	@Override
	public boolean removeSurvey(String surveyName) {
		EntityManager manager = factory.createEntityManager();
		Questions survey = null;
		try {
			survey = manager.find(Questions.class, surveyName);
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.remove(survey);
			transaction.commit();
			return true;
		} catch (Exception e) {
			throw new AdminException("Surveyname is not found");
		}
	}
}
