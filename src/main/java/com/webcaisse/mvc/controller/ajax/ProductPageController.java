package com.webcaisse.mvc.controller.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webcaisse.mvc.bean.LignePanier;
import com.webcaisse.mvc.bean.Panier;
import com.webcaisse.ws.interfaces.CaisseManagerService;
import com.webcaisse.ws.model.ProduitOut;

@Controller
@RequestMapping("/ajax/product")
public class ProductPageController {

	@Autowired
	CaisseManagerService caisseManagerService;

	@Autowired
	Panier panier;

	@RequestMapping("/details/{produitId}")
	@ResponseBody
	public JsonProductResponse loadProductDetails(ModelMap model,
			@PathVariable Long produitId) {

		JsonProductResponse jsonProductResponse = new JsonProductResponse();

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

	@RequestMapping(value="/ajouterAuPanier", method=RequestMethod.POST)
	public void ajouterAuPanier(@ModelAttribute("lignePanier") LignePanier LignePanier) {

		if (LignePanier.getIdProduit()!=null){
			
			// j'ajoute ce produit dans le panier
			panier.addProduct(LignePanier);
		}
	}
}
