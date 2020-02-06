package com.tyss.surveyapp.dto;

import java.util.List;

import lombok.Data;

@Data
public class AdminResponse {
	
	private int statusCode;
	
	private String message;
	
	private List<Survey> surveys;
	
	private Survey survey;
	
	private AuthenticationDto userDetails; 

	
	
}
