package com.webcaisse.mvc.controller.ajax;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;

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
import com.webcaisse.mvc.bean.Paiement;
import com.webcaisse.mvc.bean.RemiseProduit;
import com.webcaisse.mvc.in.NoteIn;
import com.webcaisse.service.CustomUser;
import com.webcaisse.ws.interfaces.CaisseManagerService;
import com.webcaisse.ws.model.ProduitOut;

@Controller
@RequestMapping("/ajax/product")
public class ProductPageController {

	private static final String EURO = "EUR";
	private static final Float MAX_VALUE_REMISE = 1F;
<<<<<<< HEAD
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat( "#,##" );

	@Autowired
	CaisseManagerService caisseManagerService;
	
	@Autowired
	CommandeDump commandeDump;
=======
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(
			"#,##");
	@Autowired
	CaisseManagerService caisseManagerService;

	@Autowired
	Panier panier;

	@Autowired
	ModePaiement modePaiement;

	@Autowired
	Client client;
>>>>>>> lost_branch

	@RequestMapping("loadFamillies")
	@ResponseBody
	public JsonFamillyResponse loadFamillies() {
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		return new JsonFamillyResponse(
				caisseManagerService.getFamillesActivees(customUser
						.getSocieteId()));
	}

	@RequestMapping("/details/{produitId}")
	@ResponseBody
	public JsonProductResponse loadProductDetails(ModelMap model,
			@PathVariable Long produitId) {

		JsonProductResponse jsonProductResponse = new JsonProductResponse();

		jsonProductResponse.setDevise(EURO);

		ProduitOut produit = caisseManagerService.loadProductById(produitId);
		System.out.println("produits" + produit);

		if (produit != null) {
			jsonProductResponse
					.setNbResult(produit.getPrixOut() != null ? produit
							.getPrixOut().size() : 0);
		} else {
			jsonProductResponse.setNbResult(0);
		}

		jsonProductResponse.setProduitOut(produit);

		return jsonProductResponse;
	}

	@RequestMapping(value = "/ajouterAuPanier", method = RequestMethod.GET)
	public String ajouterAuPanier(
			@ModelAttribute("lignePanier") LignePanier lignePanier,
			ModelMap model) {

		if (lignePanier.getIdProduit() != null
				&& lignePanier.getIdPrix() != null) {

			ProduitOut produit = caisseManagerService
					.loadProductById(lignePanier.getIdProduit());

			if (produit != null) {
				Boolean ajouter = Boolean.TRUE;
				// on verifie que ce produit existe ou pas dans le panier
<<<<<<< HEAD
				Integer indexProduitDansPanier  = getIndexProduitExisteDansPanier(lignePanier.getIdProduit(), lignePanier.getIdPrix());
				if (indexProduitDansPanier!=-1){
					LignePanier lp  = this.commandeDump.getPanier().getLignesPanier().get(indexProduitDansPanier);
					if (lp!=null){
						Integer quantite  = lp.getQuantite();
						lp.setQuantite(quantite+1);
						
=======
				Integer indexProduitDansPanier = getIndexProduitExisteDansPanier(
						lignePanier.getIdProduit(), lignePanier.getIdPrix());
				if (indexProduitDansPanier != -1) {
					LignePanier lp = this.panier.getLignesPanier().get(
							indexProduitDansPanier);
					if (lp != null) {
						Integer quantite = lp.getQuantite();
						lp.setQuantite(quantite + 1);

>>>>>>> lost_branch
						model.put("lignePanier", lp);

						ajouter = Boolean.FALSE;
					}
				}
				// j'ajoute ce produit dans le panier
<<<<<<< HEAD
				if (ajouter==Boolean.TRUE){
					commandeDump.getPanier().addLine(lignePanier);
=======
				if (ajouter == Boolean.TRUE) {
					panier.addLine(lignePanier);
>>>>>>> lost_branch
					model.put("lignePanier", lignePanier);
				}
				model.put("productName", produit.getLibelle());

			}
		}
		return "modules/product/lignePanier";
	}

	@RequestMapping(value = "/remiseProduit", method = RequestMethod.GET)
	@ResponseBody
<<<<<<< HEAD
	public String appliquerRemiseSurProduit(@ModelAttribute("remiseProduit") RemiseProduit remiseProduit, ModelMap model){
		if (remiseProduit.getIndexLignePanier()!=null && remiseProduit.getIndexLignePanier()<commandeDump.getPanier().getLignesPanier().size()
				&& remiseProduit.getRemiseValue()<=MAX_VALUE_REMISE){
			LignePanier lignePanier = commandeDump.getPanier().getLignesPanier().get(remiseProduit.getIndexLignePanier());
=======
	public String appliquerRemiseSurProduit(
			@ModelAttribute("remiseProduit") RemiseProduit remiseProduit,
			ModelMap model) {
		if (remiseProduit.getIndexLignePanier() != null
				&& remiseProduit.getIndexLignePanier() < panier
						.getLignesPanier().size()
				&& remiseProduit.getRemiseValue() <= MAX_VALUE_REMISE) {
			LignePanier lignePanier = panier.getLignesPanier().get(
					remiseProduit.getIndexLignePanier());
>>>>>>> lost_branch
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
<<<<<<< HEAD
		if (this.commandeDump.getPanier()!=null){
			for (LignePanier lp : this.commandeDump.getPanier().getLignesPanier()) {
				if (idProduit.equals(lp.getIdProduit()) && idPrix.equals(lp.getIdPrix())){
					return this.commandeDump.getPanier().getLignesPanier().indexOf(lp);
=======
		if (this.panier != null) {
			for (LignePanier lp : this.panier.getLignesPanier()) {
				if (idProduit.equals(lp.getIdProduit())
						&& idPrix.equals(lp.getIdPrix())) {
					return this.panier.getLignesPanier().indexOf(lp);
>>>>>>> lost_branch
				}
			}
		}
		return -1;
	}

	@RequestMapping(value = "/supprimerDuPanier/{index}", method = RequestMethod.GET)
	@ResponseBody
	public void SupprimerDuPanier(@PathVariable("index") Integer index) {
<<<<<<< HEAD
		
		if (index != null && index>=0) {
			commandeDump.getPanier().supprimerDePanier(index);
=======

		if (index != null && index >= 0) {
			panier.supprimerDePanier(index);
>>>>>>> lost_branch
		}
	}

	@RequestMapping(value = "/calculerPrixPanier", method = RequestMethod.GET)
	@ResponseBody
	public JsonPrixPanier calculerPrixPanier() {
		JsonPrixPanier jsonPrixPanier = new JsonPrixPanier();
		Double prixHt = 0D;
		Double prixTtc = 0D;

<<<<<<< HEAD
		for (LignePanier lignePanier : commandeDump.getPanier().getLignesPanier()) {
			
			prixTtc += (lignePanier.getPrix() - lignePanier.getPrix()* lignePanier.getRemise())*lignePanier.getQuantite();
//			if (lignePanier.getRemise()>0){
//				prixTtc -=prixTtc * lignePanier.getRemise();
//			}
			prixHt = prixTtc ;//- prixTtc * lignePanier.get
=======
		for (LignePanier lignePanier : panier.getLignesPanier()) {

			prixTtc += (lignePanier.getPrix() - lignePanier.getPrix()
					* lignePanier.getRemise())
					* lignePanier.getQuantite();
			// if (lignePanier.getRemise()>0){
			// prixTtc -=prixTtc * lignePanier.getRemise();
			// }
			prixHt = prixTtc;// - prixTtc * lignePanier.get
>>>>>>> lost_branch
		}
		jsonPrixPanier.setPrixHt(prixHt);
		jsonPrixPanier.setPrixTtc(prixTtc);
		jsonPrixPanier.setDevise(EURO);
		commandeDump.getPanier().updatePrice(prixHt, prixTtc);
		return jsonPrixPanier;
	}

	@RequestMapping(value = "/incDec", method = RequestMethod.GET)
<<<<<<< HEAD
	public String incrementerDecrementer(Integer indexLignePanier, Integer quantite, ModelMap model) {
		
		if (indexLignePanier>=0 && indexLignePanier<commandeDump.getPanier().getLignesPanier().size()){
			LignePanier lignePanier = commandeDump.getPanier().getLignesPanier().get(indexLignePanier);
			
			Integer quantiteFinal  = lignePanier.getQuantite() + quantite;
			if (quantiteFinal<1){
=======
	public String incrementerDecrementer(Integer indexLignePanier,
			Integer quantite, ModelMap model) {

		if (indexLignePanier >= 0
				&& indexLignePanier < panier.getLignesPanier().size()) {
			LignePanier lignePanier = panier.getLignesPanier().get(
					indexLignePanier);

			Integer quantiteFinal = lignePanier.getQuantite() + quantite;
			if (quantiteFinal < 1) {
>>>>>>> lost_branch
				quantiteFinal = 1;
			}
			lignePanier.setQuantite(quantiteFinal);

			ProduitOut produit = caisseManagerService
					.loadProductById(lignePanier.getIdProduit());

			model.put("productName", produit.getLibelle());
			model.put("lignePanier", lignePanier);

		}
		return "modules/product/lignePanier";
	}

	@RequestMapping(value = "/afficherPopupPaiement/{modeVente}", method = RequestMethod.GET)
	@ResponseBody
	public String afficherPopupPaiement(
			@PathVariable("modeVente") String modeVente) {
		return "{\"total_ttc\":" + panier.getPrixTtc() + ",\"total_ht\":"
				+ panier.getPrixHt() + "}";
	}

	@RequestMapping(value = "/afficherPopupModePaiement/{modePaiement}", method = RequestMethod.GET)
	@ResponseBody
	public String afficherPopupModePaiement(
			@PathVariable("modePaiement") Integer idModePaiement) {

		Double montant = 0D;
<<<<<<< HEAD
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
=======
		if (EnumModePaiement.CB.getMode().equals(idModePaiement)) {
			montant = modePaiement.getCb();
		} else if (EnumModePaiement.CHEQUE.getMode().equals(idModePaiement)) {
			montant = modePaiement.getCheque();
		} else if (EnumModePaiement.ESPECE.getMode().equals(idModePaiement)) {
			montant = modePaiement.getEspece();
		} else if (EnumModePaiement.FIDELITE.getMode().equals(idModePaiement)) {
			montant = modePaiement.getFidelite();
		} else if (EnumModePaiement.TR.getMode().equals(idModePaiement)) {
			montant = modePaiement.getTicketRestau();
>>>>>>> lost_branch
		}
		return montant != null ? montant.toString() : "";
	}

	@RequestMapping(value = "/saisirMontantParModePaiement", method = RequestMethod.GET)
	@ResponseBody
<<<<<<< HEAD
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
=======
	public void saisirMontantParModePaiement(
			@ModelAttribute("paiement") Paiement paiement) {

		if (EnumModePaiement.CB.getMode().equals(paiement.getIdModePaiement())) {
			modePaiement.setCb(paiement.getMontant());
			modePaiement.setLibelle("Cb");
		} else if (EnumModePaiement.CHEQUE.getMode().equals(
				paiement.getIdModePaiement())) {
			modePaiement.setCheque(paiement.getMontant());
			modePaiement.setLibelle("Cheque");
		} else if (EnumModePaiement.ESPECE.getMode().equals(
				paiement.getIdModePaiement())) {
			modePaiement.setEspece(paiement.getMontant());
			modePaiement.setLibelle("Espece");
		} else if (EnumModePaiement.FIDELITE.getMode().equals(
				paiement.getIdModePaiement())) {
			modePaiement.setFidelite(paiement.getMontant());
			modePaiement.setLibelle("fidelite");
		} else if (EnumModePaiement.TR.getMode().equals(
				paiement.getIdModePaiement())) {
			modePaiement.setTicketRestau(paiement.getMontant());
>>>>>>> lost_branch
		}
		System.out.println(paiement.getIdModePaiement());
		System.out.println(paiement.getMontant());
		System.out.println(modePaiement.getLibelle());

	}

	@RequestMapping(value = "/viderPanierModePaiement", method = RequestMethod.GET)
	@ResponseBody
	public void viderPanierModePaiement() {
		commandeDump.getPanier().empty();
		commandeDump.getModePaiement().empty();
	}
<<<<<<< HEAD
	
=======

	@RequestMapping(value = "/sauvegarderCommande/{modeVente}", method = RequestMethod.GET)
	@ResponseBody
	public Long sauvegarderCommande(@PathVariable("modeVente") String modeVente) {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		Long idCommande = null;
		CommandeIn commande = new CommandeIn();

		commande.setRegCB(modePaiement.getCb());
		commande.setRegCarteFidelite(modePaiement.getFidelite());
		commande.setRegCheque(modePaiement.getCheque());
		commande.setRegEspece(modePaiement.getEspece());
		commande.setRegTicketRestau(modePaiement.getTicketRestau());
		commande.setIdSession(customUser.getSessionId());
		commande.setMontant(panier.getPrixTtc());
		commande.setNotes(panier.getMessage());
		commande.setMode(modeVente);

		ClientIn clientIn = new ClientIn();
		// clientIn.setId(client.getIdClient());
		clientIn.setNom(client.getNom());
		clientIn.setPrenom(client.getPrenom());
		clientIn.setEtage(client.getEtage());
		clientIn.setImmeuble(client.getImmeuble());
		clientIn.setInterphone(client.getInterphone());
		clientIn.setNomRue(client.getNomRue());
		clientIn.setNumeroRue(client.getNumeroRue());

		commande.setClientIn(clientIn);

		List<LigneCommandeIn> commandeIns = new ArrayList<LigneCommandeIn>();
		commande.setLignesCommandesIn(commandeIns);

		// faire un boucle sur panier.getLignePanier pour construire les
		// LigneCOmmandeIn
		if (!CollectionUtils.isEmpty(panier.getLignesPanier())) {
			for (LignePanier lignePanier : panier.getLignesPanier()) {
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

>>>>>>> lost_branch
	@RequestMapping(value = "/afficherNotes")
	@ResponseBody
	public String ajouterNote(ModelMap model) {
		return commandeDump.getPanier().getMessage();
	}

	@RequestMapping(value = "/payerEnPlusieursForme/{valeur}/{mode}", method = RequestMethod.GET)
	@ResponseBody
	public ModePaiement payerEnPlusieursForme(
			@PathVariable("valeur") Double valeur,
			@PathVariable("mode") Integer mode) {

		if (EnumModePaiement.CB.getMode().equals(mode)) {
			modePaiement.setCb(valeur);
		} else if (EnumModePaiement.CHEQUE.getMode().equals(mode)) {
			modePaiement.setCheque(valeur);
		} else if (EnumModePaiement.ESPECE.getMode().equals(mode)) {
			modePaiement.setEspece(valeur);
		} else if (EnumModePaiement.FIDELITE.getMode().equals(mode)) {
			modePaiement.setFidelite(valeur);
		} else if (EnumModePaiement.TR.getMode().equals(mode)) {
			modePaiement.setTicketRestau(valeur);
		}

		return modePaiement;
	}

	@RequestMapping(value = "/deletePaiement/{mode}", method = RequestMethod.GET)
	@ResponseBody
	public void deletePaiement(@PathVariable("mode") Integer mode) {

		if (EnumModePaiement.CB.getMode().equals(mode)) {
			modePaiement.setCb(0D);
		} else if (EnumModePaiement.CHEQUE.getMode().equals(mode)) {
			modePaiement.setCheque(0D);
		} else if (EnumModePaiement.ESPECE.getMode().equals(mode)) {
			modePaiement.setEspece(0D);
		} else if (EnumModePaiement.FIDELITE.getMode().equals(mode)) {
			modePaiement.setFidelite(0D);
		} else if (EnumModePaiement.TR.getMode().equals(mode)) {
			modePaiement.setTicketRestau(0D);

		}
	}
	
 	@RequestMapping(value = "/ajouterNotes/{note}")
 	@ResponseBody
	public String ajouterNote(@PathVariable("note") String notes) {
		panier.setMessage(notes) ;
		return panier.getMessage();
	}

}
