package com.lams.model.bo.master;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityBO extends MasterBaseBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4805059037944649444L;
	private StateBO state;

	public CityBO() {
		super();
	}

	public StateBO getState() {
		return state;
	}

	public void setState(StateBO state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "CityBO [state=" + state + "]";
	}
}
