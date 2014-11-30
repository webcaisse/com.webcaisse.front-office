package com.webcaisse.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webcaisse.ws.interfaces.CaisseManagerService;
import com.webcaisse.ws.model.FamilleOut;
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

	@RequestMapping("/ajouter")
	public String ajouterProduits(Model model, @RequestParam(value = "idFamilly") Long idFamilly) {
		model.addAttribute("idFamilly", idFamilly);

		List<ProduitOut> produits = caisseManagerService.getProductsByFamilly(idFamilly);
		model.addAttribute("produits", produits);
		return "ajouterProduits";
	}

}
