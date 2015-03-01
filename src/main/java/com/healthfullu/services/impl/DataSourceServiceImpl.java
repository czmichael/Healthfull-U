package com.healthfullu.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.healthfullu.dao.DataSourceDao;
import com.healthfullu.data.model.DataSource;
import com.healthfullu.data.model.User;
import com.healthfullu.services.DataSourceService;


@Service("dataSourceService")
public class DataSourceServiceImpl implements DataSourceService {
	
	@Autowired
	@Qualifier("dataSourceDao")
	private DataSourceDao dataSourceDao;

	@Override
	public DataSource getDataSourceByUser(User user) {
		return dataSourceDao.getDataSourceByUserId(user.getId());
	}
}
