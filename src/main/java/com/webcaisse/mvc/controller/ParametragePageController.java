package com.webcaisse.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/parametrage")
public class ParametragePageController {

	@RequestMapping("/afficher")
	public String afficher(){
		
		return "parametrage";
	}
}
