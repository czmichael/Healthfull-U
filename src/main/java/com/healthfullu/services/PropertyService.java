package com.healthfullu.services;


/**
 * Property related services.
 */
public interface PropertyService {
	
	public static final String EMAIL_SERVER_IP = "ruhe.email.server.ip";
	 
	/**
     * Get Property as String
     */
	public String getProperty(String key, String... defaultValue);
	
	/**
	 * Get Property as Integer
     */
	public Integer getPropertyAsInt (String key, Integer... defaultValue);
	
	/**
     * Get Property as Boolean
     */
	public Boolean getPropertyAsBoolean (String key, Boolean... defaultValue);
	
}