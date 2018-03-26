package com.lams.api.domain.payment;

import javax.persistence.*;

import com.lams.api.domain.master.Auditor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "payment_details")
public class PaymentDetails extends Auditor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "application_id")
	private Long applicationId;

	@Column(name = "deposit_date")
	private Date depositDate;

	@Column(name = "gst_number")
	private String gstNumber;

	@Column(name = "ref_number")
	private String refNumber;

	@Column(name = "deposited_branch")
	private String depositedBranch;

	@Column(name = "branch_city")
	private Long branchCity;

	@Column(name = "branch_state")
	private Long branchState;

	@Column(name = "branch_country")
	private Long branchCountry;

	@Column(name = "is_payment_validated")
	private Boolean isPaymentValidated;

	@Column(name = "is_payment_submitted")
	private Boolean isPaymentSubmitted;

	@Column(name = "cheque_date")
	private Date chequeDate;

	@Column(name = "cheque_no")
	private String chequeNo;

	@Column(name = "is_cash")
	private Boolean isCash;

	@Column(name = "is_cheque")
	private Boolean isCheque;

	@Column(name = "payment_submission_date")
	private Date paymentSubmissionDate;

	@ManyToOne
	@JoinColumn(name = "payment_to")
	private PaymentAccountMaster paymentToId;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "validation_date")
	private Date validationDate;

	public Date getValidationDate() {
		return validationDate;
	}

	public void setValidationDate(Date validationDate) {
		this.validationDate = validationDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public String getDepositedBranch() {
		return depositedBranch;
	}

	public void setDepositedBranch(String depositedBranch) {
		this.depositedBranch = depositedBranch;
	}

	public Long getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(Long branchCity) {
		this.branchCity = branchCity;
	}

	public Long getBranchState() {
		return branchState;
	}

	public void setBranchState(Long branchState) {
		this.branchState = branchState;
	}

	public Long getBranchCountry() {
		return branchCountry;
	}

	public void setBranchCountry(Long branchCountry) {
		this.branchCountry = branchCountry;
	}

	public Boolean getIsPaymentValidated() {
		return isPaymentValidated;
	}

	public void setIsPaymentValidated(Boolean isPaymentValidated) {
		this.isPaymentValidated = isPaymentValidated;
	}

	public Boolean getIsPaymentSubmitted() {
		return isPaymentSubmitted;
	}

	public void setIsPaymentSubmitted(Boolean isPaymentSubmitted) {
		this.isPaymentSubmitted = isPaymentSubmitted;
	}

	public Date getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public Date getPaymentSubmissionDate() {
		return paymentSubmissionDate;
	}

	public void setPaymentSubmissionDate(Date paymentSubmissionDate) {
		this.paymentSubmissionDate = paymentSubmissionDate;
	}

	public PaymentAccountMaster getPaymentToId() {
		return paymentToId;
	}

	public void setPaymentToId(PaymentAccountMaster paymentToId) {
		this.paymentToId = paymentToId;
	}

	public Boolean getIsCash() {
		return isCash;
	}

	public void setIsCash(Boolean isCash) {
		this.isCash = isCash;
	}

	public Boolean getIsCheque() {
		return isCheque;
	}

	public void setIsCheque(Boolean isCheque) {
		this.isCheque = isCheque;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	@Override
	public String toString() {
		return "PaymentDetails [id=" + id + ", applicationId=" + applicationId + ", depositDate=" + depositDate
				+ ", gstNumber=" + gstNumber + ", refNumber=" + refNumber + ", depositedBranch=" + depositedBranch
				+ ", branchCity=" + branchCity + ", branchState=" + branchState + ", branchCountry=" + branchCountry
				+ ", isPaymentValidated=" + isPaymentValidated + ", isPaymentSubmitted=" + isPaymentSubmitted
				+ ", chequeDate=" + chequeDate + ", chequeNo=" + chequeNo + ", isCash=" + isCash + ", isCheque="
				+ isCheque + ", paymentSubmissionDate=" + paymentSubmissionDate + ", paymentToId=" + paymentToId
				+ ", remarks=" + remarks + ", validationDate=" + validationDate + "]";
	}

}
