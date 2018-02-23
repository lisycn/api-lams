package com.lams.model.bo;

import java.io.Serializable;

public class LamsResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String message;
	private Object data;
	
	public LamsResponse() {
	}
	
	public LamsResponse(Integer status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public LamsResponse(Integer status, String message , Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
