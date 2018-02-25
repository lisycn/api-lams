package com.lams.model.bo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBO extends AuditorBO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long salutation;

	private String name;

	private String email;

	private String mobile;

	private Boolean isAcceptTermCondition;

	private String firstName;

	private String lastName;

	private String middleName;

	private AddressBO address;

	private Date birthDate;

	private Long gender;

	private Boolean isActive;

	private Boolean isOtpVerified;

	private Boolean isEmailVerified;

	private Long userType;

	private BankBO bank;

	private String password;

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

	public AddressBO getAddress() {
		return address;
	}

	public void setAddress(AddressBO address) {
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

	public BankBO getBank() {
		return bank;
	}

	public void setBank(BankBO bank) {
		this.bank = bank;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getSalutation() {
		return salutation;
	}

	public void setSalutation(Long salutation) {
		this.salutation = salutation;
	}

	@Override
	public String toString() {
		return "UserBO [id=" + id + ", salutation=" + salutation + ", name=" + name + ", email=" + email + ", mobile="
				+ mobile + ", isAcceptTermCondition=" + isAcceptTermCondition + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", middleName=" + middleName + ", address=" + address + ", birthDate="
				+ birthDate + ", gender=" + gender + ", isActive=" + isActive + ", isOtpVerified=" + isOtpVerified
				+ ", isEmailVerified=" + isEmailVerified + ", userType=" + userType + ", bank=" + bank + ", password="
				+ password + "]";
	}
}