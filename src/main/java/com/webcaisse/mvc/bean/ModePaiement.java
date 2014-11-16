package com.webcaisse.mvc.bean;

public class ModePaiement {
	
	private Double espace ;
	private Double cb ;
	private Double cheque ;
	private Double fidelite ;
	private Double ticketRestau ;
	
	

	public Double getEspace() {
		return espace;
	}
	public ModePaiement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ModePaiement(Double espace, Double cb, Double cheque,
			Double fidelite, Double ticketRestau) {
		super();
		this.espace = espace;
		this.cb = cb;
		this.cheque = cheque;
		this.fidelite = fidelite;
		this.ticketRestau = ticketRestau;
	}
	public void setEspace(Double espace) {
		this.espace = espace;
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
