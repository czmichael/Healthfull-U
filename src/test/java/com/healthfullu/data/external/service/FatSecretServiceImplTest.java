package com.healthfullu.data.external.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.healthfullu.data.model.DataSource;


@RunWith(JUnit4.class)
public class FatSecretServiceImplTest {

	private final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"/WEB-INF/applicationContext.xml");
	
	private FatSecretService fatSecretService;
	
	@Before
	public void prepareTestData() {
		fatSecretService = (FatSecretService) applicationContext.getBean("fatSecretService");
	}

	@After
	public void cleanupTestData() {
		fatSecretService = null;
	}

	@Test
	public void testgetUserFoodEntriesByDate() {
		DataSource dataSource = new DataSource();
		dataSource.setOauthToken("1ad4531fd75e426188ae2c679d680bd7");
		dataSource.setOauthTokenSecret("1a9bfded5cad4bc68f6e019d79bd646d");
		fatSecretService.getUserFoodEntriesByDate(dataSource, 16292);
	}
}
