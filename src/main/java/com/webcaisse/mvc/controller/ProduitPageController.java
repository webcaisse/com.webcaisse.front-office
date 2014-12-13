package com.webcaisse.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webcaisse.ws.interfaces.CaisseManagerService;
import com.webcaisse.ws.model.FamilleOut;
import com.webcaisse.ws.model.ProduitIn;
import com.webcaisse.ws.model.ProduitOut;

@Controller
@RequestMapping("/produits")
public class ProduitPageController {

	@Autowired
	CaisseManagerService caisseManagerService;

	private static final Long ID_SOCIETE = 1L;

	@RequestMapping("afficher")
	public String afficherProduits(ModelMap model) {

		List<FamilleOut> familles = caisseManagerService.getFamillesActivees(ID_SOCIETE);

		System.out.println("familles " + familles);

		model.put("familles", familles);

		return "produits";
	}

	@RequestMapping("/listeProduits")
	public String listeProduits(Model model, @RequestParam(value = "idFamilly") Long idFamilly) {
		model.addAttribute("idFamilly", idFamilly);

		List<ProduitOut> produits = caisseManagerService.getProductsByFamilly(idFamilly);

		model.addAttribute("produits", produits);
		return "listeProduits";
	}

	@RequestMapping(value="/afficherFormulaire", method = RequestMethod.GET)
	public String afficherFormulaire(Model model) {
        model.addAttribute("produit", new ProduitIn()) ;
		return "ajoutproduits";
	}

	@RequestMapping(value="/ajouterProduits",method = RequestMethod.POST)
	public String AjouterProduits(ProduitIn produit) {
		
		ProduitOut produitOut = new ProduitOut() ;
		caisseManagerService.ajouterProduit(produit, produitOut.getFamilleId());
	
	

		return "redirect:/produits/listeProduits?idFamilly="+produitOut.getFamilleId();
	}

	@RequestMapping("/supprimerProduit/{idProduit}")
	public String supprimerProduit(@PathVariable("idProduit") Long idProduit) {

		ProduitOut produitOut = caisseManagerService.loadProductById(idProduit);

		caisseManagerService.supprimerProduit(idProduit);

		return "redirect:/produits/listeProduits?idFamilly=" + produitOut.getFamilleId();
	}

}
