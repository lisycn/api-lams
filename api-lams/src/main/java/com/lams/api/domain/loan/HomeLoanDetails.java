package com.lams.api.domain.loan;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.lams.api.domain.Applications;

@Entity
@Table(name = "home_loan_details")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class HomeLoanDetails extends Applications implements Serializable {

	private static final long serialVersionUID = -4805059037944649444L;

	public HomeLoanDetails() {
		super();
	}
	
	@Column(name="property_cost")
	private Double propertyCost;
	
	@Column(name="property_address")
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
