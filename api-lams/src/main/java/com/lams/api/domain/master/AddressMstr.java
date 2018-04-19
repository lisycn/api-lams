package com.lams.api.domain.master;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lams.api.domain.User;

@Entity
@Table(name = "address_mstr")
public class AddressMstr extends Auditor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "building_name")
	private String premisesAndBuildingName;
	
	@Column(name = "street_name")
	private String streetName;

	@Column(name = "land_mark")
	private String landMark;
	
	@Column(name = "add_type")
	private Integer addType;

	private String pincode;

//	@ManyToOne
//	@JoinColumn(name = "country_id")
//	private CountryMstr country;
//
//	@ManyToOne
//	@JoinColumn(name = "state_id")
//	private StateMstr state;

	@ManyToOne
	@JoinColumn(name = "city_id")
	private CityMstr city;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CityMstr getCity() {
		return city;
	}

	public void setCity(CityMstr city) {
		this.city = city;
	}
	
	public Integer getAddType() {
		return addType;
	}

	public void setAddType(Integer addType) {
		this.addType = addType;
	}

	public String getPremisesAndBuildingName() {
		return premisesAndBuildingName;
	}

	public void setPremisesAndBuildingName(String premisesAndBuildingName) {
		this.premisesAndBuildingName = premisesAndBuildingName;
	}

	@Override
	public String toString() {
		return "AddressMstr [id=" + id + ", streetName=" + streetName + ", landMark=" + landMark + ", addType="
				+ addType + ", pincode=" + pincode + ", city=" + city + ", user=" + user + "]";
	}

	

}
