package com.lams.model.bo.master;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessTypeBO extends MasterBaseBO implements Serializable {

	private static final long serialVersionUID = 1L;

	public BusinessTypeBO() {
		super();
	}

	@Override
	public String toString() {
		return "BusinessTypeBO []";
	}
}
