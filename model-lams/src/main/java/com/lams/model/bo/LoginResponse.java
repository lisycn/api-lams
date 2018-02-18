package com.lams.model.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse extends LamsResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long usertype;
	private String token;
	
	public LoginResponse() {
		
	}
	
	public LoginResponse(Integer status, String message) {
		super(status, message);
	}
	
	public Long getUsertype() {
		return usertype;
	}
	public void setUsertype(Long usertype) {
		this.usertype = usertype;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	

}
