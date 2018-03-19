package com.lams.model.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationsBO extends AuditorBO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long applicationTypeId;
	
	private String applicationTypeName;
	
	private String applicationTypeCode;
	
	private Long loanTypeId;
	
	private String loanTypeName;

	private Long userId;
	
	private Double outstandingAmount;
	
	private Double emi;
	
	private Double balanceTenure;
	
	private Integer closeBeforeDisbsmnt;
	
	private Integer tenure;

	private String bankName;
	
	private String bankAccNumber;
	
	private Double loanAmount;
	
	private String leadReferenceNo;
	
	private Long employmentType;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationTypeId() {
		return applicationTypeId;
	}

	public void setApplicationTypeId(Long applicationTypeId) {
		this.applicationTypeId = applicationTypeId;
	}

	public String getApplicationTypeName() {
		return applicationTypeName;
	}

	public void setApplicationTypeName(String applicationTypeName) {
		this.applicationTypeName = applicationTypeName;
	}

	public Long getLoanTypeId() {
		return loanTypeId;
	}

	public void setLoanTypeId(Long loanTypeId) {
		this.loanTypeId = loanTypeId;
	}

	public String getLoanTypeName() {
		return loanTypeName;
	}

	public void setLoanTypeName(String loanTypeName) {
		this.loanTypeName = loanTypeName;
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

	public String getLeadReferenceNo() {
		return leadReferenceNo;
	}

	public void setLeadReferenceNo(String leadReferenceNo) {
		this.leadReferenceNo = leadReferenceNo;
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

	public String getApplicationTypeCode() {
		return applicationTypeCode;
	}

	public void setApplicationTypeCode(String applicationTypeCode) {
		this.applicationTypeCode = applicationTypeCode;
	}

	public Long getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(Long employmentType) {
		this.employmentType = employmentType;
	}
	
	
	
}
