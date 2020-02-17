package com.onebill.customermanagement.dto;

import java.util.List;

import lombok.Data;

@Data
public class CustomerDto {
	
	private int id;
	
	private String name;	

	private String date;
	
	private Integer partnerId;
	
	private String status;
	
	private List<String> addresses;
	
	private List<String> emails;
	
	private List<String> phoneNumbers;

	private Partner partner;

}
