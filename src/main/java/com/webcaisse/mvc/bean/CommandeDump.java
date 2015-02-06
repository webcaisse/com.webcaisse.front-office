package com.webcaisse.mvc.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class CommandeDump implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4926151049639016919L;

	private Client client;
	
	private ModePaiement modePaiement;
	
	private Panier panier;

	public Client getClient() {
		if (client==null){
			client = new Client();
		}
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ModePaiement getModePaiement() {
		if(modePaiement==null){
			modePaiement= new ModePaiement();
		}
		return modePaiement;
	}

	public void setModePaiement(ModePaiement modePaiement) {
		this.modePaiement = modePaiement;
	}

	public Panier getPanier() {
		if (panier==null){
			panier = new Panier();
		}
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	
}
