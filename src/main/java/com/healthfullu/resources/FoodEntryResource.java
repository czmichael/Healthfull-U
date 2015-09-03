package com.healthfullu.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.healthfullu.data.external.service.FatSecretService;
import com.healthfullu.data.model.DataSource;
import com.healthfullu.data.model.FoodEntry;
import com.healthfullu.data.model.User;
import com.healthfullu.services.DataSourceService;
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
	
	@Autowired
	@Qualifier(value = "dataSourceService")
	private DataSourceService dataSourceService;
	
	
	
	/**
	 * 
	 */
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FoodEntry> getUserInJson(@Context final UriInfo uriInfo) {
		
		//TODO: change the return type to Resource with response status
		
		final MultivaluedMap<String, String> queryParams = uriInfo
				.getQueryParameters();
		final String dateString = queryParams.getFirst("date");
		final String userString = queryParams.getFirst("user");
		
		
		Integer dateInt = TimeUtil.getDaysSinceUnixEpoch(dateString);
		
		User user = new User();
		user.setId(1);
		DataSource dataSource = dataSourceService.getDataSourceByUser(user);
		
		return fatSecretService.getUserFoodEntriesByDate(dataSource, dateInt);
	}
	
	
	
	
	
	
}
