package com.lams.model.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lams.model.bo.master.BusinessTypeBO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailBo extends AuditorBO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String email;

	private String contact;
	
	private String msg;

	
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	
}