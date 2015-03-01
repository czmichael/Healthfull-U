package com.healthfullu.dao;

import com.healthfullu.data.model.DataSource;


public interface DataSourceDao {
	
	public DataSource getDataSourceByUserId(int userId);

}
