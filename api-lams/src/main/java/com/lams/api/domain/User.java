package com.lams.api.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lams.api.domain.master.AddressMstr;
import com.lams.api.domain.master.Auditor;
import com.lams.api.domain.master.BankMstr;


@Entity
@Table(name="user")
public class User extends Auditor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 
	 private String name;
	 
	 private String email;
	 
	 private String mobile;
	 
	 @Column(name = "is_accept_term_condition")
	 private Boolean isAcceptTermCondition;
	 
	 @Column(name = "first_name")
	 private String firstName;
	 
	 @Column(name = "last_name")
	 private String lastName;
	 
	 @Column(name = "middle_name")
	 private String middleName;
	 
	 @ManyToOne
	 @Column(name = "address")
	 private AddressMstr address;
	 
	 @Column(name = "birth_date")
	 private Date birthDate;
	 
	 private Long gender;
	 
	 @Column(name = "is_active")
	 private Boolean isActive;
	 
	 @Column(name = "is_otp_verified")
	 private Boolean isOtpVerified;
	 
	 @Column(name = "is_email_verified")
	 private Boolean isEmailVerified;
	 
	 @Column(name = "user_type")
	 private Long userType;
	 
	 @Column(name = "bank")
	 private BankMstr bank;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getIsAcceptTermCondition() {
		return isAcceptTermCondition;
	}

	public void setIsAcceptTermCondition(Boolean isAcceptTermCondition) {
		this.isAcceptTermCondition = isAcceptTermCondition;
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

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public AddressMstr getAddress() {
		return address;
	}

	public void setAddress(AddressMstr address) {
		this.address = address;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Long getGender() {
		return gender;
	}

	public void setGender(Long gender) {
		this.gender = gender;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsOtpVerified() {
		return isOtpVerified;
	}

	public void setIsOtpVerified(Boolean isOtpVerified) {
		this.isOtpVerified = isOtpVerified;
	}

	public Boolean getIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public Long getUserType() {
		return userType;
	}

	public void setUserType(Long userType) {
		this.userType = userType;
	}

	public BankMstr getBank() {
		return bank;
	}

	public void setBank(BankMstr bank) {
		this.bank = bank;
	}
}
