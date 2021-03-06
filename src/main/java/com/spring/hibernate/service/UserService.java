package com.spring.hibernate.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
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
		 * You can use this if you don't want to specify location. Query query = session.createQuery(
		 * "select name from UserInfo where userId = :userId");
		 * query.setString("userId", userId);
		 * 
		 */
		List<String> userList = query.list();
		session.getTransaction().commit();
		session.close();
		return userList;
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> getUserUsingNamedQuery(int userId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.getNamedQuery("UserInfo.byId");
		query.setInteger(0, userId);
		List<UserInfo> userList = query.list();
		session.getTransaction().commit();
		session.close();
		return userList;
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> getUserByNameUsingCriteria(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(UserInfo.class);
		criteria.add(Restrictions.eq("name", name)).add(Restrictions.gt("userId", 5));

		// OR
		// criteria.add(Restrictions.or(Restrictions.eq("name", name),
		// Restrictions.eq("userId", 5)));

		List<UserInfo> userList = criteria.list();
		session.getTransaction().commit();
		session.close();
		return userList;
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> getUserUsingQueryByExample(int userId, String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		UserInfo user = new UserInfo();
		user.setUserId(userId);
		user.setName(name);

		Example example = Example.create(user);

		Criteria criteria = session.createCriteria(UserInfo.class).add(example);

		List<UserInfo> userList = criteria.list();
		session.getTransaction().commit();
		session.close();
		return userList;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserInfo> getUsesUsingQueryCache() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from UserInfo where name=User 5");
		query.setCacheable(true);
		
		List<UserInfo> userInfoList =  query.list();
		userInfoList.get(0).setName("Updated User 5");
		
		session.getTransaction().commit();
		session.close();
		
		session.beginTransaction();
		
		Query query2 = session.createQuery("select * from UserInfo");
		query2.setCacheable(true);
		
		List<UserInfo> userList = query.list();
		session.getTransaction().commit();
		session.close();
		return userList;
	}
}
