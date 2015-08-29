package com.healthfullu.dao.impl;


import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.healthfullu.dao.UserDao;
import com.healthfullu.data.model.User;


@RunWith(JUnit4.class)
public class UserDaoImplTest {
	
	public static final String LOG_IN = "zhi_chen";
	public static final String PASSWORD = "123456";
	private final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"WEB-INF/applicationContext.xml");
	private UserDao userDao;
	private User user;
	
	
	@Before
	public void prepareTestData() {
		user = new User();
		userDao = (UserDao) applicationContext.getBean("userDao");
	}

	@After
	public void cleanupTestData() {
		user = null;
		userDao = null;
	}

	@Test
	public void testGetUserByLogin() {
		user = userDao.getUserByLogin(LOG_IN);
		Assert.assertEquals(user.getPassword(), PASSWORD);
	}
}
