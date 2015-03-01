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

import com.healthfullu.data.external.service.FatSecretService;
import com.healthfullu.data.model.FoodEntry;
import com.healthfullu.data.model.User;
import com.healthfullu.services.UserService;


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
	public List<FoodEntry> getUserInJson() {
		return fatSecretService.getUserFoodEntriesByDate(null, null);
	}
}
