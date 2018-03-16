package com.lams.api.domain.loan;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.lams.api.domain.Applications;

@Entity
@Table(name = "car_loan_details")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class CarLoanDetails extends Applications implements Serializable {

	private static final long serialVersionUID = -4805059037944649444L;

	public CarLoanDetails() {
		super();
	}
	
	@Column(name="car_cost")
	private Double carCost;

	public Double getCarCost() {
		return carCost;
	}

	public void setCarCost(Double carCost) {
		this.carCost = carCost;
	}
	
	
	

}

