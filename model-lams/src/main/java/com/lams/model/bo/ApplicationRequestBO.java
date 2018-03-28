package com.lams.model.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationRequestBO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long applicationTypeId;
	private Object data;
	private Long userId;
	private String status;
	
	public Long getApplicationTypeId() {
		return applicationTypeId;
	}
	public void setApplicationTypeId(Long applicationTypeId) {
		this.applicationTypeId = applicationTypeId;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
