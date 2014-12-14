package com.webcaisse.mvc;

import java.util.Date;

public class ObjectCSV {

	private String libelle ;
	private String prenom ;
	private String email ;
	private String telephone ; 
	private Date dateCommande ;

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public ObjectCSV(String libelle) {
		super();
		this.libelle = libelle;
	}

	public ObjectCSV() {
		super();
		
	}
	
}
