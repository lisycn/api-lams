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

import com.lams.api.domain.master.ApplicationTypeMstr;
import com.lams.api.domain.master.Auditor;

@Entity
@Table(name = "lender_product_mapping")
public class LenderApplicationMapping extends Auditor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "application_type_id")
	private ApplicationTypeMstr applicationTypeId;

	@Column(name = "user_id")
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ApplicationTypeMstr getApplicationTypeId() {
		return applicationTypeId;
	}

	public void setApplicationTypeId(ApplicationTypeMstr applicationTypeId) {
		this.applicationTypeId = applicationTypeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "LenderApplicationMapping [id=" + id + ", applicationTypeId=" + applicationTypeId + ", userId=" + userId
				+ "]";
	}
}
