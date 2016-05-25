package com.spring.hibernate.service;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.hibernate.domain.Address;
import com.spring.hibernate.domain.Employee;
import com.spring.hibernate.domain.Hike;
import com.spring.hibernate.domain.Vehicle;

@Service
public class EmployeeService {
    
    @Autowired
    SessionFactory sessionFactory;
    
    public Integer createEmployee(Employee emp){
        emp.setEmployeeId(1586);
        emp.setFname("Vinu");
        emp.setLname("Prabhu");
        emp.setAge(23);
        emp.setJoinDate(new Date());
        emp.setDescription("Loooooonnngggg Descriptionnnn");
        
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType("Car");
        emp.setVehicle(vehicle);
        
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
        
        Hike hike = new Hike();
        hike.setYear("2015");
        hike.setSalary("2.3");
        
        Hike hike2 = new Hike();
        hike2.setYear("2016");
        hike2.setSalary("3.0");
        
        emp.getListofHikes().add(hike);
        emp.getListofHikes().add(hike2);
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(emp);
        session.save(vehicle);
        session.getTransaction().commit();
        session.close();
        
        return emp.getEmployeeId();
    }
    
    public Employee getEmployeeBasedOnId(Integer empId){
    	 Session session = sessionFactory.openSession();
         session.beginTransaction();
         return session.get(Employee.class, empId);
    }
}
