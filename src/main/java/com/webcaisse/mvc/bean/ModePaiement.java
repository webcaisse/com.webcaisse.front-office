package com.webcaisse.mvc.bean;

import java.io.Serializable;


public class ModePaiement implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9142437692781820613L;
	
	private Double espece ;
	private Double cb ;
	private Double cheque ;
	private Double fidelite ;
	private Double ticketRestau ;
	
	
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
		this.espece =null;
		this.cb = null;
		this.cheque = null;
		this.fidelite = null;
		this.ticketRestau = null;
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
