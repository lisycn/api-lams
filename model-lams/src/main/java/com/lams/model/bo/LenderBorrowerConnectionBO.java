package com.lams.model.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LenderBorrowerConnectionBO extends AuditorBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1958749568007715061L;

	private Long id;

	private ApplicationsBO application;

	private Double loanPossibleAmount;

	private Integer tenure;

	private Double roi;

	private Double processingFees;

	private String termAndCondition;

	private String comments;

	private Long lenderApplicationId;

	public LenderBorrowerConnectionBO() {
		super();
	}

	public LenderBorrowerConnectionBO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ApplicationsBO getApplication() {
		return application;
	}

	public void setApplication(ApplicationsBO application) {
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

	public Long getLenderApplicationId() {
		return lenderApplicationId;
	}

	public void setLenderApplicationId(Long lenderApplicationId) {
		this.lenderApplicationId = lenderApplicationId;
	}

	@Override
	public String toString() {
		return "LenderBorrowerConnectionBO [id=" + id + ", application=" + application + ", loanPossibleAmount="
				+ loanPossibleAmount + ", tenure=" + tenure + ", roi=" + roi + ", processingFees=" + processingFees
				+ ", termAndCondition=" + termAndCondition + ", comments=" + comments + ", lenderApplicationId="
				+ lenderApplicationId + "]";
	}

}
