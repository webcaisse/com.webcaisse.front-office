package com.webcaisse.mvc.controller;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webcaisse.ws.CaisseManagerService;
import com.webcaisse.ws.Famille;
import com.webcaisse.ws.Produit;

@Controller
public class HomePageController {
	
	@Autowired
	JaxWsProxyFactoryBean factory;
		
	@RequestMapping("loginSuccess")
	public String home (ModelMap model){
		CaisseManagerService caisseManagerService = (CaisseManagerService) factory.create();
		System.out.println("caisseManagerService " +caisseManagerService);
		
		List<Famille> familles = caisseManagerService.getFamillesActivees();
		
		System.out.println("familles " +familles);

		model.put("familles", familles);

		return "acceuil";
	}
	
//	@RequestMapping("ajax/loadFamilly")
//	public String loadFamilly (ModelMap model){
//		CaisseManagerService caisseManagerService = (CaisseManagerService) factory.create();
//		System.out.println("caisseManagerService " +caisseManagerService);
//		
//		List<Famille> familles = caisseManagerService.getFamillesActivees();
//		
//		System.out.println("familles " +familles);
//
//		model.put("familles", familles);
//	
//		return "modules/familles";
//	}
	
	@RequestMapping("ajax/loadProduct")
	public String loadProduct(ModelMap model){
		CaisseManagerService caisseManagerService = (CaisseManagerService) factory.create();
		System.out.println("caisseManagerService " +caisseManagerService);
		
		List<Produit> produits = caisseManagerService.getProduitParFamilleReference("001");
		model.put("produits", produits);
		return "modules/familleDetails";
	}
}
