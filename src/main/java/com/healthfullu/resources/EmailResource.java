package com.healthfullu.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.healthfullu.data.external.service.FatSecretService;
import com.healthfullu.data.model.DataSource;
import com.healthfullu.data.model.Email;
import com.healthfullu.data.model.FoodEntry;
import com.healthfullu.data.model.User;
import com.healthfullu.services.DataSourceService;
import com.healthfullu.util.TimeUtil;


/**
 * The web resource for Email
 */
@Component("emailResource")
@Path("/json/email")
public class EmailResource {
	
//	@Autowired
//	@Qualifier(value = "emailService")
//	private EmailService emailService;
//	
	
	
	
	
	/**
	 * 
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Email email) {
		
		/*
		 * Later on, need to authenticate user (email_from), either using session, or 
		 * some other technique
		 */

System.out.println("1. to: " + email.getTo());
System.out.println("2. subject: " + email.getSubject());
System.out.println("3. body: " + email.getBody());
		
		
		String msg = "Your message has been sent";
//		return Response.ok( msg, "text/plain").build();

		 return Response.status(200).entity(msg).type("text/plain").build();

	}
}
