package com.webcaisse.mvc.controller.ajax;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webcaisse.mvc.bean.CommandeDump;
import com.webcaisse.mvc.bean.LignePanier;
import com.webcaisse.service.CustomUser;
import com.webcaisse.ws.interfaces.CaisseManagerService;
import com.webcaisse.ws.interfaces.CommandeManagerService;
import com.webcaisse.ws.interfaces.LivreurManagerService;
import com.webcaisse.ws.model.ClientIn;
import com.webcaisse.ws.model.CommandeIn;
import com.webcaisse.ws.model.LigneCommandeIn;



@Controller
@RequestMapping("/ajax/commandes")
public class CommandeProjetController {
	
	@Autowired
	private CaisseManagerService caisseManagerService;

	@Autowired
	LivreurManagerService livreurManagerService ;

	@Autowired
	private CommandeDump commandeDump;
	
	@Autowired
	CommandeManagerService commandeManagerService ;
	

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
	
	@RequestMapping (value = "/sauvegarderCommande/{modeVente}", method = RequestMethod.GET)
	@ResponseBody
	public Long sauvegarderCommande(@PathVariable("modeVente") String modeVente) {
		
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		CommandeIn commande = new CommandeIn() ;	

		populateModePaiement(modeVente, customUser, commande);
		
		populateClient(commande);
	
		populateCommandeLines(commande);
		
	
		
		return caisseManagerService.sauvegarderCommande(commande);
	}


	private void populateCommandeLines(CommandeIn commande) {
		List<LigneCommandeIn> commandeIns =new ArrayList<LigneCommandeIn>();
		commande.setLignesCommandesIn(commandeIns);
		
		// faire un boucle sur panier.getLignePanier pour construire les LigneCOmmandeIn
		if (!CollectionUtils.isEmpty(commandeDump.getPanier().getLignesPanier())){
			for (LignePanier lignePanier : commandeDump.getPanier().getLignesPanier()) {
				LigneCommandeIn in = new LigneCommandeIn();
				in.setIdProduit(lignePanier.getIdProduit());
				in.setPrix(lignePanier.getPrix());
				in.setQuantite(lignePanier.getQuantite());
				in.setTotal(lignePanier.getPrix()*lignePanier.getQuantite());
				
				commandeIns.add(in);
			}
		}
	}


	private void populateClient(CommandeIn commande) {
		ClientIn  clientIn= new ClientIn() ;
		clientIn.setNom(commandeDump.getClient().getNom());
		clientIn.setPrenom(commandeDump.getClient().getPrenom());
		clientIn.setEtage(commandeDump.getClient().getEtage());
		clientIn.setImmeuble(commandeDump.getClient().getImmeuble());
		clientIn.setInterphone(commandeDump.getClient().getInterphone());
		clientIn.setNomRue(commandeDump.getClient().getNomRue());
		clientIn.setNumeroRue(commandeDump.getClient().getNumeroRue());
		
		
		commande.setClientIn(clientIn);
	}


	private void populateModePaiement(String modeVente, CustomUser customUser,	CommandeIn commande ) {
		commande.setRegCB(commandeDump.getModePaiement().getCb());
		commande.setRegCarteFidelite(commandeDump.getModePaiement().getFidelite());
		commande.setRegCheque(commandeDump.getModePaiement().getCheque());
		commande.setRegEspece(commandeDump.getModePaiement().getEspece());
		commande.setRegTicketRestau(commandeDump.getModePaiement().getTicketRestau());
		commande.setIdSession(customUser.getSessionId());
		commande.setNotes(commandeDump.getPanier().getMessage());
		commande.setMode(modeVente);
		//commande.setEtat(etatCommande);
		
	}

	@RequestMapping(value = "/affecterEtat/{etatCommande}/{idCommande}", method = RequestMethod.GET)
	@ResponseBody
	public void affecterEtatToCommmande(@PathVariable("etatCommande") String etatCommande ,@PathVariable("idCommande") Long idCommande ) {
		commandeManagerService.affecterEtatToCommande(etatCommande, idCommande);
	}
		
		
}
	

