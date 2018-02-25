package com.lams.model.bo.master;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lams.model.bo.AuditorBO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MasterBaseBO extends AuditorBO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -651564742613886918L;

	private Long id;

	private String name;

	private String code;

	public MasterBaseBO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "MasterBaseBO [id=" + id + ", name=" + name + ", code=" + code + "]";
	}
}
