package com.webcaisse.mvc;

public enum EnumModePaiement {

	ESPECE(1),CB(2), CHEQUE(3), FIDELITE(4), TR(5);

	private Integer mode;
	
	EnumModePaiement(Integer mode){
		this.mode = mode;
	}

	public Integer getMode() {
		return mode;
	}
	
}