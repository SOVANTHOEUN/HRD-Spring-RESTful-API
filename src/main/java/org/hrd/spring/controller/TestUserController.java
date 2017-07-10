package org.hrd.spring.controller;

import java.util.List;

import org.hrd.spring.entities.User;
import org.hrd.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestUserController {

	UserService userService;
	
	@Autowired
	public TestUserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/find-all-user")
	public List<User> select(){
		return userService.findAll();
	}
	
}
