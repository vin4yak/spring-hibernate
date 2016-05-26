package com.spring.hibernate.service;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.hibernate.domain.Address;
import com.spring.hibernate.domain.Employee;
import com.spring.hibernate.domain.FourWheeler;
import com.spring.hibernate.domain.Hike;
import com.spring.hibernate.domain.PreviousCompany;
import com.spring.hibernate.domain.TwoWheeler;
import com.spring.hibernate.domain.Vehicle;

@Service
public class EmployeeService {

	@Autowired
	SessionFactory sessionFactory;

	public Integer createEmployee(Employee emp) {
		setFirstLevelFields(emp);
		setHomeAndOfficeAddresses(emp);
		setHikes(emp);
		setVehicle(emp);
		setPreviousCompanyOne(emp);
		setPreviousCompanyTwo(emp);

		TwoWheeler twoWheeler = new TwoWheeler();
		twoWheeler.setSteeringHandle("Bike Handle");
		twoWheeler.setVehicleType("Bike");

		FourWheeler fourWheeler = new FourWheeler();
		fourWheeler.setSteeringWheel("Figo Steering Wheel");
		fourWheeler.setVehicleType("Car");

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(emp);
		session.save(twoWheeler);
		session.save(fourWheeler);
		session.getTransaction().commit();
		session.close();

		return emp.getEmployeeId();
	}

	private PreviousCompany setPreviousCompanyTwo(Employee emp) {
		PreviousCompany previousCompany2 = new PreviousCompany();
		previousCompany2.setCompanyName("Cognizant Technologies");
		previousCompany2.setCompanyLocation("Bengaluru");
		emp.getPrevCompanyList().add(previousCompany2);
		return previousCompany2;
	}

	private PreviousCompany setPreviousCompanyOne(Employee emp) {
		PreviousCompany previousCompany = new PreviousCompany();
		previousCompany.setCompanyName("HSBC Global Technologies");
		previousCompany.setCompanyLocation("Pune");
		emp.getPrevCompanyList().add(previousCompany);
		return previousCompany;
	}

	private Vehicle setVehicle(Employee emp) {
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleType("Car");
		emp.setVehicle(vehicle);
		return vehicle;
	}

	private void setHikes(Employee emp) {
		Hike hike = new Hike();
		hike.setYear("2015");
		hike.setSalary("2.3");

		Hike hike2 = new Hike();
		hike2.setYear("2016");
		hike2.setSalary("3.0");

		emp.getListofHikes().add(hike);
		emp.getListofHikes().add(hike2);
	}

	private void setHomeAndOfficeAddresses(Employee emp) {
		Address homeAddress = new Address();
		homeAddress.setCity("Mumbai");
		homeAddress.setPinCode(400064);
		homeAddress.setState("Maharashtra");
		homeAddress.setStreet("Malad");
		emp.setHomeAddress(homeAddress);

		Address officeAddress = new Address();
		officeAddress.setCity("Pune");
		officeAddress.setPinCode(411014);
		officeAddress.setState("Maharashtra");
		officeAddress.setStreet("Kharadi");
		emp.setOfficeAddress(officeAddress);
	}

	private void setFirstLevelFields(Employee emp) {
		emp.setEmployeeId(1586);
		emp.setFname("Vinu");
		emp.setLname("Prabhu");
		emp.setAge(23);
		emp.setJoinDate(new Date());
		emp.setDescription("Loooooonnngggg Descriptionnnn");
	}

	public Employee getEmployeeBasedOnId(Integer empId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Employee emp = session.get(Employee.class, empId);
		session.close();
		return emp;
	}

	public Integer deleteEmployeeBasedOnId(Employee emp) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(emp);
		session.getTransaction().commit();
		session.close();
		return emp.getEmployeeId();
	}

	public Integer updateEmployeeBasedOnId(Employee emp) {
		emp.setCompanyName("Updated Company Name");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(emp);
		session.getTransaction().commit();
		session.close();
		return emp.getEmployeeId();
	}
}
