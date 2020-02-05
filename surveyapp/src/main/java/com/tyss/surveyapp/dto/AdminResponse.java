package com.tyss.surveyapp.dto;

import java.util.List;

import lombok.Data;

@Data
public class AdminResponse {
	
	private int statusCode;
	
	private String message;
	
	private List<Questions> surveys;
	
	private Questions survey;

}