package com.healthfullu.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.MutableDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.healthfullu.data.external.service.FatSecretService;
import com.healthfullu.data.model.FoodEntry;
import com.healthfullu.data.model.User;
import com.healthfullu.services.UserService;
import com.healthfullu.util.TimeUtil;


/**
 * The web resource for FoodEntry
 */
@Component("foodEntryResource")
@Path("/json/food_entry")
public class FoodEntryResource {
	
	@Autowired
	@Qualifier(value = "fatSecretService")
	private FatSecretService fatSecretService;
	

	/**
	 * 
	 */
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FoodEntry> getUserInJson(@Context final UriInfo uriInfo) {
		
		final MultivaluedMap<String, String> queryParams = uriInfo
				.getQueryParameters();
		final String dateString = queryParams.getFirst("date");
		final String userString = queryParams.getFirst("user");
		

//		Integer dateInt = 16288;
		Integer dateInt = TimeUtil.getDaysSinceUnixEpoch(dateString);

		return fatSecretService.getUserFoodEntriesByDate(null, dateInt);
	}
	
	
	
	
	
	
}
