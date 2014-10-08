package com.webcaisse.mvc.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.webcaisse.ws.interfaces.CaisseManagerService;
import com.webcaisse.ws.model.FamilleOut;
import com.webcaisse.ws.model.ProduitOut;

@Controller
public class HomePageController {
	
	@Autowired
	CaisseManagerService caisseManagerService ;
	//JaxWsProxyFactoryBean factory;
		
	@RequestMapping("loginSuccess")
	public String home (ModelMap model){
		System.out.println("caisseManagerService " +caisseManagerService);
		
		List<FamilleOut> familles = caisseManagerService.getFamillesActivees();
		
		System.out.println("familles " +familles);

		model.put("familles", familles);

		return "acceuil";
	}
	
	@RequestMapping("ajax/loadFamilly")
	public String loadFamilly (ModelMap model){
//		//CaisseManagerService caisseManagerService = (CaisseManagerService) factory.create();
	System.out.println("caisseManagerService " +caisseManagerService);
		
		List<FamilleOut> familles = caisseManagerService.getFamillesActivees();
		
		System.out.println("familles " +familles);

		model.put("familles", familles);
	
	return "modules/familles";
	}
	
	

	@RequestMapping("ajax/loadProduct/{fammillyId}")
	public String loadProduct(ModelMap model,@PathVariable Long fammillyId){
		
		//CaisseManagerService caisseManagerService = (CaisseManagerService) factory.create();
		System.out.println("caisseManagerService " +caisseManagerService);
		
		List<ProduitOut> produits = caisseManagerService.getProductsByFamilly(fammillyId);
		System.out.println("produits" +produits);
		model.put("produits", produits);
		

		return "modules/familleDetails";
	}
}
