package com.webcaisse.mvc.controller.ajax;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webcaisse.service.CustomUser;
import com.webcaisse.ws.interfaces.LivreurManagerService;



@Controller
@RequestMapping("/commandes/ajax")
public class CommandeProjetController {
	
	
	@Autowired
	LivreurManagerService livreurManagerService ;

	@RequestMapping("/loadLivreurs")
	@ResponseBody
	public JsonLivreurResponse loadLivreurs() {
		
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
     
		return new JsonLivreurResponse(livreurManagerService.rechercherLivreur(customUser.getSocieteId()) );
	}
	
	
	@RequestMapping(value = "/affecter/{idCommande}/{idLivreur}", method = RequestMethod.GET)
	@ResponseBody
	public void affecterLivreur(@PathVariable("idCommande") Long idCommande,@PathVariable("idLivreur") Long idLivreur ) {
		
		livreurManagerService.affecterLivreurToCommande(idLivreur, idCommande);
		
	}
}
