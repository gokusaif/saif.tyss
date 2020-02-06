package com.tyss.surveyapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tyss.surveyapp.dto.Survey;
import com.tyss.surveyapp.exceptions.AdminException;

@Repository
public class AdminDaoImpl implements AdminDao {

	@PersistenceUnit
	EntityManagerFactory factory;

	@Override
	public boolean addQuestions(Survey questions) {
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
	public List<Survey> retrive() {

		EntityManager manager = factory.createEntityManager();
		String jpql = "from Survey";
		TypedQuery<Survey> query = manager.createQuery(jpql, Survey.class);
		return query.getResultList();
	}

	@Override
	public Survey retriveSurvey(String surveyName) {
		EntityManager manager = factory.createEntityManager();
		Survey survey = null;
		try {
			survey = manager.find(Survey.class, surveyName);
			return survey;
		} catch (Exception e) {
			throw new AdminException("Surveyname is not found");
		}
		
	}

	@Override
	public boolean removeSurvey(String surveyName) {
		EntityManager manager = factory.createEntityManager();
		Survey survey = null;
		try {
			survey = manager.find(Survey.class, surveyName);
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
