package com.webcaisse.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webcaisse.service.CustomUser;
import com.webcaisse.ws.interfaces.CaisseManagerService;
import com.webcaisse.ws.model.FamilleOut;
import com.webcaisse.ws.model.ProduitIn;
import com.webcaisse.ws.model.ProduitOut;

@Controller
@RequestMapping("/produits")
public class ProduitPageController {

	@Autowired
	CaisseManagerService caisseManagerService;

	@RequestMapping("afficher")
	public String afficherProduits(ModelMap model) {
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<FamilleOut> familles = caisseManagerService.getFamillesActivees(customUser.getSocieteId());

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

	@RequestMapping(value="/afficherFormulaire/{famillyId}", method = RequestMethod.GET)
	public String afficherFormulaire(Model model, @PathVariable("famillyId") Long famillyId) {
		ProduitIn in = new ProduitIn();
		in.setFamilleId(famillyId);
        model.addAttribute("produit", in) ;
		return "ajoutproduits";
	}

	@RequestMapping(value="/ajouterProduits",method = RequestMethod.POST)
	public String AjouterProduits(@ModelAttribute("produitIn") ProduitIn produit) {
		
		//ProduitOut produitOut = new ProduitOut() ;
		caisseManagerService.ajouterProduit(produit);
		return "redirect:/produits/listeProduits?idFamilly="+produit.getFamilleId();
	}

	@RequestMapping("/supprimerProduit/{idProduit}")
	public String supprimerProduit(@PathVariable("idProduit") Long idProduit) {

		ProduitOut produitOut = caisseManagerService.loadProductById(idProduit);

		caisseManagerService.supprimerProduit(idProduit);

		return "redirect:/produits/listeProduits?idFamilly=" + produitOut.getFamilleId();
	}
	
 @RequestMapping(value="/afficherUpdateProduct/{idProduit}", method=RequestMethod.GET)
 public String afficherUpadateProduct(Model model , @PathVariable("idProduit") Long idProduit)
 {
	 ProduitOut produitOut = caisseManagerService.loadProductById(idProduit) ;
	 ProduitIn in = new ProduitIn();
	 in.setCode(produitOut.getCode());
	 in.setLibelle(produitOut.getLibelle());
	 in.setFamilleId(produitOut.getFamilleId());
	 in.setId(idProduit);
	 
	 model.addAttribute("produit", in) ;
	 return "formulaireMaj" ;
 }
 
 @RequestMapping(value="/saveUpdateProduct",method = RequestMethod.POST)
	public String saveUpdate(@ModelAttribute("produitIn") ProduitIn produit) {
		
   //  ProduitOut produitOut = caisseManagerService.loadProductById(produit.getId()) ;
	 
		caisseManagerService.updateProduit(produit);
		return "redirect:/produits/listeProduits?idFamilly="+produit.getFamilleId();
	}
 
	
	
}
