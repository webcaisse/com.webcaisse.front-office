package com.webcaisse.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/clients")
public class ClientPageController {

	
	@RequestMapping("/afficher")
	public String afficherClients (){
		return "clients";
	}
}
