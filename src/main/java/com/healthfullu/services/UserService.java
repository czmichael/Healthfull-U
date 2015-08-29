package com.healthfullu.services;

import com.healthfullu.data.model.User;


/**
 * 
 */
public interface UserService {

	/**
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(int id);
	
	/**
	 * 
	 * 
	 * @param username
	 * @return
	 */
	public User getUserByLogin(String username);
	
}
