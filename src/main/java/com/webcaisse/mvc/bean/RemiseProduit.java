package com.webcaisse.mvc.bean;

public class RemiseProduit {
	
	private Long productId;
	
	private Long priceId;
	
	private Float remiseValue ;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}

	public Float getRemiseValue() {
		return remiseValue;
	}

	public void setRemiseValue(Float remiseValue) {
		this.remiseValue = remiseValue;
	}
	
	
}
