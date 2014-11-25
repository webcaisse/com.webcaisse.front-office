package com.webcaisse.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/produits")
public class ProduitPageController {

	@RequestMapping("afficher")
	public String afficherProduits (){
		return "produits";
	}
}
