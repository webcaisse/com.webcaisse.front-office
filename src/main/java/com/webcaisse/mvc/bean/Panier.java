package com.webcaisse.mvc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Panier implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -568027640257729662L;

	/**
	 * List de lignes
	 */
	private List<LignePanier> lignesPanier = new ArrayList<LignePanier>();

	private Double prixTtc = 0D;

	private Double prixHt = 0D;
	
	private String message ;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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

	public void addLine(LignePanier lignePanier) {
		if (lignePanier.getIdProduit() != null && lignePanier.getIdPrix()!=null) {
			// je verifie que ce produit est ajouté deja dans le panier
			boolean produitDejaPresent = false;
			Integer indexLigne =0;
			for (LignePanier lp : lignesPanier) {
				if (lp!=null && lp.getIdProduit() !=null && lp.getIdPrix()!=null && lp.getIdProduit().equals(lignePanier.getIdProduit())
						&& lp.getIdPrix().equals(lignePanier.getIdPrix())){
					produitDejaPresent = true;
					indexLigne = lignesPanier.indexOf(lp);
					break;
				}
			}
			if (produitDejaPresent){
				LignePanier lp  = lignesPanier.get(indexLigne);
				Integer quantite  = lp.getQuantite();
				lp.setQuantite(quantite + lignePanier.getQuantite());
			}else{
				// on ajoute le LP
				lignesPanier.add(lignePanier);				
			}
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

	/**
	 * 
	 */
	public void empty() {
		updatePrice(0D, 0D);
		setLignesPanier(new ArrayList<LignePanier>());
	}
	
	/**
	 * 
	 * @param prixHt
	 * @param prixTtc
	 */
	public void updatePrice (Double prixHt, Double prixTtc){
		setPrixHt(prixHt);
		setPrixTtc(prixTtc);
	}
	
}
