package com.onebill.customermanagement.dao;

import java.util.List;

import com.onebill.customermanagement.dto.Customer;
import com.onebill.customermanagement.dto.Partner;

public interface Dao {

	public Partner addPartner(Partner partner);

	public Customer addCustomer(Customer customer);

	public Partner getPartner(int id);

	public Customer getCustomer(int id);

	public List<Partner> getPartners();

	public List<Customer> getCustomers();

	public boolean deleteCustomer(int id);

	public boolean deletePartner(int id);
	
	public boolean updateCustomer(Customer customer);
	
	public boolean updatePartner(Partner partner);
}
