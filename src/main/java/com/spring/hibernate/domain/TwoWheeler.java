package com.spring.hibernate.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue("Bike")
@Entity
public class TwoWheeler extends Vehicle {

	private String SteeringHandle;

}
