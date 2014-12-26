package com.webcaisse.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webcaisse.ws.interfaces.SessionManagerService;


@Controller
public class LogoutPageControlleur {

	@Autowired
	
	SessionManagerService sessionManagerService ;
	
	@RequestMapping("/logout/{idSession}")
	public String logOut(@PathVariable Long idSession){

		sessionManagerService.fermerSession(idSession);
		//request.getSession().invalidate();
		
		return "redirect:/j_spring_security_logout" ;
	}
}
