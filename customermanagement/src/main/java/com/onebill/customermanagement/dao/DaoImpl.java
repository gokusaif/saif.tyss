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
import com.onebill.customermanagement.exceptions.CustomException;

@Repository
public class DaoImpl implements Dao {

	@PersistenceUnit
	EntityManagerFactory factory;

	@Override
	public Partner addPartner(Partner partner) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			if (partner.getPartnerId() != 0) {
				Partner existingPartner = manager.find(Partner.class, partner.getPartnerId());
				if (existingPartner != null) {
					List<Partner> subPartners = existingPartner.getSubPartners();
					subPartners.add(partner);
					partner.setPartner(existingPartner);
				} else {
					throw new CustomException("Partner does not exist");
				}
			}
			transaction.begin();
			manager.persist(partner);
			transaction.commit();
			return partner;
		} catch (Exception e) {
			throw new CustomException("Partner does not exist");
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
				throw new CustomException("Partner does not exist");
			}
		} catch (Exception e) {
			throw new CustomException("Partner does not exist");
		}
	}

	@Override
	public Partner getPartner(int id) {
		EntityManager manager = factory.createEntityManager();
		Partner partner = manager.find(Partner.class, id);
		if (partner != null) {
			if (partner.getPartner() != null) {
				partner.setPartnerId(partner.getPartner().getId());
			}
			List<Customer> customers = partner.getCustomers();
			for (Customer customer : customers) {
				customer.setPartnerId(customer.getPartner().getId());
				customer.setPartnerName(customer.getPartner().getName());
			}
			List<Partner> subPartners = partner.getSubPartners();
			for (Partner subPartner : subPartners) {
				subPartner.setPartnerId(partner.getId());
				List<Customer> subCustomers = subPartner.getCustomers();
				for (Customer subCustomer : subCustomers) {
					subCustomer.setPartnerId(subCustomer.getPartner().getId());
					subCustomer.setPartnerName(subCustomer.getPartner().getName());
				}
			}
			return partner;
		}
		throw new CustomException("Partner does not exist");
	}

	@Override
	public Customer getCustomer(int id) {
		EntityManager manager = factory.createEntityManager();
		Customer customer = manager.find(Customer.class, id);
		if (customer != null) {
			customer.setPartnerId(customer.getPartner().getId());
			customer.setPartnerName(customer.getPartner().getName());
			return customer;
		} 
		throw new CustomException("Customer does not exist");
	}

	@Override
	public List<Partner> getPartners() {
		EntityManager manager = factory.createEntityManager();
		String jpql = "from Partner";
		TypedQuery<Partner> query = manager.createQuery(jpql, Partner.class);
		List<Partner> partners = query.getResultList();
		if (partners.size() != 0) {
			for (Partner partner : partners) {
				if (partner.getPartner() != null) {
					partner.setPartnerId(partner.getPartner().getId());
				}
				List<Customer> customers = partner.getCustomers();
				for (Customer customer : customers) {
					customer.setPartnerId(customer.getPartner().getId());
					customer.setPartnerName(customer.getPartner().getName());
				}
			}
			return partners;
		}
		throw new CustomException("Partner does not exist");
	}

	@Override
	public List<Customer> getCustomers() {
		EntityManager manager = factory.createEntityManager();
		String jpql = "from Customer";
		TypedQuery<Customer> query = manager.createQuery(jpql, Customer.class);
		List<Customer> customers = query.getResultList();
		if (customers.size() != 0) {
			for (Customer customer : customers) {
				customer.setPartnerId(customer.getPartner().getId());
				customer.setPartnerName(customer.getPartner().getName());
			}
			return customers;
		}
		throw new CustomException("Customer does not exist");
	}

	@Override
	public boolean deleteCustomer(int id) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		Customer customer = manager.find(Customer.class, id);
		if( customer != null) {
			transaction.begin();
			customer.setStatus("Inactive");
			transaction.commit();
			return true;			
		}
		throw new CustomException("Customer does not exist");
	}

	@Override
	public boolean deletePartner(int id) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		Partner partner = manager.find(Partner.class, id);
		if( partner != null) {
			transaction.begin();
			partner.setStatus("Inactive");
			transaction.commit();
			return true;			
		}
		throw new CustomException("Partner does not exist");
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		Customer existing = manager.find(Customer.class, customer.getId());
		if ( existing != null) {
			transaction.begin();
			existing.setName(customer.getName());
			existing.setStatus(customer.getStatus());
			existing.setAddresses(customer.getAddresses());
			existing.setEmails(customer.getEmails());
			existing.setPhoneNumbers(customer.getPhoneNumbers());
			transaction.commit();
			return true;			
		}
		throw new CustomException("Customer does not exist");
	}

	@Override
	public boolean updatePartner(Partner partner) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		Partner existing = manager.find(Partner.class, partner.getId());
		if( existing != null) {
			transaction.begin();
			existing.setName(partner.getName());
			existing.setStatus(partner.getStatus());
			existing.setAddresses(partner.getAddresses());
			existing.setEmails(partner.getEmails());
			existing.setPhoneNumbers(partner.getPhoneNumbers());
			transaction.commit();
			return true;			
		} 
		throw new CustomException("Partner does not exist");
	}

}
