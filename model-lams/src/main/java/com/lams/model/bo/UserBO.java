package com.lams.model.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lams.model.bo.master.BusinessTypeBO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBO extends AuditorBO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long salutation;

	private String name;

	private String email;

	private String mobile;

	private Integer invitationCount;

	private Boolean isAcceptTermCondition;

	private String firstName;

	private String lastName;

	private String middleName;

	private AddressBO communicationAdd;

	private AddressBO permanentAdd;

	private Date birthDate;

	private Long gender;

	private Boolean isActive;

	private Boolean isOtpVerified;

	private Boolean isEmailVerified;

	private Long userType;

	private BankBO bank;

	private List<ApplicationsBO> applications;

	private String password;

	private String tempPassword;

	private String panCard;

	private String aadharCardNo;

	private String eduQualification;

	private String contactNumber;

	private String otp;

	private Boolean isSent;

	private Boolean isSameUsAddress;

	private Long employmentType;

	private String employerName;

	private String employmentAddress;

	private Double grossMonthlyIncome;

	private Integer totalWorkExperience;

	private String entityName;

	private Long selfEmployedType;

	private Long entityType;
	
	private BusinessTypeBO businessType;
	
	private String aboutMe;
	
	private UserBO channelPartner;
	
	private String code;
	
	private String gstNumber;
	
	private String contactPersonName;

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

	public AddressBO getCommunicationAdd() {
		return communicationAdd;
	}

	public void setCommunicationAdd(AddressBO communicationAdd) {
		this.communicationAdd = communicationAdd;
	}

	public AddressBO getPermanentAdd() {
		return permanentAdd;
	}

	public void setPermanentAdd(AddressBO permanentAdd) {
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
		return invitationCount;
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

	public List<ApplicationsBO> getApplications() {
		return applications;
	}

	public void setApplications(List<ApplicationsBO> applications) {
		this.applications = applications;
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

	public Long getSelfEmployedType() {
		return selfEmployedType;
	}

	public void setSelfEmployedType(Long selfEmployedType) {
		this.selfEmployedType = selfEmployedType;
	}

	public Long getEntityType() {
		return entityType;
	}

	public void setEntityType(Long entityType) {
		this.entityType = entityType;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Boolean getIsSent() {
		return isSent;
	}

	public void setIsSent(Boolean isSent) {
		this.isSent = isSent;
	}

	public BusinessTypeBO getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessTypeBO businessType) {
		this.businessType = businessType;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public UserBO getChannelPartner() {
		return channelPartner;
	}

	public void setChannelPartner(UserBO channelPartner) {
		this.channelPartner = channelPartner;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	@Override
	public String toString() {
		return "UserBO [id=" + id + ", salutation=" + salutation + ", name=" + name + ", email=" + email + ", mobile="
				+ mobile + ", invitationCount=" + invitationCount + ", isAcceptTermCondition=" + isAcceptTermCondition
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName
				+ ", communicationAdd=" + communicationAdd + ", permanentAdd=" + permanentAdd + ", birthDate="
				+ birthDate + ", gender=" + gender + ", isActive=" + isActive + ", isOtpVerified=" + isOtpVerified
				+ ", isEmailVerified=" + isEmailVerified + ", userType=" + userType + ", bank=" + bank
				+ ", applications=" + applications + ", password=" + password + ", tempPassword=" + tempPassword
				+ ", panCard=" + panCard + ", aadharCardNo=" + aadharCardNo + ", eduQualification=" + eduQualification
				+ ", contactNumber=" + contactNumber + ", otp=" + otp + ", isSent=" + isSent + ", isSameUsAddress="
				+ isSameUsAddress + ", employmentType=" + employmentType + ", employerName=" + employerName
				+ ", employmentAddress=" + employmentAddress + ", grossMonthlyIncome=" + grossMonthlyIncome
				+ ", totalWorkExperience=" + totalWorkExperience + ", entityName=" + entityName + ", selfEmployedType="
				+ selfEmployedType + ", entityType=" + entityType + ", businessType=" + businessType + ", aboutMe="
				+ aboutMe + ", channelPartner=" + channelPartner + ", code=" + code + ", gstNumber=" + gstNumber
				+ ", contactPersonName=" + contactPersonName + "]";
	}

	
}