package com.spring.hibernate.domain.employee;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Hike {

	@Column(name = "HIKE_YEAR")
	private String year;

	@Column(name = "SALARY")
	private String salary;

}
