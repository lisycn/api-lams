package com.lams.api.domain.payment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lams.api.domain.master.Auditor;
import com.lams.api.domain.master.BankMstr;

@Entity
@Table(name = "payment_account_master")
public class PaymentAccountMaster extends Auditor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "account_holder")
	private String accountHolder;

	private BankMstr bank;

	private String branch;

	@Column(name = "account_number")
	private Long accountNumber;

	@Column(name = "amount_payable")
	private Double amountPayable;

	@Column(name = "acc_type")
	private String accType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public BankMstr getBank() {
		return bank;
	}

	public void setBank(BankMstr bank) {
		this.bank = bank;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getAmountPayable() {
		return amountPayable;
	}

	public void setAmountPayable(Double amountPayable) {
		this.amountPayable = amountPayable;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	@Override
	public String toString() {
		return "PaymentAccountMaster [id=" + id + ", accountHolder=" + accountHolder + ", bank=" + bank + ", branch="
				+ branch + ", accountNumber=" + accountNumber + ", amountPayable=" + amountPayable + ", accType="
				+ accType + "]";
	}
}
