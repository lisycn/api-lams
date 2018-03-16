package com.lams.model.loan.bo;

import java.io.Serializable;

import com.lams.model.bo.ApplicationsBO;

public class LoanAgainstPropertyDetailsBO extends ApplicationsBO implements Serializable {

	private static final long serialVersionUID = -4805059037944649444L;

	public LoanAgainstPropertyDetailsBO() {
		super();
	}
	
	private Double propertyMarketValue;
	
	private String propertyAddress;
	
	private String reasonForLap;

	public Double getPropertyMarketValue() {
		return propertyMarketValue;
	}

	public void setPropertyMarketValue(Double propertyMarketValue) {
		this.propertyMarketValue = propertyMarketValue;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public String getReasonForLap() {
		return reasonForLap;
	}

	public void setReasonForLap(String reasonForLap) {
		this.reasonForLap = reasonForLap;
	}
	

}
