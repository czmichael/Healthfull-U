package com.healthfullu.service.impl;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.healthfullu.services.LoginService;


@RunWith(JUnit4.class)
public class LoginServiceImplTest {

	private final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"/WEB-INF/applicationContext.xml");
	
	private LoginService loginService;
	
	@Before
	public void prepareTestData() {
		loginService = (LoginService) applicationContext.getBean("loginService");
	}

	@After
	public void cleanupTestData() {}
	
	@Test
	public void testValidateCredentialIsValid() {
		boolean isValid = loginService.validateCredential("zhi_chen", "123456");
		Assert.assertTrue(isValid);
	}
	
	@Test
	public void testValidateCredentialIsNotValid() {
		boolean isValid = loginService.validateCredential("zhi_chen", "");
		Assert.assertFalse(isValid);
	}
}
