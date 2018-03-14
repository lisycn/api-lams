package com.lams.api.service;

import java.util.List;

import com.lams.model.bo.LamsResponse;
import com.lams.model.bo.UserBO;

public interface UserMstrService {

	public LamsResponse registration(UserBO userBO,Long userId);
	
	public List<UserBO> getUsersByUserType(Long userType);
	
	public UserBO getUserById(Long id);
	
	public LamsResponse updateUserDetails(UserBO userBO);
	
//	public LamsResponse verifyEmail(String email);
	
	public UserBO inviteLender(UserBO userBO,Long userId) throws Exception;
}

