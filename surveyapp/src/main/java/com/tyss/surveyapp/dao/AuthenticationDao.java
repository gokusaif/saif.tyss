package com.tyss.surveyapp.dao;

import com.tyss.surveyapp.dto.AuthenticationDto;

public interface AuthenticationDao {
	
	public boolean addUser(AuthenticationDto authenticationDto);
	
	public boolean deleteUser(AuthenticationDto authenticationDto);
	
	public AuthenticationDto getUser(AuthenticationDto authenticationDto);
	
	public boolean updateUser(AuthenticationDto authenticationDto);
	

}
