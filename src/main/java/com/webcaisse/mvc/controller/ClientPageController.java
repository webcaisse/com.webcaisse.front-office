package com.webcaisse.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webcaisse.ws.interfaces.ClientManagerService;
import com.webcaisse.ws.interfaces.CommandeManagerService;
import com.webcaisse.ws.model.ClientOut;
import com.webcaisse.ws.model.CommandeOut;


@Controller
@RequestMapping("/clients")
public class ClientPageController {

	@Autowired (required=true)
	ClientManagerService clientManagerService ;
	
	
	@RequestMapping("/afficher/{idSociete}")
	public String afficherClients (ModelMap model ,@PathVariable Long idSociete){
		
	List<ClientOut> clients = clientManagerService.rechercherClient(idSociete) ;
		
		model.put("clients", clients) ;
		
		return "clients";
	}
}
