package com.tyss.surveyapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.tyss.surveyapp.dto.AuthenticationDto;

@Repository
public class AuthenticationDaoImpl implements AuthenticationDao{

	@PersistenceUnit
	EntityManagerFactory entityManagerFactory;
	
	@Override
	public boolean addUser(AuthenticationDto authenticationDto) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		try {
			transaction.begin();
             entityManager.persist(authenticationDto);
			transaction.commit();
			return true;
		}catch (Exception e) {
			transaction.rollback();
		}
		return false;
	}
	

	@Override
	public boolean deleteUser(AuthenticationDto authenticationDto) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction transaction=entityManager.getTransaction();
		try {
		String deleteUser="delete from AuthenticationDto where userName=:userName and password=:password"; 
		Query query=entityManager.createQuery(deleteUser);
		query.setParameter("userName", authenticationDto.getUserName());
		query.setParameter("password", authenticationDto.getPassword());
		transaction.begin();
		
		
		}catch (Exception e) {
			transaction.rollback();
		}
		
		return false;
	}

	@Override
	public AuthenticationDto getUser(AuthenticationDto authenticationDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUser(AuthenticationDto authenticationDto) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
}
