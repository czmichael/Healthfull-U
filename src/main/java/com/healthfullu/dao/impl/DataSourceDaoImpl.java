package com.healthfullu.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.healthfullu.dao.DataSourceDao;
import com.healthfullu.data.model.DataSource;


@Repository("dataSourceDao")
@Transactional
public class DataSourceDaoImpl implements DataSourceDao {

	@PersistenceContext
	EntityManager em;
	
	public DataSource getDataSourceByUserId(int userId) {
		Query query = em
				.createQuery("select ds from DataSource ds where ds.user.id=:userId");
		query.setParameter("userId", userId);
		DataSource dataSource = (DataSource) query.getSingleResult();

		return dataSource;
	}
}
