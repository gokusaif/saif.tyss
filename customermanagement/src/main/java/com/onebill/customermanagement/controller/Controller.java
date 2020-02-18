package com.onebill.customermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onebill.customermanagement.dto.Customer;
import com.onebill.customermanagement.dto.Partner;
import com.onebill.customermanagement.dto.Response;
import com.onebill.customermanagement.service.Service;

@RestController
@CrossOrigin
public class Controller {

	@Autowired
	Service service;

	@PostMapping(path = "/partner/add-partner", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addPartner(@RequestBody Partner partner) {
		Response response = new Response();
		if (service.addPartner(partner) != null) {
			response.setStatusCode(201);
			response.setMessage("success");
		} else {
			response.setStatusCode(405);
			response.setMessage("Partner id does not exist");
		}
		return response;
	}

	@PostMapping(path = "/customer/add-customer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addCustomer(@RequestBody Customer customer) {
		Response response = new Response();
		if (service.addCustomer(customer) != null) {
			response.setStatusCode(201);
			response.setMessage("success");
		} else {
			response.setStatusCode(405);
			response.setMessage("Partner id does not exist");
		}
		return response;
	}

	@GetMapping(path = "/partner/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getPartner(@PathVariable("id") int id) {
		Response response = new Response();
		Partner partner = service.getPartner(id);
		if (partner != null) {
			response.setStatusCode(201);
			response.setMessage("Partner found");
			response.setPartner(partner);
		} else {
			response.setStatusCode(405);
			response.setMessage("Partner not found");
		}
		return response;
	}

	@GetMapping(path = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getCustomer(@PathVariable("id") int id) {
		Response response = new Response();
		Customer customer = service.getCustomer(id);
		if (customer != null) {
			response.setStatusCode(201);
			response.setMessage("Customer found");
			response.setCustomer(customer);
		} else {
			response.setStatusCode(405);
			response.setMessage("Customer not found");
		}
		return response;
	}

	@GetMapping(path = "/partner/partners", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getPartners() {
		Response response = new Response();
		List<Partner> partners = service.getPartners();
		if (partners.size() != 0) {
			response.setStatusCode(201);
			response.setMessage("Partners found");
			response.setPartners(partners);
		} else {
			response.setStatusCode(405);
			response.setMessage("Partners not found");
		}
		return response;
	}

	@GetMapping(path = "/customer/customers", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getCustomers() {
		Response response = new Response();
		List<Customer> customers = service.getCustomers();
		if (customers.size() != 0) {
			response.setStatusCode(201);
			response.setMessage("Customers found");
			response.setCustomers(customers);
		} else {
			response.setStatusCode(405);
			response.setMessage("Customers not found");
		}
		return response;
	}

	@DeleteMapping(path = "/customer/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response deleteCustomer(@PathVariable("id") int id) {
		Response response = new Response();
		if( service.deleteCustomer(id)) {			
			response.setStatusCode(201);
			response.setMessage("Status changed");
		} else {
			response.setStatusCode(401);
			response.setMessage("Customer not found");
		}
		return response;
	}

	@DeleteMapping(path = "/partner/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response deletePartner(@PathVariable("id") int id) {
		Response response = new Response();
		if(service.deletePartner(id)) {			
			response.setStatusCode(201);
			response.setMessage("Status changed");
		} else {
			response.setStatusCode(401);
			response.setMessage("Partner not found");
		}
		return response;
	}

	@PutMapping(path = "/customer/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response updateCustomer(@RequestBody Customer customer) {
		Response response = new Response();
		if(service.updateCustomer(customer)) {			
			response.setStatusCode(201);
			response.setMessage("Customer details updated");
		} else {
			response.setStatusCode(401);
			response.setMessage("Customer not found");
		}
		return response;
	}
	
	@PutMapping(path = "/partner/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response updateCustomer(@RequestBody Partner partner) {
		Response response = new Response();
		if(service.updatePartner(partner)) {			
			response.setStatusCode(201);
			response.setMessage("Partner details updated");
		} else {
			response.setStatusCode(401);
			response.setMessage("Partner not found");
		}
		return response;
	}

}
