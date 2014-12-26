package com.webcaisse.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webcaisse.service.CustomUser;
import com.webcaisse.ws.interfaces.SessionManagerService;


@Controller
public class LogoutPageControlleur {

	@Autowired
	
	SessionManagerService sessionManagerService ;
	
	@RequestMapping("/logout")
	public String logOut(){

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		sessionManagerService.fermerSession(customUser.getSessionId());
		//request.getSession().invalidate();
		
		return "redirect:/j_spring_security_logout" ;
	}
}
