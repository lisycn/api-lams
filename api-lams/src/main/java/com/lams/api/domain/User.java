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
import com.lams.api.domain.master.BusinessTypeMstr;

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

	// Being used for Channel Partner and Borrower
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

	@Column(name = "employment_type")
	private Long employmentType;

	@Column(name = "employer_name")
	private String employerName;

	@Column(name = "employment_address")
	private String employmentAddress;

	@Column(name = "gross_monthly_income")
	private Double grossMonthlyIncome;

	@Column(name = "total_work_experience")
	private Integer totalWorkExperience;

	@Column(name = "entity_name")
	private String entityName;

	@Column(name = "contact_person_name")
	private String contactPersonName;

	@Column(name = "gst_number")
	private String gstNumber;

	@ManyToOne
	@JoinColumn(name = "business_type_id")
	private BusinessTypeMstr businessTypeMstr;

	@ManyToOne
	@JoinColumn(name = "channel_partner_id")
	private User channelPartnerId;

	@Column(name = "about_me")
	private String aboutMe;
	
	private String code;

	@Column(name = "entity_type")
	private Long entityType;

	@Column(name = "self_employed_type")
	private Long selfEmployedType;
	
	@Column(name = "is_profile_filled")
	private Boolean isProfileFilled;

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

	public Long getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(Long employmentType) {
		this.employmentType = employmentType;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getEmploymentAddress() {
		return employmentAddress;
	}

	public void setEmploymentAddress(String employmentAddress) {
		this.employmentAddress = employmentAddress;
	}

	public Double getGrossMonthlyIncome() {
		return grossMonthlyIncome;
	}

	public void setGrossMonthlyIncome(Double grossMonthlyIncome) {
		this.grossMonthlyIncome = grossMonthlyIncome;
	}

	public Integer getTotalWorkExperience() {
		return totalWorkExperience;
	}

	public void setTotalWorkExperience(Integer totalWorkExperience) {
		this.totalWorkExperience = totalWorkExperience;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Long getEntityType() {
		return entityType;
	}

	public void setEntityType(Long entityType) {
		this.entityType = entityType;
	}

	public Long getSelfEmployedType() {
		return selfEmployedType;
	}

	public void setSelfEmployedType(Long selfEmployedType) {
		this.selfEmployedType = selfEmployedType;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public BusinessTypeMstr getBusinessTypeMstr() {
		return businessTypeMstr;
	}

	public void setBusinessTypeMstr(BusinessTypeMstr businessTypeMstr) {
		this.businessTypeMstr = businessTypeMstr;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public User getChannelPartnerId() {
		return channelPartnerId;
	}

	public void setChannelPartnerId(User channelPartnerId) {
		this.channelPartnerId = channelPartnerId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getIsProfileFilled() {
		return isProfileFilled;
	}

	public void setIsProfileFilled(Boolean isProfileFilled) {
		this.isProfileFilled = isProfileFilled;
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
				+ eduQualification + ", contactNumber=" + contactNumber + ", isSameUsAddress=" + isSameUsAddress
				+ ", employmentType=" + employmentType + ", employerName=" + employerName + ", employmentAddress="
				+ employmentAddress + ", grossMonthlyIncome=" + grossMonthlyIncome + ", totalWorkExperience="
				+ totalWorkExperience + ", entityName=" + entityName + ", contactPersonName=" + contactPersonName
				+ ", gstNumber=" + gstNumber + ", businessTypeMstr=" + businessTypeMstr + ", channelPartnerId="
				+ channelPartnerId + ", aboutMe=" + aboutMe + ", code=" + code + ", entityType=" + entityType
				+ ", selfEmployedType=" + selfEmployedType + ", isProfileFilled=" + isProfileFilled + "]";
	}

	
}
