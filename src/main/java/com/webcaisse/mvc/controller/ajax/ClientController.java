package com.webcaisse.mvc.controller.ajax;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webcaisse.mvc.bean.Client;
import com.webcaisse.ws.interfaces.ClientManagerService;
import com.webcaisse.ws.model.ClientIn;
import com.webcaisse.ws.model.ClientOut;



@Controller
@RequestMapping("/ajax/client")
public class ClientController {
	
	@Autowired
	Client client;
	
	@Autowired
	ClientManagerService clientManagerService ;
	
	@RequestMapping(value = "/ajouterClient", method = RequestMethod.POST)
	public String ajouterClient(@ModelAttribute("clientIn") ClientIn clientIn) {
		
		client.setNom(clientIn.getNom());
		client.setPrenom(clientIn.getPrenom());
		client.setTelephone(clientIn.getTelephone());
		client.setCodePostale(clientIn.getCodePostale());
		client.setInterphone(clientIn.getInterphone());
		client.setNomRue(clientIn.getNomRue());
		client.setNumeroRue(clientIn.getNumeroRue());
		client.setImmeuble(clientIn.getImmeuble());
		client.setEtage(clientIn.getEtage());
		
		
		return "redirect:/loginSuccess" ;
}

	@RequestMapping(value = "/autoCompleteClient", method = RequestMethod.GET)
	@ResponseBody
	public List <ClientOut> autoCompleteClient(@RequestParam("term") String param){
		
		return clientManagerService.autoCompleteClient(param) ;
	}
	
	
}
