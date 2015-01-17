package com.webcaisse.mvc.controller.ajax;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webcaisse.mvc.bean.Client;
import com.webcaisse.ws.model.ClientIn;



@Controller
@RequestMapping("/ajax/client")
public class ClientController {
	
	@Autowired
	Client client;
	
	
	@RequestMapping(value = "/ajouterClient", method = RequestMethod.POST)
	public String ajouterClient(@ModelAttribute("clientIn") ClientIn clientIn, ModelMap model) {
		
		client.setNom(clientIn.getNom());
		client.setPrenom(clientIn.getPrenom());
		client.setCodePostale(clientIn.getCodePostale());
		client.setInterphone(clientIn.getInterphone());
		client.setNomRue(clientIn.getNomRue());
		client.setNumeroRue(clientIn.getNumeroRue());
		client.setImmeuble(clientIn.getImmeuble());
		client.setEtage(clientIn.getEtage());
		
		
		return "redirect:/loginSuccess" ;
}

}