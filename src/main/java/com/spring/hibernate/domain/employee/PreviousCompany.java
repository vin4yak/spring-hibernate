package com.spring.hibernate.domain.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class PreviousCompany {

	@Id
	@GeneratedValue
	@Column(name = "PREV_COMP_ID")
	private int prevCompId;

	@Column(name = "PREV_COMPANY_NAME")
	private String companyName;

	@Column(name = "PREV_COMPANY_LOCATION")
	private String companyLocation;

}
