package com.webcaisse.mvc.controller.ajax.response;

public class JsonSoldePaiement {


	private Double solde;
	private String devise;
	private Double  montant ;
	
	
	
	public Double getSolde() {
		return solde;
	}
	public void setSolde(Double solde) {
		this.solde = solde;
	}
	public String getDevise() {
		return devise;
	}
	public void setDevise(String devise) {
		this.devise = devise;
	}
	
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}

	
	
}
