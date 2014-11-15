package com.webcaisse.mvc.controller.ajax;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webcaisse.mvc.bean.LignePanier;
import com.webcaisse.mvc.bean.ModePaiement;
import com.webcaisse.mvc.bean.Panier;
import com.webcaisse.mvc.bean.RemiseProduit;
import com.webcaisse.ws.interfaces.CaisseManagerService;
import com.webcaisse.ws.model.ProduitOut;

@Controller
@RequestMapping("/ajax/product")
public class ProductPageController {
	
	private static final Long ID_SOCIETE = 1L;
	private static final String EURO ="EUR";
	private static final Float MAX_VALUE_REMISE = 1F;
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat( "#,##" );
	@Autowired
	CaisseManagerService caisseManagerService;

	@Autowired
	Panier panier;

	@RequestMapping("loadFamillies")
	@ResponseBody
	public JsonFamillyResponse loadFamillies() {
		return new JsonFamillyResponse(caisseManagerService.getFamillesActivees(ID_SOCIETE));
	}

	@RequestMapping("/details/{produitId}")
	@ResponseBody
	public JsonProductResponse loadProductDetails(ModelMap model, @PathVariable Long produitId) {

		JsonProductResponse jsonProductResponse = new JsonProductResponse();
		
		jsonProductResponse.setDevise(EURO);

		ProduitOut produit = caisseManagerService.loadProductById(produitId);
		System.out.println("produits" + produit);

		if (produit != null) {
			jsonProductResponse.setNbResult(produit.getPrixOut() != null ? produit.getPrixOut().size() : 0);
		} else {
			jsonProductResponse.setNbResult(0);
		}

		jsonProductResponse.setProduitOut(produit);

		return jsonProductResponse;
	}

	@RequestMapping(value = "/ajouterAuPanier", method = RequestMethod.GET)
	public String ajouterAuPanier(@ModelAttribute("lignePanier") LignePanier lignePanier, ModelMap model) {

		if (lignePanier.getIdProduit() != null && lignePanier.getIdPrix()!=null) {

			ProduitOut produit = caisseManagerService.loadProductById(lignePanier.getIdProduit());

			if (produit != null) {
				Boolean ajouter  = Boolean.TRUE;
				// on verifie que ce produit existe ou pas dans le panier
				Integer indexProduitDansPanier  = getIndexProduitExisteDansPanier(lignePanier.getIdProduit(), lignePanier.getIdPrix());
				if (indexProduitDansPanier!=-1){
					LignePanier lp  = this.panier.getLignesPanier().get(indexProduitDansPanier);
					if (lp!=null){
						Integer quantite  = lp.getQuantite();
						lp.setQuantite(quantite+1);
						
						model.put("lignePanier", lp);
						
						ajouter = Boolean.FALSE;
					}
				}
				// j'ajoute ce produit dans le panier
				if (ajouter==Boolean.TRUE){
					panier.addLine(lignePanier);
					model.put("lignePanier", lignePanier);
				}
				model.put("productName", produit.getLibelle());

			}
		}
		return "modules/product/lignePanier";
	}

	@RequestMapping(value="/remiseProduit", method=RequestMethod.GET)
	@ResponseBody
	public String appliquerRemiseSurProduit(@ModelAttribute("remiseProduit") RemiseProduit remiseProduit, ModelMap model){
		if (remiseProduit.getIndexLignePanier()!=null && remiseProduit.getIndexLignePanier()<panier.getLignesPanier().size()
				&& remiseProduit.getRemiseValue()<=MAX_VALUE_REMISE){
			LignePanier lignePanier = panier.getLignesPanier().get(remiseProduit.getIndexLignePanier());
			lignePanier.setRemise(remiseProduit.getRemiseValue());
			
			Double nouveauPrix  = (lignePanier.getPrix() - lignePanier.getPrix()*lignePanier.getRemise())* lignePanier.getQuantite();
			return DECIMAL_FORMAT.format(nouveauPrix);
		}
		
		return "PAS_DE_REMISE";
	}

	/**
	 * Pour dire si pour un produit et un prix donnée j'ai cette ligne dans le panier
	 * @param idProduit
	 * @param idPrix
	 * @return
	 */
	private Integer getIndexProduitExisteDansPanier(Long idProduit, Long idPrix) {
		if (this.panier!=null){
			for (LignePanier lp : this.panier.getLignesPanier()) {
				if (idProduit.equals(lp.getIdProduit()) && idPrix.equals(lp.getIdPrix())){
					return this.panier.getLignesPanier().indexOf(lp);
				}
			}
		}
		return -1;
	}

	@RequestMapping(value = "/supprimerDuPanier/{index}", method = RequestMethod.GET)
	@ResponseBody
	public void SupprimerDuPanier(@PathVariable("index") Integer index) {
		
		if (index != null && index>=0) {
			panier.supprimerDePanier(index);
		}
	}

	@RequestMapping(value = "/calculerPrixPanier", method = RequestMethod.GET)
	@ResponseBody
	public JsonPrixPanier calculerPrixPanier() {
		JsonPrixPanier jsonPrixPanier = new JsonPrixPanier();
		Double prixHt = 0D;
		Double prixTtc = 0D;

		for (LignePanier lignePanier : panier.getLignesPanier()) {
			
			prixTtc += (lignePanier.getPrix() - lignePanier.getPrix()* lignePanier.getRemise())*lignePanier.getQuantite();
//			if (lignePanier.getRemise()>0){
//				prixTtc -=prixTtc * lignePanier.getRemise();
//			}
			prixHt = prixTtc ;//- prixTtc * lignePanier.get
		}
		jsonPrixPanier.setPrixHt(prixHt);
		jsonPrixPanier.setPrixTtc(prixTtc);
		jsonPrixPanier.setDevise(EURO);
		panier.updatePrice(prixHt, prixTtc);
		return jsonPrixPanier;
	}

	@RequestMapping(value = "/viderPanier", method = RequestMethod.GET)
	@ResponseBody
	public void viderPanier() {
		panier.empty();
	}

	@RequestMapping(value = "/incDec", method = RequestMethod.GET)
	public String incrementerDecrementer(Integer indexLignePanier, Integer quantite, ModelMap model) {
		
		if (indexLignePanier>=0 && indexLignePanier<panier.getLignesPanier().size()){
			LignePanier lignePanier = panier.getLignesPanier().get(indexLignePanier);
			
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
	
	
	@RequestMapping(value = "/ajouterNote/{message}", method = RequestMethod.GET)
	@ResponseBody
	public String ajouterNote(@PathVariable String message, ModelMap model) {
		String messageActuel  = panier.getMessage();
		panier.setMessage((messageActuel!=null?message:"")+message) ;
		return message;
	}

	@RequestMapping(value = "/saisirModePaiement", method = RequestMethod.GET)
	@ResponseBody
	public void saisirModePaiement(Double montant,@ModelAttribute("modePaiement") ModePaiement modePaiement ) {
	
		if(montant != null && modePaiement != null){
			modePaiement.setCb(montant);
			modePaiement.setCheque(montant);
			modePaiement.setEspace(montant);
			modePaiement.setFidelite(montant);
			modePaiement.setTicketRestau(montant);
		}
		
	}
}
	
	
	

