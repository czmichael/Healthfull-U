package com.healthfullu.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.healthfullu.data.model.Email;
import com.healthfullu.services.EmailService;


@RunWith(JUnit4.class)
public class EmailServiceImplTest {

	private final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"/WEB-INF/applicationContext.xml");
	
	private EmailService emailService;
	
	@Before
	public void prepareTestData() {
		emailService = (EmailService) applicationContext.getBean("emailService");
	}

	@After
	public void cleanupTestData() {}
	
	@Test
	public void testSendEmail() {
//		Email email = new Email();
//		email.setFrom("cz_michael@hotmail.com");
//		email.setTo("cz.michael@gmail.com");
//		email.setSubject("healthfull U test 10");
//		email.setBody("hello, world");
//		emailService.sendEmail(email);
	}
}
