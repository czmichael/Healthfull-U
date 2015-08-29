package com.healthfullu.services;


/**
 * Log in related service
 */
public interface LoginService {
	
	/**
	 * Generate JSON Web Token (JWT)
	 * 
	 * @param subject - subject used to generate JWT
	 * @return the JWT token 
	 */
	public String generateToken(String subject);

	/**
	 * Validates if the username and password are valid
	 * 
	 * @param username username provided from the front end log in form
	 * @param password password provided from the front end log in form
	 * @return true if username exists and password is correct; false otherwise.
	 */
	public boolean validateCredential(String username, String password);
}
