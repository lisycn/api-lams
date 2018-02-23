package com.lams.api.service;

import com.lams.model.bo.LoginResponse;
import com.lams.model.bo.UserBO;

public interface LoginService {

	public LoginResponse login(UserBO userBO);
	
	public LoginResponse logout(String token);
}
