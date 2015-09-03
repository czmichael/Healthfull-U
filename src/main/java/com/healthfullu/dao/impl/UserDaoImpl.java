package com.healthfullu.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

	@Override
	public User getUserByUserId(int id) {
		return em.find(User.class, id);
	}
	
	@Override
	public User getUserByLogin(String login) {
		Query query = em
				.createQuery("select user from User user where user.login=:login");
		query.setParameter("login", login);
		
		try {
			User user = (User) query.getSingleResult();	
			return user;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public void createUser(User user) {
		em.persist(user);
	}

	@Override
	public void removeUserByUserId(int id) {
		em.remove(getUserByUserId(id));
	}

	@Override
	public boolean isUserExisted(String email) {
		Query query = em
				.createQuery("select count(usr) from User usr where usr.fbEmail=:fbEmail");
		query.setParameter("fbEmail", email);
		Long resultNumber = (Long) query.getSingleResult();

		return resultNumber.intValue() == 1;
	}
}
