package com.healthfullu.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
@Path("/api/login")
public class LoginResource {
	
	@Autowired
	@Qualifier(value = "loginService")
	private LoginService loginService;

	/**
	 * 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUserInJson(LoginDTO login) {
		String username = login.getUsername();
		String password = login.getPassword();
		
		boolean isValidUser = loginService.validateCredential(username, password);
		if (isValidUser) {
			String token = loginService.generateToken(username);
			return Response.status(Response.Status.OK).entity(new TokenDTO(token)).build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity("").build();
		}
	}
}
