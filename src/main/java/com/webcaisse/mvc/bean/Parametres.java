package com.webcaisse.mvc.bean;
import java.util.ArrayList;
import java.util.List;

import com.webcaisse.ws.model.ParametreIn;
public class Parametres {
	
	private List<ParametreIn> parametres = new ArrayList<ParametreIn> () ;

	public List<ParametreIn> getParametres() {
		return parametres;
	}

	public void setParametres(List<ParametreIn> parametres) {
		this.parametres = parametres;
	}
	
	
	
}
