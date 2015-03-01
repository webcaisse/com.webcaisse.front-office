package com.webcaisse.beans.commande;

import java.io.Serializable;

public class NoteIn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String notes ;

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
