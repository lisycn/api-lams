package com.lams.model.bo;

import java.io.Serializable;

import com.lams.model.bo.master.ApplicationTypeMstrBO;

public class LenderApplicationMappingBO extends AuditorBO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private ApplicationTypeMstrBO applicationTypeMstrBO;

	private UserBO user;

	public LenderApplicationMappingBO() {
		super();
	}

	public LenderApplicationMappingBO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ApplicationTypeMstrBO getApplicationTypeMstrBO() {
		return applicationTypeMstrBO;
	}

	public void setApplicationTypeMstrBO(ApplicationTypeMstrBO applicationTypeMstrBO) {
		this.applicationTypeMstrBO = applicationTypeMstrBO;
	}

	public UserBO getUser() {
		return user;
	}

	public void setUser(UserBO user) {
		this.user = user;
	}

}
