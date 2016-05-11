package com.spring.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {
	
	@Column(name="STREET_NAME")
	private String street;
	
	@Column(name="CITY_NAME")
	private String city;
	
	@Column(name="STATE_NAME")
	private String state;
	
	@Column(name="PIN_CODE")
	private Integer pinCode;

}
