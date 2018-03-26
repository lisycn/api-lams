package com.lams.model.bo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentBO extends AuditorBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6256889837661145480L;
	private Long id;
	private Long applicationId;
	private Long userId;
	private String txnId;
	private Double amount;
	private String productInfo;
	private String firstName;
	private String email;
	private String phone;
	private String status;
	private Date updatedOn;
	private Long clientId;

	public PaymentBO() {

	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PaymentBO [id=" + id + ", applicationId=" + applicationId + ", userId=" + userId + ", txnId=" + txnId
				+ ", amount=" + amount + ", productInfo=" + productInfo + ", firstName=" + firstName + ", email="
				+ email + ", phone=" + phone + ", status=" + status + ", updatedOn=" + updatedOn + ", clientId="
				+ clientId + "]";
	}
}
