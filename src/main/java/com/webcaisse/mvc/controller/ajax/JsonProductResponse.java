package com.webcaisse.mvc.controller.ajax;

import com.webcaisse.ws.model.ProduitOut;

public class JsonProductResponse {

	private Integer nbResult ;
	
	private String devise ;
	
	private ProduitOut produitOut;

	public Integer getNbResult() {
		return nbResult;
	}

	public void setNbResult(Integer nbResult) {
		this.nbResult = nbResult;
	}

	public ProduitOut getProduitOut() {
		return produitOut;
	}

	public void setProduitOut(ProduitOut produitOut) {
		this.produitOut = produitOut;
	}

	public String getDevise() {
		return devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
	}
	
}
