package com.healthfullu.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.healthfullu.dao.UserDao;
import com.healthfullu.data.model.User;
import com.healthfullu.services.UserService;


@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Override
	public User getUserById(int id) {
		return userDao.getUserByUserId(id);
	}

	@Override
	public User getUserByLogin(String username) {
		return userDao.getUserByLogin(username);
	}
}
