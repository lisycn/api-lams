package com.lams.api.domain.master;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the otp_type_master database table.
 * 
 */
@Entity
@Table(name = "otp_type_master")
@NamedQuery(name = "OtpTypeMaster.findAll", query = "SELECT o FROM OtpTypeMaster o")
public class OtpTypeMaster extends MasterBase implements Serializable {
	private static final long serialVersionUID = 1L;

	public OtpTypeMaster() {
		super();
	}

	public OtpTypeMaster(Long id) {
		super(id);
	}
}