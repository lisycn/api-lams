package com.lams.model.loan.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lams.model.bo.ApplicationsBO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HomeLoanDetailsBO extends ApplicationsBO implements Serializable {

	private static final long serialVersionUID = -4805059037944649444L;

	public HomeLoanDetailsBO() {
		super();
	}
	
	private Double propertyCost;
	
	private String propertyAddress;

	public Double getPropertyCost() {
		return propertyCost;
	}

	public void setPropertyCost(Double propertyCost) {
		this.propertyCost = propertyCost;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}
	
}
