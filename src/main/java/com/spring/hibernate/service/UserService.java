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
}
