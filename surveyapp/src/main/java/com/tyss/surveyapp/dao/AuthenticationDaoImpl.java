package com.tyss.surveyapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.tyss.surveyapp.dto.AuthenticationDto;
import com.tyss.surveyapp.exceptions.AdminException;

@Repository
public class AuthenticationDaoImpl implements AuthenticationDao {

	@PersistenceUnit
	EntityManagerFactory entityManagerFactory;

	@Override
	public boolean addUser(AuthenticationDto authenticationDto) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(authenticationDto);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			throw new AdminException("The user name that you have entered already exits !!");
		}

	}

	@Override
	public boolean deleteUser(AuthenticationDto authenticationDto) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			String deleteUser = "delete from AuthenticationDto where userName=:userName and password=:password";
			Query query = entityManager.createQuery(deleteUser);
			query.setParameter("userName", authenticationDto.getUserName());
			query.setParameter("password", authenticationDto.getPassword());
			transaction.begin();
			int delete = query.executeUpdate();
			if (delete > 0) {
				return true;
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new AdminException("User deletion failed, Username and password did`nt matched !!");
		}

		return false;
	}

	@Override
	public AuthenticationDto getUser(AuthenticationDto authenticationDto) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			String findUser = "from AuthenticationDto where userName=:userName";
			Query query = entityManager.createQuery(findUser);
			query.setParameter("userName", authenticationDto.getUserName());
			AuthenticationDto userFound = (AuthenticationDto) query.getSingleResult();
			if (userFound.getPassword().equals(authenticationDto.getPassword())) {
				return userFound;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new AdminException("Username does`nt exists !!");
		}
	}

	@Override
	public boolean updateUser(AuthenticationDto authenticationDto) {

		return false;
	}

}
