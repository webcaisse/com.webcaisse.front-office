package com.webcaisse.beans.commande.panier;

import java.io.Serializable;


/**
 * une ligne de panier dans l'objet Panier 
 * @author Belhadj
 *
 */
public class LignePanier implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6668274564228828605L;

	private Long idProduit;
	
	private float remise =1f;
	
	private Integer quantite;

	private Double prix;
	
	private Long idPrix ;
	
	public Long getIdPrix() {
		return idPrix;
	}
	public void setIdPrix(Long idPrix) {
		this.idPrix = idPrix;
	}
	public LignePanier() {
		this(null, 0, 0, 0D,null);
	}	


	public LignePanier(Long idProduit, float remise, Integer quantite,Double prix, Long idPrix) {
		super();
		this.idProduit = idProduit;
		this.remise = remise;
		this.quantite = quantite;
		this.prix = prix;
		this.idPrix = idPrix;
	}
	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public float getRemise() {
		return remise;
	}

	public void setRemise(float remise) {
		this.remise = remise;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	
	
}
