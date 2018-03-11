package com.lams.model.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lams.model.bo.master.CityBO;
import com.lams.model.bo.master.CountryBO;
import com.lams.model.bo.master.StateBO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressBO extends AuditorBO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String streetName;

	private String landMark;

	private String pincode;

	private CountryBO country;

	private StateBO state;

	private CityBO city;
	
	private Integer addType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public CountryBO getCountry() {
		return country;
	}

	public void setCountry(CountryBO country) {
		this.country = country;
	}

	public StateBO getState() {
		return state;
	}

	public void setState(StateBO state) {
		this.state = state;
	}

	public CityBO getCity() {
		return city;
	}

	public void setCity(CityBO city) {
		this.city = city;
	}

	public Integer getAddType() {
		return addType;
	}

	public void setAddType(Integer addType) {
		this.addType = addType;
	}

	@Override
	public String toString() {
		return "AddressBO [id=" + id + ", streetName=" + streetName + ", landMark=" + landMark + ", pincode=" + pincode
				+ ", country=" + country + ", state=" + state + ", city=" + city + ", addType=" + addType + "]";
	}


}
