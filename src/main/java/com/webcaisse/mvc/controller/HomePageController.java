package com.webcaisse.mvc.controller;

import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RequestMethod;

import com.webcaisse.mvc.bean.Panier;
import com.webcaisse.ws.interfaces.CaisseManagerService;
import com.webcaisse.ws.model.FamilleOut;
import com.webcaisse.ws.model.ProduitOut;

@Controller
public class HomePageController {
	
	private static final Long ID_SOCIETE = 1L;
	
	@Autowired
	CaisseManagerService caisseManagerService ;
		
	@RequestMapping("loginSuccess")
	public String home (ModelMap model){
		System.out.println("caisseManagerService " +caisseManagerService);
		


		List<FamilleOut> familles = caisseManagerService.getFamillesActivees(ID_SOCIETE);

		
		System.out.println("familles " +familles);

		model.put("familles", familles);

		return "acceuil";
	}

	@RequestMapping("ajax/loadFamilly")
	public String loadFamilly (ModelMap model){
//		//CaisseManagerService caisseManagerService = (CaisseManagerService) factory.create();
	System.out.println("caisseManagerService " +caisseManagerService);
		


		List<FamilleOut> familles = caisseManagerService.getFamillesActivees(ID_SOCIETE);

		
		System.out.println("familles " +familles);

		model.put("familles", familles);
	
		return "modules/familles";
	}
	
	@RequestMapping("ajax/loadProduct/{fammillyId}")
	public String loadProduct(ModelMap model,@PathVariable Long fammillyId){
		
		List<ProduitOut> produits = caisseManagerService.getProductsByFamilly(fammillyId);
		model.put("produits", produits);
		return "modules/familleDetails";
	}
	
}
