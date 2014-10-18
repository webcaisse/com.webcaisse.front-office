package com.webcaisse.mvc.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class Panier {

	/**
	 * List de lignes
	 */
	private List<LignePanier> lignesPanier = new ArrayList<LignePanier>();

	public List<LignePanier> getLignesPanier() {
		return lignesPanier;
	}

	public void addProduct(LignePanier lignePanier) {
		if (lignePanier.getIdProduit() != null) {
			this.getLignesPanier().add(lignePanier);
		}
	}

	
	
}
