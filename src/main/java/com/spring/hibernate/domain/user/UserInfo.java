package com.spring.hibernate.domain.user;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Users")
@NamedQuery(name="UserInfo.byId", query="from UserInfo where userId = ?")
@NamedNativeQuery(name="UserInfo.byName", query="select * from users where FULL_NAME = ?", resultClass=UserInfo.class)
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "FULL_NAME")
	private String name;

}
