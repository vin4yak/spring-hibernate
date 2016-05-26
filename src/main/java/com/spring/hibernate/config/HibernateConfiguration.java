package com.spring.hibernate.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class HibernateConfiguration {

	@Bean
	public SessionFactory sessionFactory() {
		return new Configuration().configure().buildSessionFactory();
	}

}
