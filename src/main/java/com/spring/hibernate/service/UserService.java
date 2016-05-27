package com.spring.hibernate.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.hibernate.domain.user.UserInfo;

@Service
public class UserService {

	@Autowired
	SessionFactory sessionFactory;

	public void createUsers() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		for (int i = 1; i <= 10; i++) {
			UserInfo user = new UserInfo();
			user.setName("User " + i);
			session.save(user);
		}

		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> getAllUsers() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from UserInfo where userId > 3 and userId < 10");
		List<UserInfo> userList = query.list();
		session.getTransaction().commit();
		session.close();
		return userList;
	}

	@SuppressWarnings("unchecked")
	public List<String> getUser(int userId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Note that this method is vulnerable to SQL Injection.
		Query query = session.createQuery("select name from UserInfo where userId = " + userId);
		List<String> userList = query.list();
		session.getTransaction().commit();
		session.close();
		return userList;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getUserUsingQuerySubstitution(int userId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("select name from UserInfo where userId = ?");
		query.setInteger(0, userId);
		/*
		 * You can use this if you don't want to specify location.
		 * Query query = session.createQuery("select name from UserInfo where userId = :userId");
		 * query.setString("userId", userId);
		 * 
		 */
		List<String> userList = query.list();
		session.getTransaction().commit();
		session.close();
		return userList;
	}
}
