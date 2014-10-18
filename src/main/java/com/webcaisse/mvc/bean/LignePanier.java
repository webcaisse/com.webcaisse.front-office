package com.webcaisse.mvc.bean;


/**
 * une ligne de panier dans l'objet Panier 
 * @author Belhadj
 *
 */
public class LignePanier {

	
	private Long idProduit;
	
	private float remise ;
	
	private Integer quantite;

	private Double prix;
	
	public LignePanier() {
		this(null, 0, 0, 0D);
	}	
	public LignePanier(Long idProduit, float remise, Integer quantite, Double prix) {
		super();
		this.idProduit = idProduit;
		this.remise = remise;
		this.quantite = quantite;
		this.prix = prix;
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
