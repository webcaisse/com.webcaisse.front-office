package com.webcaisse.mvc.controller.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webcaisse.mvc.bean.LignePanier;
import com.webcaisse.mvc.bean.Panier;
import com.webcaisse.ws.interfaces.CaisseManagerService;
import com.webcaisse.ws.model.ProduitOut;

@Controller
@RequestMapping("/ajax/product")
public class ProductPageController {

	private static final String EURO ="EUR";
	@Autowired
	CaisseManagerService caisseManagerService;

	@Autowired
	Panier panier;

	@RequestMapping("loadFamillies")
	@ResponseBody
	public JsonFamillyResponse loadFamillies() {

		return new JsonFamillyResponse(caisseManagerService.getFamillesActivees());
	}

	@RequestMapping("/details/{produitId}")
	@ResponseBody
	public JsonProductResponse loadProductDetails(ModelMap model, @PathVariable Long produitId) {

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
	public String ajouterAuPanier(@ModelAttribute("lignePanier") LignePanier LignePanier, ModelMap model) {

		if (LignePanier.getIdProduit() != null) {

			ProduitOut produit = caisseManagerService.loadProductById(LignePanier.getIdProduit());

			if (produit != null) {
				
				// nombre de ligne dans le panier 
				Integer nbLignes  = this.panier.getLignesPanier().size();
				
				// j'ajoute ce produit dans le panier
				panier.addProduct(LignePanier);
				
				model.put("productName", produit.getLibelle());
				model.put("productPrice", LignePanier.getPrix());
				model.put("lignePanierIndex", nbLignes);
			}

		}
		return "modules/product/lignePanier";
	}


	@RequestMapping(value = "/supprimerDuPanier/{index}", method = RequestMethod.GET)
	@ResponseBody
	public void SupprimerDuPanier(@PathVariable("index") Integer index) {
		
		if (index != null && index>0) {
			panier.supprimerDePanier(index);
		}
	}

	@RequestMapping(value = "/calculerPrixPanier", method = RequestMethod.GET)
	@ResponseBody
	public String calculerPrixPanier() {
		return panier.getPrixttc().toString();
	}

	@RequestMapping(value = "/viderPanier", method = RequestMethod.GET)
	@ResponseBody
	public void viderPanier() {
		panier.empty();
	}

}
