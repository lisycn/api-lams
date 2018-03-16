package com.lams.model.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OTPRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3492982882631535965L;
	private String mobileNo;
	private String requestId;
	private Long requestType;
	private Long masterId;
	private String emailId;
	private String otp;
	private String templateName;

	public OTPRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Long getRequestType() {
		return requestType;
	}

	public void setRequestType(Long requestType) {
		this.requestType = requestType;
	}

	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Override
	public String toString() {
		return "OTPRequest [mobileNo=" + mobileNo + ", requestId=" + requestId + ", requestType=" + requestType
				+ ", masterId=" + masterId + ", emailId=" + emailId + ", otp=" + otp + ", templateName=" + templateName
				+ "]";
	}
}
