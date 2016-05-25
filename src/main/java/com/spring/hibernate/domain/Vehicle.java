package com.spring.hibernate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Vehicle {
	
	@Id
	@GeneratedValue
	private int vehicleId;
	private String vehicleType;

}
