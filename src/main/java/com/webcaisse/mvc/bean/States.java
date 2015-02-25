package com.webcaisse.mvc.bean;

public enum States {

	SAVE_OK("1"), SAVE_KO("2");
	
	private String code ;
	
	States (String val){
		this.code=val;
	}

	public String getCode() {
		return code;
	}

}
