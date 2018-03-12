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

import com.lams.api.domain.master.AddressMstr;
import com.lams.api.domain.master.Auditor;
import com.lams.api.domain.master.BankMstr;

@Entity
@Table(name = "user")
public class User extends Auditor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String email;

	private String mobile;

	private String password;

	@Column(name = "temp_password")
	private String tempPassword;

	@Column(name = "invitation_count")
	private Integer invitationCount;

	@Column(name = "is_accept_term_condition")
	private Boolean isAcceptTermCondition;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "communication_add")
	private AddressMstr communicationAdd;

	@Column(name = "permanent_add")
	private AddressMstr permanentAdd;

	@Column(name = "birth_date")
	private Date birthDate;

	private Long gender;

	@Column(name = "is_otp_verified")
	private Boolean isOtpVerified;

	@Column(name = "is_email_verified")
	private Boolean isEmailVerified;

	@Column(name = "user_type")
	private Long userType;

	@Column(name = "salutation")
	private Long salutation;

	@ManyToOne
	@JoinColumn(name = "bank")
	private BankMstr bank;

	@Column(name = "pan_card")
	private String panCard;

	@Column(name = "aadhar_card_no")
	private String aadharCardNo;

	@Column(name = "edu_qualification")
	private String eduQualification;

	@Column(name = "contact_number")
	private String contactNumber;

	@Column(name = "is_same_us_address")
	private Boolean isSameUsAddress;

	public User() {
		super();
	}

	public User(Long id) {
		super();
		this.id = id;
	}

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

	public AddressMstr getCommunicationAdd() {
		return communicationAdd;
	}

	public void setCommunicationAdd(AddressMstr communicationAdd) {
		this.communicationAdd = communicationAdd;
	}

	public AddressMstr getPermanentAdd() {
		return permanentAdd;
	}

	public void setPermanentAdd(AddressMstr permanentAdd) {
		this.permanentAdd = permanentAdd;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getAadharCardNo() {
		return aadharCardNo;
	}

	public void setAadharCardNo(String aadharCardNo) {
		this.aadharCardNo = aadharCardNo;
	}

	public String getEduQualification() {
		return eduQualification;
	}

	public void setEduQualification(String eduQualification) {
		this.eduQualification = eduQualification;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Boolean getIsSameUsAddress() {
		return isSameUsAddress;
	}

	public void setIsSameUsAddress(Boolean isSameUsAddress) {
		this.isSameUsAddress = isSameUsAddress;
	}

	public Integer getInvitationCount() {
		return invitationCount == null ? 0 : invitationCount;
	}

	public void setInvitationCount(Integer invitationCount) {
		this.invitationCount = invitationCount;
	}

	public String getTempPassword() {
		return tempPassword;
	}

	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", password="
				+ password + ", tempPassword=" + tempPassword + ", invitationCount=" + invitationCount
				+ ", isAcceptTermCondition=" + isAcceptTermCondition + ", firstName=" + firstName + ", lastName="
				+ lastName + ", middleName=" + middleName + ", communicationAdd=" + communicationAdd + ", permanentAdd="
				+ permanentAdd + ", birthDate=" + birthDate + ", gender=" + gender + ", isOtpVerified=" + isOtpVerified
				+ ", isEmailVerified=" + isEmailVerified + ", userType=" + userType + ", salutation=" + salutation
				+ ", bank=" + bank + ", panCard=" + panCard + ", aadharCardNo=" + aadharCardNo + ", eduQualification="
				+ eduQualification + ", contactNumber=" + contactNumber + ", isSameUsAddress=" + isSameUsAddress + "]";
	}

}
