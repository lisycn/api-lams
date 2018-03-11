package com.lams.api.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lams.api.domain.master.ApplicationTypeMstr;
import com.lams.api.domain.master.Auditor;
import com.lams.api.domain.master.LoanTypeMstr;

@Entity
@Table(name = "applications")
public class Applications extends Auditor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "application_type_id")
	private ApplicationTypeMstr applicationTypeId;
	
	@ManyToOne
	@JoinColumn(name = "loan_type_id")
	private LoanTypeMstr loanTypeId;
	
	@Column(name = "user_id")
	private Long userId;

	@Column(name="outstanding_amount")
	private Double outstandingAmount;
	
	private Double emi;
	
	@Column(name="balance_tenure")
	private Double balanceTenure;
	
	@Column(name="close_before_disbsmnt")
	private Integer closeBeforeDisbsmnt;
	
	private Integer tenure;

	@Column(name="property_cost")
	private Double propertyCost;
	
	@Column(name="property_address")
	private String propertyAddress;
	
	@Column(name="bank_name")
	private String bankName;
	
	@Column(name="bank_acc_number")
	private String bankAccNumber;
	
	@Column(name="loan_amount")
	private Double loanAmount;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ApplicationTypeMstr getApplicationTypeId() {
		return applicationTypeId;
	}

	public void setApplicationTypeId(ApplicationTypeMstr applicationTypeId) {
		this.applicationTypeId = applicationTypeId;
	}

	public LoanTypeMstr getLoanTypeId() {
		return loanTypeId;
	}

	public void setLoanTypeId(LoanTypeMstr loanTypeId) {
		this.loanTypeId = loanTypeId;
	}

	public Double getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(Double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public Double getEmi() {
		return emi;
	}

	public void setEmi(Double emi) {
		this.emi = emi;
	}

	public Double getBalanceTenure() {
		return balanceTenure;
	}

	public void setBalanceTenure(Double balanceTenure) {
		this.balanceTenure = balanceTenure;
	}

	public Integer getCloseBeforeDisbsmnt() {
		return closeBeforeDisbsmnt;
	}

	public void setCloseBeforeDisbsmnt(Integer closeBeforeDisbsmnt) {
		this.closeBeforeDisbsmnt = closeBeforeDisbsmnt;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccNumber() {
		return bankAccNumber;
	}

	public void setBankAccNumber(String bankAccNumber) {
		this.bankAccNumber = bankAccNumber;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	

}
