package com.healthfullu.services;

import com.healthfullu.data.model.DataSource;
import com.healthfullu.data.model.User;


public interface DataSourceService {
	
	public DataSource getDataSourceByUser(User user);

}
