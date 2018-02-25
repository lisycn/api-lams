package com.lams.model.bo.master;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StateBO extends MasterBaseBO implements Serializable {

	private static final long serialVersionUID = 1L;

	private CountryBO country;

	public StateBO() {
		super();
	}

	public CountryBO getCountry() {
		return country;
	}

	public void setCountry(CountryBO country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "StateMstr [country=" + country + "]";
	}
}
