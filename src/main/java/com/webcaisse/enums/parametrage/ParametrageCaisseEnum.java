package com.webcaisse.enums.parametrage;

public enum ParametrageCaisseEnum {

	entete1("REF_ENTETE1"), entete2("REF_ENTETE2"), entete3("REF_ENTETE3"), entete4(
			"REF_ENTETE4"), pied1("REF_PIED1"), pied2("REF_PIED2"), pied3(
			"REF_PIED3"), pied4("REF_PIED4"), ;

	private String valeur;

	ParametrageCaisseEnum(String val) {
		this.valeur = val;
	}

	public String getValeur() {
		return valeur;
	}

}
