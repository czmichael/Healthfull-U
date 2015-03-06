package com.healthfullu.data.external.service;

import java.util.List;

import com.healthfullu.data.model.DataSource;
import com.healthfullu.data.model.FoodEntry;


public interface FatSecretService {
	
	public List<FoodEntry> getUserFoodEntriesByDate(DataSource dataSource, Integer dateInt);

}
