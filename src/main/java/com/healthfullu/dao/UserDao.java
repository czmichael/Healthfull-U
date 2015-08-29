package com.healthfullu.dao;

import com.healthfullu.data.model.User;


/**
 * user data access interface
 */
public interface UserDao {
	
	public User getUserByUserId(int id);

	public User getUserByLogin(String login);
	
	public boolean isUserExisted(String _fbEmail);

	public void createUser(User user);

	public void removeUserByUserId(int id);
	
	
}
