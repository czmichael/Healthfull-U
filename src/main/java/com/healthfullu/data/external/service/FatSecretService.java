package com.healthfullu.data.external.service;

import java.util.Date;
import java.util.List;

import com.healthfullu.data.model.FoodEntry;
import com.healthfullu.data.model.User;


public interface FatSecretService {
	
	public List<FoodEntry> getUserFoodEntriesByDate(User user, Integer dateInt);

}
