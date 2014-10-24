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

	private Double prixttc =0D;

	public Double getPrixttc() {
		return prixttc;
	}

	public void setPrixttc(Double prixttc) {
		this.prixttc = prixttc;
	}


	public List<LignePanier> getLignesPanier() {
		return lignesPanier;
	}

	
	public void setLignesPanier(List<LignePanier> lignesPanier) {
		this.lignesPanier = lignesPanier;
	}

	public void addProduct(LignePanier lignePanier) {
		if (lignePanier.getIdProduit() != null) {
			
			// prix avant 
			Double prixAvant = this.getPrixttc();
			
			// on ajoute le LP
			this.getLignesPanier().add(lignePanier);
			
			// prix après 
			Double prixApres = prixAvant +  lignePanier.getPrix();
			
			// maj prix
			this.setPrixttc(prixApres);
			
		}
	}

	
	/**
	 * l'index commence par 0
	 * @param index
	 */
	public void supprimerDePanier(Integer index) {

		//lignesPanier.remove(index);
		if (index<this.getLignesPanier().size()){
			
			// recuperation de ligne panier par son index
			LignePanier  lignePanier  = this.getLignesPanier().get(index);

			// calcul de prix de lignePanier
			Double prixLignePanier  = lignePanier.getPrix();
			
			// suppression de ligne panier
			this.getLignesPanier().remove(lignePanier);
	
			// maj prix ligne panier
			Double prixPanierActuel  =this.getPrixttc()-prixLignePanier;
			if (prixPanierActuel<0){
				prixPanierActuel = 0D;
			}
			this.setPrixttc(prixPanierActuel);
		}
		
	}
	
	public void empty (){
		setPrixttc(0D);
		setLignesPanier(new ArrayList<LignePanier>());
	}
	
}
