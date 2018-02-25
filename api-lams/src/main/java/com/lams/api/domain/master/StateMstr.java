package com.lams.api.domain.master;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "state_mstr")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class StateMstr extends MasterBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private CountryMstr country;

	public StateMstr() {
		super();
	}

	public CountryMstr getCountry() {
		return country;
	}

	public void setCountry(CountryMstr country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "StateMstr [country=" + country + "]";
	}
}
