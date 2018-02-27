package com.lams.api.domain.master;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "city_mstr")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class CityMstr extends MasterBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4805059037944649444L;
	@ManyToOne
	@JoinColumn(name = "state_id")
	private StateMstr state;

	public CityMstr() {
		super();
	}

	public CityMstr(Long id) {
		super();
		this.setId(id);
	}

	public StateMstr getState() {
		return state;
	}

	public void setState(StateMstr state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "CityMstr [state=" + state + "]";
	}
}
