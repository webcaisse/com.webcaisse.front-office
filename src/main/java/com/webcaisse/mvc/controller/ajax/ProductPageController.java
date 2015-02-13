package com.webcaisse.mvc.controller.ajax;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.common.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webcaisse.mvc.EnumModePaiement;
import com.webcaisse.mvc.bean.CommandeDump;
import com.webcaisse.mvc.bean.LignePanier;
import com.webcaisse.mvc.bean.ModePaiement;
import com.webcaisse.mvc.bean.Paiement;
import com.webcaisse.mvc.bean.RemiseProduit;
import com.webcaisse.mvc.in.NoteIn;
import com.webcaisse.service.CustomUser;
import com.webcaisse.ws.interfaces.CaisseManagerService;
import com.webcaisse.ws.model.ClientIn;
import com.webcaisse.ws.model.CommandeIn;
import com.webcaisse.ws.model.EtatCommandeIn;
import com.webcaisse.ws.model.LigneCommandeIn;
import com.webcaisse.ws.model.ProduitOut;

@Controller
@RequestMapping("/ajax/product")
public class ProductPageController {

	private static final String EURO = "EUR";
	private static final Float MAX_VALUE_REMISE = 1F;
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##");
	
	@Autowired
	CommandeDump commandeDump;


	@Autowired
	CaisseManagerService caisseManagerService;
	
	
	
	@RequestMapping("loadFamillies")
	@ResponseBody
	public JsonFamillyResponse loadFamillies() {
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		return new JsonFamillyResponse(caisseManagerService.getFamillesActivees(customUser
						.getSocieteId()));
	}

	@RequestMapping("/details/{produitId}")
	@ResponseBody
	public JsonProductResponse loadProductDetails(ModelMap model,@PathVariable Long produitId) {

		JsonProductResponse jsonProductResponse = new JsonProductResponse();

		jsonProductResponse.setDevise(EURO);

		ProduitOut produit = caisseManagerService.loadProductById(produitId);
		System.out.println("produits" + produit);

		if (produit != null) {
			jsonProductResponse.setNbResult(produit.getPrixOut() != null ? produit
							.getPrixOut().size() : 0);
		} else {
			jsonProductResponse.setNbResult(0);
		}

		jsonProductResponse.setProduitOut(produit);

		return jsonProductResponse;
	}

	@RequestMapping(value = "/ajouterAuPanier", method = RequestMethod.GET)
	public String ajouterAuPanier(@ModelAttribute("lignePanier") LignePanier lignePanier,ModelMap model) {

		if (lignePanier.getIdProduit() != null
				&& lignePanier.getIdPrix() != null) {

			ProduitOut produit = caisseManagerService.loadProductById(lignePanier.getIdProduit());

			if (produit != null) {
				Boolean ajouter = Boolean.TRUE;
				// on verifie que ce produit existe ou pas dans le panier

				Integer indexProduitDansPanier  = getIndexProduitExisteDansPanier(lignePanier.getIdProduit(), lignePanier.getIdPrix());
				if (indexProduitDansPanier!=-1){
					LignePanier lp  = this.commandeDump.getPanier().getLignesPanier().get(indexProduitDansPanier);
					if (lp!=null){
						Integer quantite  = lp.getQuantite();
						lp.setQuantite(quantite+1);
						
						model.put("lignePanier", lp);

						ajouter = Boolean.FALSE;
					}
				}
				// j'ajoute ce produit dans le panier

				if (ajouter==Boolean.TRUE){
					commandeDump.getPanier().addLine(lignePanier);

				

					model.put("lignePanier", lignePanier);
				}
				model.put("productName", produit.getLibelle());

			}
		}
		return "modules/product/lignePanier";
	}

	@RequestMapping(value = "/remiseProduit", method = RequestMethod.GET)
	@ResponseBody
	public String appliquerRemiseSurProduit(@ModelAttribute("remiseProduit") RemiseProduit remiseProduit, ModelMap model){
		if (remiseProduit.getIndexLignePanier()!=null && remiseProduit.getIndexLignePanier()<commandeDump.getPanier().getLignesPanier().size()
				&& remiseProduit.getRemiseValue()<=MAX_VALUE_REMISE){
			LignePanier lignePanier = commandeDump.getPanier().getLignesPanier().get(remiseProduit.getIndexLignePanier());
			lignePanier.setRemise(remiseProduit.getRemiseValue());

			Double nouveauPrix = (lignePanier.getPrix() - lignePanier.getPrix()
					* lignePanier.getRemise())
					* lignePanier.getQuantite();
			return DECIMAL_FORMAT.format(nouveauPrix);
		}

		return "PAS_DE_REMISE";
	}

	/**
	 * Pour dire si pour un produit et un prix donnée j'ai cette ligne dans le
	 * panier
	 * 
	 * @param idProduit
	 * @param idPrix
	 * @return
	 */
	private Integer getIndexProduitExisteDansPanier(Long idProduit, Long idPrix) {
		if (this.commandeDump.getPanier()!=null){
			for (LignePanier lp : this.commandeDump.getPanier().getLignesPanier()) {
				if (idProduit.equals(lp.getIdProduit()) && idPrix.equals(lp.getIdPrix())){
					return this.commandeDump.getPanier().getLignesPanier().indexOf(lp);
				}
			}
		}
		return -1;
	}

	@RequestMapping(value = "/supprimerDuPanier/{index}", method = RequestMethod.GET)
	@ResponseBody
	public void SupprimerDuPanier(@PathVariable("index") Integer index) {
		
		if (index != null && index>=0) {
			commandeDump.getPanier().supprimerDePanier(index);
		}
	}

	@RequestMapping(value = "/calculerPrixPanier", method = RequestMethod.GET)
	@ResponseBody
	public JsonPrixPanier calculerPrixPanier() {
		JsonPrixPanier jsonPrixPanier = new JsonPrixPanier();
		Double prixHt = 0D;
		Double prixTtc = 0D;

		for (LignePanier lignePanier : commandeDump.getPanier().getLignesPanier()) {
			
			prixTtc += (lignePanier.getPrix() - lignePanier.getPrix()* lignePanier.getRemise())*lignePanier.getQuantite();
//			if (lignePanier.getRemise()>0){
//				prixTtc -=prixTtc * lignePanier.getRemise();
//			}
			prixHt = prixTtc ;//- prixTtc * lignePanier.get
		}
		jsonPrixPanier.setPrixHt(prixHt);
		jsonPrixPanier.setPrixTtc(prixTtc);
		jsonPrixPanier.setDevise(EURO);
		commandeDump.getPanier().updatePrice(prixHt, prixTtc);
		return jsonPrixPanier;
	}

	@RequestMapping(value = "/incDec", method = RequestMethod.GET)
	public String incrementerDecrementer(Integer indexLignePanier, Integer quantite, ModelMap model) {
		
		if (indexLignePanier>=0 && indexLignePanier<commandeDump.getPanier().getLignesPanier().size()){
			LignePanier lignePanier = commandeDump.getPanier().getLignesPanier().get(indexLignePanier);
			
			Integer quantiteFinal  = lignePanier.getQuantite() + quantite;
			if (quantiteFinal<1){
				quantiteFinal = 1;
			}
			lignePanier.setQuantite(quantiteFinal);

			ProduitOut produit = caisseManagerService.loadProductById(lignePanier.getIdProduit());

			model.put("productName", produit.getLibelle());
			model.put("lignePanier", lignePanier);

		}
		return "modules/product/lignePanier";
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

	@RequestMapping(value = "/saisirMontantParModePaiement", method = RequestMethod.GET)
	@ResponseBody
	public void saisirMontantParModePaiement(@ModelAttribute("paiement") Paiement paiement) {
		
		if (EnumModePaiement.CB.getMode().equals(paiement.getIdModePaiement())){
			commandeDump.getModePaiement().setCb(paiement.getMontant());
		}else if (EnumModePaiement.CHEQUE.getMode().equals(paiement.getIdModePaiement())){
			commandeDump.getModePaiement().setCheque(paiement.getMontant());
		}else if (EnumModePaiement.ESPECE.getMode().equals(paiement.getIdModePaiement())){
			commandeDump.getModePaiement().setEspece(paiement.getMontant());
		}else if (EnumModePaiement.FIDELITE.getMode().equals(paiement.getIdModePaiement())){
			commandeDump.getModePaiement().setFidelite(paiement.getMontant());
		}else if (EnumModePaiement.TR.getMode().equals(paiement.getIdModePaiement())){
			commandeDump.getModePaiement().setTicketRestau(paiement.getMontant());
		}
		System.out.println(paiement.getIdModePaiement());
		System.out.println(paiement.getMontant());
		

	}

	@RequestMapping(value = "/viderPanierModePaiement", method = RequestMethod.GET)
	@ResponseBody
	public void viderPanierModePaiement() {
		commandeDump.getPanier().empty();
		commandeDump.getModePaiement().empty();
	}

	@RequestMapping(value = "/sauvegarderCommande/{modeVente}", method = RequestMethod.GET)
	@ResponseBody
	public Long sauvegarderCommande(@PathVariable("modeVente") String modeVente) {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		Long idCommande = null;
		CommandeIn commande = new CommandeIn();

		commande.setRegCB(commandeDump.getModePaiement().getCb());
		commande.setRegCarteFidelite(commandeDump.getModePaiement().getFidelite());
		commande.setRegCheque(commandeDump.getModePaiement().getCheque());
		commande.setRegEspece(commandeDump.getModePaiement().getEspece());
		commande.setRegTicketRestau(commandeDump.getModePaiement().getTicketRestau());
		commande.setIdSession(customUser.getSessionId());
		commande.setMontant(commandeDump.getPanier().getPrixTtc());
		commande.setNotes(commandeDump.getPanier().getMessage());
		commande.setMode(modeVente);

		ClientIn clientIn = new ClientIn();
		// clientIn.setId(client.getIdClient());
		clientIn.setNom(commandeDump.getClient().getNom());
		clientIn.setPrenom(commandeDump.getClient().getPrenom());
		clientIn.setEtage(commandeDump.getClient().getEtage());
		clientIn.setImmeuble(commandeDump.getClient().getImmeuble());
		clientIn.setInterphone(commandeDump.getClient().getInterphone());
		clientIn.setNomRue(commandeDump.getClient().getNomRue());
		clientIn.setNumeroRue(commandeDump.getClient().getNumeroRue());
		commande.setClientIn(clientIn);

		List<LigneCommandeIn> commandeIns = new ArrayList<LigneCommandeIn>();
		commande.setLignesCommandesIn(commandeIns);

		// faire un boucle sur panier.getLignePanier pour construire les
		// LigneCOmmandeIn
		if (!CollectionUtils.isEmpty(commandeDump.getPanier().getLignesPanier())) {
			for (LignePanier lignePanier : commandeDump.getPanier().getLignesPanier()) {
				LigneCommandeIn in = new LigneCommandeIn();
				in.setIdProduit(lignePanier.getIdProduit());
				in.setPrix(lignePanier.getPrix());
				in.setQuantite(lignePanier.getQuantite());
				in.setTotal(lignePanier.getPrix() * lignePanier.getQuantite());

				commandeIns.add(in);
			}
		}

		idCommande = caisseManagerService.sauvegarderCommande(commande);

		return idCommande;
	}

	@RequestMapping(value = "/afficherNotes")
	@ResponseBody
	public String ajouterNote(ModelMap model) {
		return commandeDump.getPanier().getMessage();
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
	
 	@RequestMapping(value = "/ajouterNotes/{note}")
 	@ResponseBody
	public String ajouterNote(@PathVariable("note") String notes) {
 		commandeDump.getPanier().setMessage(notes) ;
		return commandeDump.getPanier().getMessage();
	}

}

