package com.lams.api.service;

import java.text.ParseException;
import java.util.List;

import com.lams.api.domain.User;
import com.lams.model.bo.LamsResponse;
import com.lams.model.bo.UserBO;
import com.lams.model.utils.Enums.OTPType;

public interface UserMstrService {

	public LamsResponse registration(UserBO userBO, Long userId);

	public List<UserBO> getUsersByUserType(Long userType);

	public UserBO getUserById(Long id);

	public LamsResponse updateUserDetails(UserBO userBO);

	public LamsResponse verifyOTP(UserBO userBO, OTPType type) throws ParseException;

	public UserBO inviteLender(UserBO userBO, Long userId) throws Exception;

	public LamsResponse resendOtp(UserBO userBO, OTPType type, String templateName);
	
	public LamsResponse changePassword(UserBO userBO);

	public boolean sendOtp(User user, OTPType type, String templateName);
}
