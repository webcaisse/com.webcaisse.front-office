package com.webcaisse.mvc.bean;

import org.springframework.stereotype.Component;

@Component
public class ModePaiement {
	
	private String Libelle ;
	private Double espece ;
	private Double cb ;
	private Double cheque ;
	private Double fidelite ;
	private Double ticketRestau ;
	

	
	public String getLibelle() {
		return Libelle;
	}
	public void setLibelle(String libelle) {
		Libelle = libelle;
	}
	public ModePaiement() {
		super();
	}
	public ModePaiement(Double espace, Double cb, Double cheque,Double fidelite, Double ticketRestau) {
		super();
		this.espece = espace;
		this.cb = cb;
		this.cheque = cheque;
		this.fidelite = fidelite;
		this.ticketRestau = ticketRestau;
	}
	
	public void empty(){
		this.espece =0D;
		this.cb = 0D;
		this.cheque = 0D;
		this.fidelite = 0D;
		this.ticketRestau = 0D;
	}

	public Double getEspece() {
		return espece;
	}

	public void setEspece(Double espace) {
		this.espece = espace;
	}
	public Double getCb() {
		return cb;
	}
	public void setCb(Double cb) {
		this.cb = cb;
	}
	public Double getCheque() {
		return cheque;
	}
	public void setCheque(Double cheque) {
		this.cheque = cheque;
	}
	public Double getFidelite() {
		return fidelite;
	}
	public void setFidelite(Double fidelite) {
		this.fidelite = fidelite;
	}
	public Double getTicketRestau() {
		return ticketRestau;
	}
	public void setTicketRestau(Double ticketRestau) {
		this.ticketRestau = ticketRestau;
	}

	
	
}
