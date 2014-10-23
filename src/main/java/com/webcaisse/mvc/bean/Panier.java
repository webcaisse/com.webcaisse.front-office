package com.webcaisse.mvc.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.stereotype.Component;

@Component
public class Panier {

	/**
	 * List de lignes
	 */
	private List<LignePanier> lignesPanier = new ArrayList<LignePanier>();
	
	public Double getPrixttc() {
		return prixttc;
	}

	public void setPrixttc(Double prixttc) {
		this.prixttc = prixttc;
	}

	private Double prixttc ;

	public List<LignePanier> getLignesPanier() {
		return lignesPanier;
	}

	public void addProduct(LignePanier lignePanier) {
		if (lignePanier.getIdProduit() != null) {
			this.getLignesPanier().add(lignePanier);
		}
	}

	public void supprimerDePanier(Integer index) {

		//lignesPanier.remove(index);
		
		/*for (int i=0;i<=lignesPanier.size();i++){
			if (i==index-1){
				lignesPanier.remove(i);
				break;
			}
		}*/
		
		lignesPanier.remove(index-1);
	}

	public Double calculTTCPanier(){
	
		Double prixttc=0.0 ;
		
		if(lignesPanier !=null){
			
		 for(int i = 0; i < lignesPanier.size(); i++)
		    {
		     prixttc+=lignesPanier.get(i).getPrix() ;
		    }               
		  
		}
		
		return prixttc ;
	}
	
}
