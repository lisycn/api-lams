package com.lams.api.domain.payment;

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

import com.lams.api.domain.master.Auditor;

@Entity
@Table(name = "payment_history")
public class PaymentHistory extends Auditor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "application_id")
	private Long applicationId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "txn_id")
	private String txnId;
	private Double amount;

	@Column(name = "product_info")
	private String productInfo;

	@Column(name = "first_name")
	private String firstName;
	private String email;
	private String phone;
	private String status;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "is_payment_validated")
	private Boolean isPaymentValidated;

	@Column(name = "validation_date")
	private Date validationDate;

	@Column(name = "deposit_date")
	private Date depositDate;

	@ManyToOne
	@JoinColumn(name = "service_provider")
	private ServiceProvider providerId;

	public PaymentHistory() {
		super();
	}

	public PaymentHistory(Long id) {
		super();
		this.id = id;
	}

	public Boolean getPaymentValidated() {
		return isPaymentValidated;
	}

	public void setPaymentValidated(Boolean paymentValidated) {
		isPaymentValidated = paymentValidated;
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public ServiceProvider getProviderId() {
		return providerId;
	}

	public void setProviderId(ServiceProvider providerId) {
		this.providerId = providerId;
	}

	public Boolean getIsPaymentValidated() {
		return isPaymentValidated;
	}

	public void setIsPaymentValidated(Boolean isPaymentValidated) {
		this.isPaymentValidated = isPaymentValidated;
	}
}
