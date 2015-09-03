package com.healthfullu.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthfullu.data.model.Email;
import com.healthfullu.services.EmailService;

/**
 * The web resource for Email
 */
@Component("emailResource")
@Path("/json/email")
public class EmailResource {

	@Autowired
	private EmailService emailService;

	/**
	 * 
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Email email) {
		
		// TODO: need to authenticate user (email_from), either using session, or some other technique
		
		email.setFrom("healthfull.u2@gmail.com");
		email.setTo(email.getTo());
		email.setSubject(email.getSubject());
		email.setBody(email.getBody());
		emailService.sendEmail(email);


		String msg = "Your message has been sent";

		return Response.status(200).entity(msg).type("text/plain").build();

	}
}
