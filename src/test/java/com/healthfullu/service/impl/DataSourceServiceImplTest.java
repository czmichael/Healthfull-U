package com.healthfullu.service.impl;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.healthfullu.data.model.DataSource;
import com.healthfullu.data.model.User;
import com.healthfullu.services.DataSourceService;


@RunWith(JUnit4.class)
public class DataSourceServiceImplTest {

	private final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"/WEB-INF/applicationContext.xml");
	
	private DataSourceService dataSourceService;
	
	
	@Before
	public void prepareTestData() {
		dataSourceService = (DataSourceService) applicationContext.getBean("dataSourceService");
	}

	@After
	public void cleanupTestData() {
		dataSourceService = null;
	}

	@Test
	public void testGetDataSourceByUser() {
		User user = new User();
		user.setId(1);
		DataSource dataSource = dataSourceService.getDataSourceByUser(user);
		Assert.assertEquals(dataSource.getName(), "fat_secret");
	}
}
