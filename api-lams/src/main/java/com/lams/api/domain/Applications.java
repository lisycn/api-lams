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
import com.lams.api.domain.master.LoanTypeMstr;

@Entity
@Table(name = "applications")
public class Applications implements Serializable {

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
	private Double balance_tenure;
	
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="created_by")
	private Long createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modify_date")
	private Date modifyDate;
	
	@Column(name="modify_by")
	private Long modifyBy;
	
	@Column(name="is_active")
	private Boolean isActive;

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

	public Double getBalance_tenure() {
		return balance_tenure;
	}

	public void setBalance_tenure(Double balance_tenure) {
		this.balance_tenure = balance_tenure;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	

}
