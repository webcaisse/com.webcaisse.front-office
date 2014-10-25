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

	private Double prixTtc = 0D;

	private Double prixHt = 0D;

	public Double getPrixTtc() {
		return prixTtc;
	}

	private void setPrixTtc(Double prixTtc) {
		this.prixTtc = prixTtc;
	}

	public Double getPrixHt() {
		return prixHt;
	}

	private void setPrixHt(Double prixHt) {
		this.prixHt = prixHt;
	}

	public List<LignePanier> getLignesPanier() {
		return lignesPanier;
	}

	public void setLignesPanier(List<LignePanier> lignesPanier) {
		this.lignesPanier = lignesPanier;
	}

	public void addProduct(LignePanier lignePanier) {
		if (lignePanier.getIdProduit() != null) {
			// on ajoute le LP
			this.getLignesPanier().add(lignePanier);
		}
	}

	/**
	 * l'index commence par 0
	 * 
	 * @param index
	 */
	public void supprimerDePanier(Integer index) {

		// lignesPanier.remove(index);
		if (index < this.getLignesPanier().size()) {

			// recuperation de ligne panier par son index
			LignePanier lignePanier = this.getLignesPanier().get(index);
			// suppression de ligne panier
			this.getLignesPanier().remove(lignePanier);
		}

	}

	public void empty() {
		updatePrice(0D, 0D);
		setLignesPanier(new ArrayList<LignePanier>());
	}
	
	public void updatePrice (Double prixHt, Double prixTtc){
		setPrixHt(prixHt);
		setPrixTtc(prixTtc);
	}

}
