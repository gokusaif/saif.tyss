package com.onebill.customermanagement.dto;

import java.util.List;

import lombok.Data;

@Data
public class Response {

	private int statusCode;
	
	private String message;
	
	private List<Partner> partners;
	
	private List<Customer> customers;
	
	private Partner partner;
	
	private Customer customer;
	
}
