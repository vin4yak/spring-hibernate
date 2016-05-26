package com.spring.hibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.hibernate.domain.user.UserInfo;
import com.spring.hibernate.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final String TOTAL_USERS_FETCHED = "Users Fetched: ";

	private static final String USERS_CREATED = "Users Created Successfully!";

	@Autowired
	private UserService userService;

	@RequestMapping("/getAll")
	public ResponseEntity<String> fetchUsers() {
		List<UserInfo> userList = userService.getAllUsers();
		return new ResponseEntity<String>(TOTAL_USERS_FETCHED + userList.size(), HttpStatus.OK);
	}

	@RequestMapping("/create")
	public ResponseEntity<String> createUsers() {
		userService.createUsers();
		return new ResponseEntity<String>(USERS_CREATED, HttpStatus.OK);
	}

}
