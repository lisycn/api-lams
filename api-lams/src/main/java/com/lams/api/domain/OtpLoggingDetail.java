package com.lams.api.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lams.api.domain.master.Auditor;
import com.lams.api.domain.master.OtpTypeMaster;

/**
 * The persistent class for the otp_logging_details database table.
 * 
 */
@Entity
@Table(name = "otp_logging_details")
public class OtpLoggingDetail extends Auditor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8173598054898240710L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private OtpTypeMaster type;

	@Column(name = "otp")
	private String otp;

	@Column(name = "master_id")
	private Long masterId;

	@Column(name = "is_verified")
	private boolean isVerified;

	@Column(name = "is_expired")
	private boolean isExpired;

	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(name = "email")
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public OtpTypeMaster getType() {
		return type;
	}

	public void setType(OtpTypeMaster type) {
		this.type = type;
	}

	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "OtpLoggingDetail [id=" + id + ", type=" + type + ", otp=" + otp + ", masterId=" + masterId
				+ ", isVerified=" + isVerified + ", isExpired=" + isExpired + ", mobileNo=" + mobileNo + ", email="
				+ email + "]";
	}
}