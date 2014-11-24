package com.webcaisse.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webcaisse.ws.interfaces.CaisseManagerService;
import com.webcaisse.ws.interfaces.CommandeManagerService;
import com.webcaisse.ws.model.CommandeOut;

@Controller
@RequestMapping("/commandes")
public class CommandePageController {

	@Autowired (required=true)
	CommandeManagerService commandeManagerService ;
	
	@RequestMapping("/enCours/{idSession}")
	public String afficherCommandeEnCours(ModelMap model,@PathVariable  Long idSession){
		
		List<CommandeOut> commandes = commandeManagerService.rechercherCommande(idSession) ;
		
		model.put("commandes", commandes) ;
		
		return "/commandesEnCours";
	}
}
