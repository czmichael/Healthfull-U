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

import com.healthfullu.data.model.TokenDTO;



/**
 * The web resource for login
 */
@Component("logoutResource")
@Path("/api/logout")
public class LogoutResource {
	
	/**
	 * 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUserInJson() {
		// TODO: probably need to do more things here, like remove corresponding token?
System.out.println("=== logging out ====");		
		return Response.status(Response.Status.OK).entity(new TokenDTO("")).build();
	}
}
