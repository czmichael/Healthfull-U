package com.healthfullu.resources;

import java.util.ArrayList;
import java.util.List;

//import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.healthfullu.data.model.LoginDTO;
import com.healthfullu.data.model.TokenDTO;
import com.healthfullu.services.LoginService;


/**
 * The web resource for login
 */
@Component("loginResource")
@Path("/api")
public class LoginResource {
	
	@Autowired
	@Qualifier(value = "loginService")
	private LoginService loginService;

	/**
	 * 
	 */
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public TokenDTO getUserInJson(LoginDTO login) {
		String username = login.getUsername();
		String password = login.getPassword();
		
		
		boolean isValidUser = loginService.validateCredential(username, password);
		if (isValidUser) {
			String token = loginService.generateToken("Joe");
			return new TokenDTO(token);
		} else {
			// TODO: how to return failing status to front end ajax call?
			throw new RuntimeException("");
		}
		
		
	}
}
