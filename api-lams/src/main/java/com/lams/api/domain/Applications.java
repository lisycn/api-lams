package com.lams.api.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lams.api.domain.master.ApplicationTypeMstr;
import com.lams.api.domain.master.Auditor;
import com.lams.api.domain.master.LoanTypeMstr;

@Entity
@Table(name = "applications")
@Inheritance(strategy = InheritanceType.JOINED)
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
	private Boolean closeBeforeDisbsmnt;
	
	private Integer tenure;
	
	@Column(name="bank_name")
	private String bankName;
	
	@Column(name="bank_acc_number")
	private String bankAccNumber;
	
	@Column(name="loan_amount")
	private Double loanAmount;
	
	@Column(name="lead_reference_no")
	private String leadReferenceNo;
	
	@Column(name="is_upload_complete")
	private Boolean isUploadComplete;
	
	private String status;
	
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

	public String getLeadReferenceNo() {
		return leadReferenceNo;
	}

	public void setLeadReferenceNo(String leadReferenceNo) {
		this.leadReferenceNo = leadReferenceNo;
	}

	public String getStatus() {
		return status;
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
	
	

}
