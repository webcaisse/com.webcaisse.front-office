package com.webcaisse.mvc.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webcaisse.ws.interfaces.CaisseManagerService;
import com.webcaisse.ws.interfaces.CommandeManagerService;
import com.webcaisse.ws.model.CommandeOut;

@Controller
@RequestMapping("/commandes")
public class CommandePageController {

	@Autowired (required=true)
	CommandeManagerService commandeManagerService ;
	
	private static final Long ID_SOCIETE = 1L;
	
	@RequestMapping("/enCours/{idSession}")
	public String afficherCommandeEnCours(ModelMap model,@PathVariable  Long idSession){
		
		List<CommandeOut> commandes = commandeManagerService.rechercherCommande(idSession) ;
		
		model.put("commandes", commandes) ;
		
		return "/commandesEnCours";
	}
	
	@RequestMapping("/rechercherCommande")
	public String rechercherCommandes(@RequestParam(value="dateCommande") Date  dateCommande,ModelMap model){
		
		//List<CommandeOut> commandes = commandeManagerService.rechercherCommandeParDate(ID_SOCIETE, dateCommande) ;
		model.addAttribute("dateCommande", dateCommande) ;
		
		model.put("commandes", commandeManagerService.rechercherCommandeParDate(ID_SOCIETE,dateCommande));
		
		return "/commandesEnCours";
	}
	
	
}
