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
	
	private Boolean closeBeforeDisbsmnt;
	
	private Integer tenure;

	private String bankName;
	
	private String bankAccNumber;
	
	private Double loanAmount;
	
	private String leadReferenceNo;
	
	private Long employmentType;
	
	private Boolean isUploadComplete;
	
	private String status;
	
	private String firstName;

	private String lastName;
	
	private String name;

	private String email;

	private String mobile;
	
	private Boolean isLoanDetailsLock;
	
	private Boolean isLoanDetailsComplete;

	
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

	public Boolean getCloseBeforeDisbsmnt() {
		return closeBeforeDisbsmnt;
	}

	public void setCloseBeforeDisbsmnt(Boolean closeBeforeDisbsmnt) {
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
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIsUploadComplete() {
		return isUploadComplete;
	}

	public void setIsUploadComplete(Boolean isUploadComplete) {
		this.isUploadComplete = isUploadComplete;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getIsLoanDetailsLock() {
		return isLoanDetailsLock;
	}

	public void setIsLoanDetailsLock(Boolean isLoanDetailsLock) {
		this.isLoanDetailsLock = isLoanDetailsLock;
	}

	public Boolean getIsLoanDetailsComplete() {
		return isLoanDetailsComplete;
	}

	public void setIsLoanDetailsComplete(Boolean isLoanDetailsComplete) {
		this.isLoanDetailsComplete = isLoanDetailsComplete;
	}
	
	
	
}
