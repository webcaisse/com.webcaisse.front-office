package com.webcaisse.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginPageController {

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login/failure")
	public String loginFailure() {
		String message = "Login Failure!";
		return "redirect:/login?message=" + message;
	}
}
