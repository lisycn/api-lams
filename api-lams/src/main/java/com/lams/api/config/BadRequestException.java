package com.lams.api.config;

public class BadRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	private Integer status;

	public BadRequestException(String message,Integer status) {
		super(message);
		this.message = message;
		this.status = status;
	}
	
	public BadRequestException(String message,Throwable throwble, Integer status) {
		super(message, throwble);
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	
}
