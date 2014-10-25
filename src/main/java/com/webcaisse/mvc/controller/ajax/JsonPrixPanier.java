package com.webcaisse.mvc.controller.ajax;

public class JsonPrixPanier {

	private Double prixHt;
	private Double prixTtc;
	private String devise;
	
	public Double getPrixHt() {
		return prixHt;
	}
	public void setPrixHt(Double prixHt) {
		this.prixHt = prixHt;
	}
	public Double getPrixTtc() {
		return prixTtc;
	}
	public void setPrixTtc(Double prixTtc) {
		this.prixTtc = prixTtc;
	}
	public String getDevise() {
		return devise;
	}
	public void setDevise(String devise) {
		this.devise = devise;
	}
	
}
