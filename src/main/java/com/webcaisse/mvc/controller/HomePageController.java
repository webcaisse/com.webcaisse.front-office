package com.webcaisse.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webcaisse.service.CustomUser;
import com.webcaisse.ws.interfaces.CaisseManagerService;
import com.webcaisse.ws.interfaces.ClientManagerService;
import com.webcaisse.ws.model.ClientIn;
import com.webcaisse.ws.model.FamilleOut;
import com.webcaisse.ws.model.ProduitOut;

@Controller
public class HomePageController {

	@Autowired
	CaisseManagerService caisseManagerService;
	
	@Autowired
	ClientManagerService clientManagerService;

	@RequestMapping({"/loginSuccess","/"})
	public String home(ModelMap model) {
		
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		System.out.println("caisseManagerService " + caisseManagerService);
		List<FamilleOut> familles = caisseManagerService
				.getFamillesActivees(customUser.getSocieteId());
		System.out.println("familles " + familles);

		model.put("familles", familles);

		model.put("clientIn", new ClientIn());
		return "acceuil";
	}

	@RequestMapping("ajax/loadFamilly")
	public String loadFamilly(ModelMap model) {
		// //CaisseManagerService caisseManagerService = (CaisseManagerService)
		// factory.create();
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		System.out.println("caisseManagerService " + caisseManagerService);

		List<FamilleOut> familles = caisseManagerService.getFamillesActivees(customUser.getSocieteId());

		System.out.println("familles " + familles);

		model.put("familles", familles);

		return "modules/familles";
	}

	@RequestMapping("ajax/loadProduct/{fammillyId}")
	public String loadProduct(ModelMap model, @PathVariable Long fammillyId) {

		List<ProduitOut> produits = caisseManagerService.getProductsByFamilly(fammillyId);
		model.put("produits", produits);
		return "modules/familleDetails";
	}

}
