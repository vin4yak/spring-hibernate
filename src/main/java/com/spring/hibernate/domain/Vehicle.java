package com.spring.hibernate.domain;

import javax.persistence.Column;
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
	@Column(name="VEHICLE_ID")
	private int vehicleId;
	
	@Column(name="VEHICLE_TYPE")	
	private String vehicleType;

}
