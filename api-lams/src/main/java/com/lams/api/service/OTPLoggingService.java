package com.lams.api.service;

import java.text.ParseException;

import com.lams.model.bo.OTPRequest;

public interface OTPLoggingService {

	public boolean sendOTP(OTPRequest request);
	
	public String getOTP(OTPRequest request);
	
	public boolean isOTPExists(OTPRequest request);
	
	public boolean verifyOTP(OTPRequest request) throws ParseException;
}
