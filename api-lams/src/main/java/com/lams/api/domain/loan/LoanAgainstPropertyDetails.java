package com.lams.api.domain.loan;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.lams.api.domain.Applications;

@Entity
@Table(name = "loan_against_property_details")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class LoanAgainstPropertyDetails extends Applications implements Serializable {

	private static final long serialVersionUID = -4805059037944649444L;

	public LoanAgainstPropertyDetails() {
		super();
	}
	
	@Column(name="property_market_value")
	private Double propertyMarketValue;
	
	@Column(name="property_address")
	private String propertyAddress;
	
	@Column(name="reason_for_lap")
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
