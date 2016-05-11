package com.spring.hibernate.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.hibernate.domain.Employee;
import com.spring.hibernate.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    
    private static final String EMPLOYEE_CREATED_SUCCESSFULLY = "Employee Created Successfully With ID: ";
    
    private static final String GET_EMPLOYEE_MESSAGE = "Following Employee Was Fetched: ";
    
    @Autowired
    private EmployeeService employeeService;
    
    @RequestMapping("/create")
    public ResponseEntity<String> createUser() throws IOException{
        Employee emp = new Employee();
        Integer empId = employeeService.createEmployee(emp);
        return new ResponseEntity<String>(EMPLOYEE_CREATED_SUCCESSFULLY + empId, HttpStatus.OK);
    }
    
    @RequestMapping("/getEmployee")
    public ResponseEntity<String> getEmployeeBasedOnEmployeeId(@RequestParam(name="empId", required=true) Integer empId) throws IOException{
        Employee emp = employeeService.getEmployeeBasedOnId(empId);
        return new ResponseEntity<String>(GET_EMPLOYEE_MESSAGE + emp.getEmployeeId(), HttpStatus.OK);
    }

}
