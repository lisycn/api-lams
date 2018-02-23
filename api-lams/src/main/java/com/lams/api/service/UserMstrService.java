package com.lams.api.service;

import java.util.List;

import com.lams.model.bo.LamsResponse;
import com.lams.model.bo.UserBO;

public interface UserMstrService {

	public LamsResponse registration(UserBO userBO);
	
	public List<UserBO> getUsersByUserType(Long userType);
}
