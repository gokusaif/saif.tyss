package com.tyss.surveyapp.service;

import com.tyss.surveyapp.dto.AuthenticationDto;

public interface AuthenticationService {

	
public boolean addUser(AuthenticationDto authenticationDto);
	
	public boolean deleteUser(AuthenticationDto authenticationDto);
	
	public AuthenticationDto getUser(AuthenticationDto authenticationDto);
	
	public boolean updateUser(AuthenticationDto authenticationDto);
	

}
