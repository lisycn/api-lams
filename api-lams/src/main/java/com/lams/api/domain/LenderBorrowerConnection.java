package com.lams.api.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lams.api.domain.master.Auditor;

@Entity
@Table(name = "lender_borrower_connection")
public class LenderBorrowerConnection extends Auditor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1958749568007715061L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "loan_possible_amount")
	private Double loanPossibleAmount;

	private Integer tenure;

	private Double roi;

	@Column(name = "processing_fees")
	private Double processingFees;

	@Column(name = "term_and_condition")
	private String termAndCondition;

	private String comments;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "application_id")
	private Applications application;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lender_application_mapping_id")
	private LenderApplicationMapping lenderApplicationMapping;

	private String status;
	
	public LenderBorrowerConnection() {
		super();
	}

	public LenderBorrowerConnection(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Applications getApplication() {
		return application;
	}

	public void setApplication(Applications application) {
		this.application = application;
	}

	public Double getLoanPossibleAmount() {
		return loanPossibleAmount;
	}

	public void setLoanPossibleAmount(Double loanPossibleAmount) {
		this.loanPossibleAmount = loanPossibleAmount;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}

	public Double getProcessingFees() {
		return processingFees;
	}

	public void setProcessingFees(Double processingFees) {
		this.processingFees = processingFees;
	}

	public String getTermAndCondition() {
		return termAndCondition;
	}

	public void setTermAndCondition(String termAndCondition) {
		this.termAndCondition = termAndCondition;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public LenderApplicationMapping getLenderApplicationMapping() {
		return lenderApplicationMapping;
	}

	public void setLenderApplicationMapping(LenderApplicationMapping lenderApplicationMapping) {
		this.lenderApplicationMapping = lenderApplicationMapping;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "LenderBorrowerConnection [id=" + id + ", loanPossibleAmount=" + loanPossibleAmount + ", tenure="
				+ tenure + ", roi=" + roi + ", processingFees=" + processingFees + ", termAndCondition="
				+ termAndCondition + ", comments=" + comments + ", application=" + application
				+ ", lenderApplicationMapping=" + lenderApplicationMapping + ", status=" + status + "]";
	}

	

}
