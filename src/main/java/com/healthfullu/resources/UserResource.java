package com.healthfullu.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.healthfullu.data.model.User;
import com.healthfullu.services.UserService;


/**
 * The web resource for user
 */
@Component("userResource")
@Path("/json/user")
public class UserResource {
	
	@Autowired
	@Qualifier(value = "userService")
	private UserService userService;

	/**
	 * 
	 */
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUserInJson() {
		// TODO: change the return type to Resource with response status
		final List<User> result = new ArrayList<User>();
		User  user = userService.getUserById(1);
		result.add(user);
		
		
		return result;

	}
}
