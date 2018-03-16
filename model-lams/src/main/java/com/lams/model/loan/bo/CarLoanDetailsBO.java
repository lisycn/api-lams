package com.lams.model.loan.bo;

import java.io.Serializable;

import com.lams.model.bo.ApplicationsBO;

public class CarLoanDetailsBO extends ApplicationsBO implements Serializable {

	private static final long serialVersionUID = -4805059037944649444L;

	public CarLoanDetailsBO() {
		super();
	}
	
	private Double carCost;

	public Double getCarCost() {
		return carCost;
	}

	public void setCarCost(Double carCost) {
		this.carCost = carCost;
	}
	
	
	

}

