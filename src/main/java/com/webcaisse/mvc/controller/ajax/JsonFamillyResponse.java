package com.webcaisse.mvc.controller.ajax;

import java.util.List;

import com.webcaisse.ws.model.FamilleOut;

public class JsonFamillyResponse {

	
	private List<FamilleOut> famillesOut;
	
	public JsonFamillyResponse(List<FamilleOut> famillesOut) {
		super();
		this.famillesOut = famillesOut;
	}

	public JsonFamillyResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<FamilleOut> getFamillesOut() {
		return famillesOut;
	}

	public void setFamillesOut(List<FamilleOut> famillesOut) {
		this.famillesOut = famillesOut;
	}

	
	
}
