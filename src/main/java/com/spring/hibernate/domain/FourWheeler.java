package com.spring.hibernate.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue("Car")
@Entity
public class FourWheeler extends Vehicle {

	private String SteeringWheel;

}
