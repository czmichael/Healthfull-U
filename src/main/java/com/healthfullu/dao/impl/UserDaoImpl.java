package com.healthfullu.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.healthfullu.dao.UserDao;
import com.healthfullu.data.model.User;


/**
 * user data access implementation
 */
@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	EntityManager em;

	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	public User getUserByUserId(int id) {
		return em.find(User.class, id);
	}
	
	public void createUser(User user) {
		em.persist(user);
	}

	public void removeUserByUserId(int id) {
		em.remove(getUserByUserId(id));
	}

	public boolean isUserExisted(String email) {
		Query query = em
				.createQuery("select count(usr) from User usr where usr.fbEmail=:fbEmail");
		query.setParameter("fbEmail", email);
		Long resultNumber = (Long) query.getSingleResult();

		return resultNumber.intValue() == 1;
	}
}
