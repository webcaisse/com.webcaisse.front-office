package com.webcaisse.mvc.controller.ajax;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webcaisse.beans.commande.CommandeDump;
import com.webcaisse.beans.commande.ModePaiement;
import com.webcaisse.beans.commande.panier.LignePanier;
import com.webcaisse.enums.commande.EnumModePaiement;
import com.webcaisse.mvc.controller.ajax.response.JsonLivreurResponse;
import com.webcaisse.service.CustomUser;
import com.webcaisse.ws.interfaces.CaisseManagerService;
import com.webcaisse.ws.interfaces.CommandeManagerService;
import com.webcaisse.ws.interfaces.LivreurManagerService;
import com.webcaisse.ws.interfaces.ParametreManagerService;
import com.webcaisse.ws.model.ClientIn;
import com.webcaisse.ws.model.CommandeIn;
import com.webcaisse.ws.model.CommandeOut;
import com.webcaisse.ws.model.LigneCommandeIn;
import com.webcaisse.ws.model.LigneCommandeOut;
import com.webcaisse.ws.model.ParametreOut;



@Controller
@RequestMapping("/commandes/ajax")
public class AjaxCommandeProjetController {
	
	@Autowired
	private CaisseManagerService caisseManagerService;

	@Autowired
	LivreurManagerService livreurManagerService ;

	@Autowired
	private CommandeDump commandeDump;
	
	@Autowired
	CommandeManagerService commandeManagerService ;
	
	
	

	@RequestMapping(value="/loadLivreurs")
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
	public void affecterEtatToCommmande(@PathVariable("etatCommande") String etatCommande ,@PathVariable("idCommande") Long idCommande  ) {
		commandeManagerService.affecterEtatToCommande(etatCommande, idCommande);
	}
	
	@RequestMapping(value = "/affecterEtatAvecMode/{etatCommande}/{idCommande}/{modeCommande}", method = RequestMethod.GET)
	@ResponseBody
	public void affecterEtatToCommmande(@PathVariable("etatCommande") String etatCommande ,@PathVariable("idCommande") Long idCommande ,@PathVariable("modeCommande") String modeCommande ) {
		commandeManagerService.affecterEtatToCommandeAvecMode(etatCommande, idCommande,modeCommande);
	}
	
	
	@RequestMapping(value = "/afficherPopupPaiement/{modeVente}", method = RequestMethod.GET)
	@ResponseBody
	public String afficherPopupPaiement(@PathVariable("modeVente") String modeVente) {
		return "{\"total_ttc\":" + commandeDump.getPanier().getPrixTtc() + ",\"total_ht\":"
				+ commandeDump.getPanier().getPrixHt() + "}";
	}
	

	@RequestMapping(value = "/afficherPopupModePaiement/{modePaiement}", method = RequestMethod.GET)
	@ResponseBody
	public String afficherPopupModePaiement(@PathVariable("modePaiement") Integer idModePaiement) {

		Double montant = 0D;
		if (EnumModePaiement.CB.getMode().equals(idModePaiement)){
			montant = commandeDump.getModePaiement().getCb();
		}else if (EnumModePaiement.CHEQUE.getMode().equals(idModePaiement)){
			montant = commandeDump.getModePaiement().getCheque();
		}else if (EnumModePaiement.ESPECE.getMode().equals(idModePaiement)){
			montant = commandeDump.getModePaiement().getEspece();
		}else if (EnumModePaiement.FIDELITE.getMode().equals(idModePaiement)){
			montant = commandeDump.getModePaiement().getFidelite();
		}else if (EnumModePaiement.TR.getMode().equals(idModePaiement)){
			montant = commandeDump.getModePaiement().getTicketRestau();
		}
		return montant != null ? montant.toString() : "";
	}

	@RequestMapping(value = "/payerEnPlusieursForme/{valeur}/{mode}", method = RequestMethod.GET)
	@ResponseBody
	public ModePaiement payerEnPlusieursForme(@PathVariable("valeur") Double valeur,@PathVariable("mode") Integer mode) {

		if (EnumModePaiement.CB.getMode().equals(mode)) {
			commandeDump.getModePaiement().setCb(valeur);
		} else if (EnumModePaiement.CHEQUE.getMode().equals(mode)) {
			commandeDump.getModePaiement().setCheque(valeur);
		} else if (EnumModePaiement.ESPECE.getMode().equals(mode)) {
			commandeDump.getModePaiement().setEspece(valeur);
		} else if (EnumModePaiement.FIDELITE.getMode().equals(mode)) {
			commandeDump.getModePaiement().setFidelite(valeur);
		} else if (EnumModePaiement.TR.getMode().equals(mode)) {
			commandeDump.getModePaiement().setTicketRestau(valeur);
		}

		return commandeDump.getModePaiement();
	}
	
	@RequestMapping(value = "/deletePaiement/{mode}", method = RequestMethod.GET)
	@ResponseBody
	public void deletePaiement(@PathVariable("mode") Integer mode) {

		if (EnumModePaiement.CB.getMode().equals(mode)) {
			commandeDump.getModePaiement().setCb(0D);
		} else if (EnumModePaiement.CHEQUE.getMode().equals(mode)) {
			commandeDump.getModePaiement().setCheque(0D);
		} else if (EnumModePaiement.ESPECE.getMode().equals(mode)) {
			commandeDump.getModePaiement().setEspece(0D);
		} else if (EnumModePaiement.FIDELITE.getMode().equals(mode)) {
			commandeDump.getModePaiement().setFidelite(0D);
		} else if (EnumModePaiement.TR.getMode().equals(mode)) {
			commandeDump.getModePaiement().setTicketRestau(0D);

		}
	}
	
	
	
}