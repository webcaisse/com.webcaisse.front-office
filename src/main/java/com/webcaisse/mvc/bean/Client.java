package com.webcaisse.mvc.bean;

import org.springframework.stereotype.Component;


@Component
public class Client {
	
	
    private Long idClient ;
	private String telephone ;
	private  String nom ;
	private String codePostale;
	private Integer etage ;
	private String immeuble;
	private String interphone ;
	private String numeroRue;
	private String nomRue ;
	private String prenom ;
	
	
	
	public Long getIdClient() {
		return idClient;
	}
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCodePostale() {
		return codePostale;
	}
	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}
	public Integer getEtage() {
		return etage;
	}
	public void setEtage(Integer etage) {
		this.etage = etage;
	}
	public String getImmeuble() {
		return immeuble;
	}
	public void setImmeuble(String immeuble) {
		this.immeuble = immeuble;
	}
	public String getInterphone() {
		return interphone;
	}
	public void setInterphone(String interphone) {
		this.interphone = interphone;
	}
	public String getNumeroRue() {
		return numeroRue;
	}
	public void setNumeroRue(String numeroRue) {
		this.numeroRue = numeroRue;
	}
	public String getNomRue() {
		return nomRue;
	}
	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Client(String telephone, String nom, String codePostale,
			Integer etage, String immeuble, String interphone,
			String numeroRue, String nomRue, String ville, String prenom) {
		super();
		this.telephone = telephone;
		this.nom = nom;
		this.codePostale = codePostale;
		this.etage = etage;
		this.immeuble = immeuble;
		this.interphone = interphone;
		this.numeroRue = numeroRue;
		this.nomRue = nomRue;
		this.prenom = prenom;
	}
	public Client() {
		super();
		
	}
	
	
	
	
	
	
}
