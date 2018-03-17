package com.lams.api.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.lams.api.domain.LoginAuditTrail;
import com.lams.api.domain.User;
import com.lams.api.repository.LoginAuditTrailRepository;
import com.lams.api.repository.UserMstrRepository;
import com.lams.api.service.LoginService;
import com.lams.api.service.UserMstrService;
import com.lams.model.bo.LoginResponse;
import com.lams.model.bo.UserBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.Enums.OTPType;
import com.lams.model.utils.NotificationAlias;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	public static final Logger logger = Logger.getLogger(LoginServiceImpl.class);

	@Autowired
	private UserMstrRepository userMstrRepository;

	@Autowired
	private UserMstrService userMstrService;

	@Autowired
	private LoginAuditTrailRepository auditTrailRepository;

	@Override
	public LoginResponse login(UserBO userBO) {

		String encrptPass = DigestUtils.md5DigestAsHex(userBO.getPassword().getBytes()).toString();
		User user = userMstrRepository.findByEmailAndPasswordAndIsActive(userBO.getEmail(), encrptPass, true);
		if (CommonUtils.isObjectNullOrEmpty(user)) {
			logger.info("Email and password not matched -------------------->" + userBO.getEmail());
			return new LoginResponse(HttpStatus.UNAUTHORIZED.value(), "Invalid email or password");
		}
		
		//Check if OTP is Verified or not
		if (CommonUtils.isObjectNullOrEmpty(user.getIsOtpVerified()) || !user.getIsOtpVerified()) {
			boolean sendOtp = userMstrService.sendOtp(user, OTPType.REGISTRATION, NotificationAlias.SMS);
			BeanUtils.copyProperties(user, userBO, "tempPassword", "password");
			userBO.setIsSent(sendOtp);
			return new LoginResponse(HttpStatus.OK.value(),
					"We have sent OTP on " + user.getMobile() + " as you Mobile Verification was Remain.", userBO);
		}

		//Check if Email is Verified or not
//		if (CommonUtils.isObjectNullOrEmpty(user.getIsEmailVerified()) || !user.getIsEmailVerified()) {
//			BeanUtils.copyProperties(user, userBO, "tempPassword", "password");
//			return new LoginResponse(HttpStatus.OK.value(), "We have sent Email Verification Link on " + user.getEmail()
//					+ " as you Email Verification was Remain.", userBO);
//		}

		LoginAuditTrail loginAuditTrail = new LoginAuditTrail();
		loginAuditTrail.setEmail(user.getEmail());
		loginAuditTrail.setLoginDate(new Date());
		String token = CommonUtils.generateRandomToken();
		loginAuditTrail.setToken(token);
		loginAuditTrail.setUserId(user.getId());
		loginAuditTrail.setUserType(user.getUserType());
		loginAuditTrail.setIsActive(true);
		auditTrailRepository.save(loginAuditTrail);
		LoginResponse loginResponse = new LoginResponse(HttpStatus.OK.value(), "Login Successfully");
		loginResponse.setUsertype(user.getUserType());
		loginResponse.setToken(token);
		logger.info("Login successfully ------------------->" + userBO.getEmail());
		return loginResponse;
	}

	@Override
	public LoginResponse logout(String token) {
		LoginAuditTrail loginAuditTrail = auditTrailRepository.findByTokenAndIsActive(token, true);
		if (CommonUtils.isObjectNullOrEmpty(loginAuditTrail)) {
			return new LoginResponse(HttpStatus.BAD_REQUEST.value(), "Invalid token");
		}
		loginAuditTrail.setIsActive(false);
		return new LoginResponse(HttpStatus.OK.value(), "Logout successfully");
	}

}
