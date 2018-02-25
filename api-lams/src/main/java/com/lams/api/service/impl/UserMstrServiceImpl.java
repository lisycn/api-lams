package com.lams.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.lams.api.domain.User;
import com.lams.api.repository.BankMstrRepository;
import com.lams.api.repository.UserMstrRepository;
import com.lams.api.service.UserMstrService;
import com.lams.model.bo.LamsResponse;
import com.lams.model.bo.UserBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.CommonUtils.UserType;

@Service
@Transactional
public class UserMstrServiceImpl implements UserMstrService {

	public final static Logger logger = Logger.getLogger(UserMstrServiceImpl.class.getName());

	@Autowired
	private UserMstrRepository userMstrRepository;

	@Autowired
	private BankMstrRepository bankMstrRepository;

	@Override
	public LamsResponse registration(UserBO userBO) {

		// CHECK IF EMAIL IS EXIST
		if (userMstrRepository.checkEmail(userBO.getEmail()) > 0) {
			logger.info("Email is already exist ------------------------->" + userBO.getEmail());
			return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Email is Already Exist");
		}

		// CHECK IF MOBILE IS EXIST
		if (userMstrRepository.checkMobile(userBO.getMobile()) > 0) {
			logger.info("Mobile is already exist ------------------------->" + userBO.getMobile());
			return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Mobile is Already Exist");
		}
		User user = new User();
		BeanUtils.copyProperties(userBO, user, "id");
		user.setIsActive(true);
		user.setCreatedDate(new Date());
		if (!CommonUtils.isObjectNullOrEmpty(userBO.getUserType())) {
			UserType userType = CommonUtils.UserType.getType(userBO.getUserType().intValue());
			if (CommonUtils.isObjectNullOrEmptyOrDash(userType) && userType.equals(CommonUtils.UserType.LENDER)) {
				if (!CommonUtils.isObjectNullOrEmpty(userBO.getBank())) {
					user.setBank(bankMstrRepository.findOne(userBO.getBank().getId()));
				}
			}
		}
		user.setIsAcceptTermCondition(true);
		user.setIsEmailVerified(false);
		user.setIsOtpVerified(false);
		user.setPassword(DigestUtils.md5DigestAsHex(userBO.getPassword().getBytes()).toString());
		userMstrRepository.save(user);
		logger.info(
				"Successfully registration --------EMAIL---> " + userBO.getEmail() + "---------ID----" + user.getId());
		return new LamsResponse(HttpStatus.OK.value(), "Successfully Registration");
	}

	@Override
	public List<UserBO> getUsersByUserType(Long userType) {
		List<User> userList = null;
		if (CommonUtils.isObjectNullOrEmpty(userType) || userType == -1) {
			userList = userMstrRepository.findAll();
		} else {
			userList = userMstrRepository.findByUserType(userType);
		}

		List<UserBO> userBOList = new ArrayList<>(userList.size());
		UserBO userBo = null;
		for (User user : userList) {
			userBo = new UserBO();
			BeanUtils.copyProperties(user, userBo, "password");
			userBOList.add(userBo);
		}
		return userBOList;
	}

	@Override
	public UserBO getUserById(Long id) {
		User user = userMstrRepository.findOne(id);
		if (CommonUtils.isObjectNullOrEmpty(user)) {
			logger.log(Level.INFO, "Invalid User Id while getting Object by Id==>{}", id);
			return null;
		}
		UserBO userBo = new UserBO();
		BeanUtils.copyProperties(user, userBo, "password");
		return userBo;
	}

	@Override
	public LamsResponse updateUserDetails(UserBO userBO) {
		User user = userMstrRepository.findOne(userBO.getId());
		if (CommonUtils.isObjectNullOrEmpty(user)) {
			logger.log(Level.INFO, "Invalid User Id while getting Object by Id==>{}", userBO.getId());
			return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "User not Found");
		}
		BeanUtils.copyProperties(userBO, user, "password");
		if (!CommonUtils.isObjectNullOrEmpty(userBO.getUserType())) {
			UserType userType = CommonUtils.UserType.getType(userBO.getUserType().intValue());
			if (CommonUtils.isObjectNullOrEmptyOrDash(userType) && userType.equals(CommonUtils.UserType.LENDER)) {
				if (!CommonUtils.isObjectNullOrEmpty(userBO.getBank())) {
					user.setBank(bankMstrRepository.findOne(userBO.getBank().getId()));
				}
			}
		}
		user.setModifiedDate(new Date());
		user.setModifiedBy(userBO.getId());
		userMstrRepository.save(user);
		logger.log(Level.INFO, "Successfully updated User Details for ID----" + user.getId());
		return new LamsResponse(HttpStatus.OK.value(), "Success", getUserById(user.getId()));
	}
}
