package com.onebill.customermanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.onebill.customermanagement.dto.Customer;
import com.onebill.customermanagement.dto.Partner;

@Repository
public class DaoImpl implements Dao {

	@PersistenceUnit
	EntityManagerFactory factory;

	@Override
	public Partner addPartner(Partner partner) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.persist(partner);
			transaction.commit();
			return partner;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Customer addCustomer(Customer customer) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			Partner partner = manager.find(Partner.class, customer.getPartnerId());
			if (partner != null) {
				transaction.begin();
				List<Customer> customers = partner.getCustomers();
				customers.add(customer);
				customer.setPartner(partner);
				manager.persist(customer);
				transaction.commit();
				return customer;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Partner getPartner(int id) {
		EntityManager manager = factory.createEntityManager();
		Partner partner = manager.find(Partner.class, id);
		return partner;
	}

	@Override
	public Customer getCustomer(int id) {
		EntityManager manager = factory.createEntityManager();
		Customer customer = manager.find(Customer.class, id);
		customer.setPartnerId(customer.getPartner().getId());
		return customer;
	}

	@Override
	public List<Partner> getPartners() {
		EntityManager manager = factory.createEntityManager();
		String jpql = "from Partner";
		TypedQuery<Partner> query = manager.createQuery(jpql, Partner.class);
		List<Partner> partners = query.getResultList();
		if (partners.size() != 0) {
			return partners;
		}
		return null;
	}

	@Override
	public List<Customer> getCustomers() {
		EntityManager manager = factory.createEntityManager();
		String jpql = "from Customer";
		TypedQuery<Customer> query = manager.createQuery(jpql, Customer.class);
		List<Customer> customers = query.getResultList();
		if (customers.size() != 0) {
			return customers;
		}
		return null;
	}

	@Override
	public boolean deleteCustomer(int id) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		Customer customer = manager.find(Customer.class, id);
		transaction.begin();
		customer.setStatus("Inactive");
		transaction.commit();
		return true;
	}

	@Override
	public boolean deletePartner(int id) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		Partner partner = manager.find(Partner.class, id);
		transaction.begin();
		partner.setStatus("Inactive");
		transaction.commit();
		return true;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		Customer existing = manager.find(Customer.class, customer.getId());
		transaction.begin();
		existing.setName(customer.getName());
		existing.setDate(customer.getDate());
		existing.setAddresses(customer.getAddresses());
		existing.setEmails(customer.getEmails());
		existing.setPhoneNumbers(customer.getPhoneNumbers());
		existing.setPartner(customer.getPartner());
		transaction.commit();
		return true;
	}

	@Override
	public boolean updatePartner(Partner partner) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		Partner existing = manager.find(Partner.class, partner.getPartnerId());
		transaction.begin();
		existing.setName(partner.getName());
		existing.setDate(partner.getDate());
		existing.setAddresses(partner.getAddresses());
		existing.setEmails(partner.getEmails());
		existing.setPhoneNumbers(partner.getPhoneNumbers());
		transaction.commit();
		return true;
	}

}
