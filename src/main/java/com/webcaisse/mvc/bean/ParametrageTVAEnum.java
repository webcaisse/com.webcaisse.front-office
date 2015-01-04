package com.webcaisse.mvc.bean;

public enum ParametrageTVAEnum {

	tvaSurPlace("REF_TVA_SP"), tvaEmporter("REF_TVA_EM"), tvaLivraison("REF_TVA_LV") ;

	private String valeur;

	ParametrageTVAEnum(String val) {
		this.valeur = val;
	}

	public String getValeur() {
		return valeur;
	}

}
