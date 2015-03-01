package com.webcaisse.mvc.controller.ajax.response;

import java.util.List;

import com.webcaisse.ws.model.LivreurOut;

public class JsonLivreurResponse {

	private List<LivreurOut> livreursOut;

	public List<LivreurOut> getLivreursOut() {
		return livreursOut;
	}

	public void setLivreursOut(List<LivreurOut> livreursOut) {
		this.livreursOut = livreursOut;
	}

	public JsonLivreurResponse(List<LivreurOut> livreursOut) {
		super();
		this.livreursOut = livreursOut;
	}

	public JsonLivreurResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
