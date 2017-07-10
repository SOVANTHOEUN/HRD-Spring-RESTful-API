package org.hrd.spring.controller.web;

import org.hrd.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/all-user")
	public String select(Model model){
		model.addAttribute("USERS", userService.findAll());
		return "user-list";
	}
	
}
