package com.tyss.surveyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.surveyapp.dto.AdminResponse;
import com.tyss.surveyapp.dto.AuthenticationDto;
import com.tyss.surveyapp.service.AuthenticationService;

@CrossOrigin
@RestController
public class AuthenticationController {

	@Autowired
	AuthenticationService authenticationService;
	
	@PostMapping(path = "/addUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse addUser(@RequestBody AuthenticationDto authenticationDto) {
		AdminResponse adminResponse = new AdminResponse();
		if (authenticationService.addUser(authenticationDto)) {
			adminResponse.setStatusCode(201);
			adminResponse.setMessage("Success");
		}
		return adminResponse;
	}
	
	@PostMapping(path = "/authenticateUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse authenticateUser(@RequestBody AuthenticationDto authenticationDto) {
		AdminResponse adminResponse = new AdminResponse();
		 AuthenticationDto userFound=authenticationService.getUser(authenticationDto);
		if(userFound != null) {
			adminResponse.setStatusCode(201);
		    adminResponse.setMessage("User Found");
		    adminResponse.setUserDetails(userFound);
		}else {
			adminResponse.setStatusCode(406);
		    adminResponse.setMessage("Incorrect password");
		    adminResponse.setUserDetails(null);
		}
		 return adminResponse;
	}
	
	
	@PostMapping(path = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse updateUser(@RequestBody AuthenticationDto authenticationDto) {
		AdminResponse adminResponse = new AdminResponse();
		if (authenticationService.updateUser(authenticationDto)) {
			adminResponse.setStatusCode(201);
			adminResponse.setMessage("Password updated successfully !!");
		}else {
			adminResponse.setStatusCode(406);
			adminResponse.setMessage("User Updation Failed");
		}
		return adminResponse;
	}
	
	
	@PostMapping(path = "/deleteUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AdminResponse deleteUser(@RequestBody AuthenticationDto authenticationDto) {
		AdminResponse adminResponse = new AdminResponse();
		if (authenticationService.deleteUser(authenticationDto)) {
			adminResponse.setStatusCode(201);
			adminResponse.setMessage("User deleted successfully !!");
		}else {
			adminResponse.setStatusCode(406);
			adminResponse.setMessage("User deletion Failed");
		}
		return adminResponse;
	}
	
	
	
}
