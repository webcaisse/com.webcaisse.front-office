package com.webcaisse.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/commandes")
public class CommandePageController {

	
	@RequestMapping("/enCours")
	public String afficherCommandeEnCours (){
		return "/commandesEnCours";
	}
}
