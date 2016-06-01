package com.spring.hibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.hibernate.domain.user.UserInfo;
import com.spring.hibernate.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final String TOTAL_USERS_FETCHED = "Users fetched: ";

	private static final String USER_FETCHED = "Following user was fetched: ";

	private static final String USERS_CREATED = "Users created successfully!";

	@Autowired
	private UserService userService;

	@RequestMapping("/create")
	public ResponseEntity<String> createUsers() {
		userService.createUsers();
		return new ResponseEntity<String>(USERS_CREATED, HttpStatus.OK);
	}

	@RequestMapping("/getAll")
	public ResponseEntity<String> fetchUsers() {
		List<UserInfo> userList = userService.getAllUsers();
		return new ResponseEntity<String>(TOTAL_USERS_FETCHED + userList.size(), HttpStatus.OK);
	}

	@RequestMapping("/getUser")
	public ResponseEntity<String> fetchUserBasedOnId(@RequestParam(name = "userId", required = true) int userId) {
		List<String> userList = userService.getUser(userId);
		return new ResponseEntity<String>(USER_FETCHED + userList.get(0), HttpStatus.OK);
	}
	
	@RequestMapping("/getUserById")
	public ResponseEntity<String> fetchUserBasedOnIdUsingDifferentQueries(@RequestParam(name = "userId", required = true) int userId) {
		List<UserInfo> userList = userService.getUserUsingNamedQuery(userId);
		return new ResponseEntity<String>(USER_FETCHED + userList.get(0).getName(), HttpStatus.OK);
	}

}
