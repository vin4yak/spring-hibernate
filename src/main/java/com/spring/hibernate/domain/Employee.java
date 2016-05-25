package com.spring.hibernate.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="Employees")
public class Employee {
	
	/*
	 * We can also have embedded objects as primary key using @EmbeddedId.
	 * It works in the same way as @Embedded
	*/
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int generatedValue;
    
    @Column(name="EMP_ID")
    private Integer employeeId;
    
    @Column(name="FIRST_NAME")
    private String fname;
    
    @Column(name="LAST_NAME")
    private String lname;
    
    @Column(name="AGE")
    private Integer age;
    
    @OneToOne
    @JoinColumn(name="VEHICLE_ID")
    private Vehicle vehicle;
    
    @Embedded
    private Address homeAddress;
    
    @Embedded
    @AttributeOverrides({
    	@AttributeOverride(name="street", column=@Column(name="OFFICE_STREET_NAME")),
    	@AttributeOverride(name="city", column=@Column(name="OFFICE_CITY_NAME")),
    	@AttributeOverride(name="state", column=@Column(name="OFFICE_STATE_NAME")),
    	@AttributeOverride(name="pinCode", column=@Column(name="OFFICE_PIN_CODE"))
    })
    private Address officeAddress;
    
    @Transient
    private String companyName = "Nisum Technologies.";
    
    @ElementCollection(fetch=FetchType.EAGER)
    @JoinTable(name="EMPLOYEE_HIKES", joinColumns=@JoinColumn(name="GEN_VALUE"))
    @GenericGenerator(name="sequence-gen", strategy="sequence")
    @CollectionId(columns = { @Column(name="HIKE_ID") }, generator = "sequence-gen", type = @Type(type="long"))
    private List<Hike> listofHikes = new ArrayList<Hike>();
    
    @Lob
    @Column(name="DESCRIPTION")
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="JOIN_DATE")
    private Date joinDate;

}
