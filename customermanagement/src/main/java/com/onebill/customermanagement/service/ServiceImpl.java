package com.onebill.customermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.onebill.customermanagement.dao.Dao;
import com.onebill.customermanagement.dto.Customer;
import com.onebill.customermanagement.dto.Partner;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service{
	
	@Autowired
	Dao dao;

	@Override
	public Partner addPartner(Partner partner) {
		
		return dao.addPartner(partner);
	}

	@Override
	public Customer addCustomer(Customer customer) {
		
		return dao.addCustomer(customer);
	}

	@Override
	public Partner getPartner(int id) {
		
		return dao.getPartner(id);
	}

	@Override
	public Customer getCustomer(int id) {
		
		return dao.getCustomer(id);
	}

	@Override
	public List<Partner> getPartners() {
		
		return dao.getPartners();
	}

	@Override
	public List<Customer> getCustomers() {
		
		return dao.getCustomers();
	}

	@Override
	public boolean deleteCustomer(int id) {
		
		return dao.deleteCustomer(id);
	}

	@Override
	public boolean deletePartner(int id) {
		
		return dao.deletePartner(id);
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		
		return dao.updateCustomer(customer);
	}

	@Override
	public boolean updatePartner(Partner partner) {
		
		return dao.updatePartner(partner);
	}

}
