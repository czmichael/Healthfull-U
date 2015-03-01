package com.healthfullu.dao.impl;


import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.healthfullu.dao.DataSourceDao;
import com.healthfullu.data.model.DataSource;


@RunWith(JUnit4.class)
public class DataSourceDaoImplTest {
	
	private final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"WEB-INF/applicationContext.xml");
	private DataSourceDao dataSourceDao;
	private DataSource dataSource;
	
	
	@Before
	public void prepareTestData() {
		dataSource = new DataSource();
		dataSourceDao = (DataSourceDao) applicationContext.getBean("dataSourceDao");
	}

	@After
	public void cleanupTestData() {
		dataSource = null;
		dataSourceDao = null;
	}

	@Test
	public void testGetDataSourceByUserId() {
		final int userId = 1;
		dataSource = dataSourceDao.getDataSourceByUserId(userId);
		Assert.assertEquals(dataSource.getName(), "fat_secret");
	}
}
