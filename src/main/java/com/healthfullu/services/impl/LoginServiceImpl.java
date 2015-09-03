package com.healthfullu.services.impl;

import com.healthfullu.data.model.User;
import com.healthfullu.services.LoginService;
import com.healthfullu.services.UserService;

import java.security.Key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;


@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	@Qualifier(value = "userService")
	private UserService userService;
	
	@Override
	public String generateToken(String subject) {
		// We need a signing key, so we'll create one just for this example. Usually
		// the key would be read from your application configuration instead.
		Key key = MacProvider.generateKey();

		
		String s = Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS512, key).compact();
		System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(s).getBody().getSubject());
		return s;
	}
	
	@Override
	public boolean validateCredential(String username, String password) {
		User user = userService.getUserByLogin(username);
		boolean isValid = (user != null && password.equals(user.getPassword())); 
		return isValid;
	}
	
	/*
	 * Test main
	 */
	public static void main(String[] args) {
		LoginServiceImpl loginService = new LoginServiceImpl();
		System.out.println("Token: " + loginService.generateToken("Joe"));
	}
	
}
