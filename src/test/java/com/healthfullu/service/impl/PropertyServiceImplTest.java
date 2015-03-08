package com.healthfullu.service.impl;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.healthfullu.services.PropertyService;


@RunWith(JUnit4.class)
public class PropertyServiceImplTest {

	private final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"/WEB-INF/applicationContext.xml");
	
	private PropertyService propertyService;
	
	
	@Before
	public void prepareTestData() {
		propertyService = (PropertyService) applicationContext.getBean("propertyService");
	}

	@After
	public void cleanupTestData() {}
	
	
	
	@Test
	public void testGetProperty() {
		Assert.assertEquals("127.0.0.1", propertyService.getProperty(PropertyService.EMAIL_SERVER_IP));
	}
}
