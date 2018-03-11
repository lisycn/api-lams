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
import com.lams.api.domain.master.AddressMstr;
import com.lams.api.domain.master.CityMstr;
import com.lams.api.domain.master.CountryMstr;
import com.lams.api.domain.master.StateMstr;
import com.lams.api.repository.UserMstrRepository;
import com.lams.api.repository.master.AddressMstrRepository;
import com.lams.api.repository.master.BankMstrRepository;
import com.lams.api.service.UserMstrService;
import com.lams.api.service.master.AddressService;
import com.lams.model.bo.AddressBO;
import com.lams.model.bo.LamsResponse;
import com.lams.model.bo.UserBO;
import com.lams.model.bo.master.CityBO;
import com.lams.model.bo.master.CountryBO;
import com.lams.model.bo.master.StateBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.Enums;
import com.lams.model.utils.Enums.UserType;

@Service
@Transactional
public class UserMstrServiceImpl implements UserMstrService {

	public final static Logger logger = Logger.getLogger(UserMstrServiceImpl.class.getName());

	@Autowired
	private UserMstrRepository userMstrRepository;

	@Autowired
	private BankMstrRepository bankMstrRepository;

	@Autowired
	private AddressService addressService;

	@Autowired
	private AddressMstrRepository addressMstrRepository;

	@Override
	public LamsResponse registration(UserBO userBO, Long userId) {

		User user = null;
		if (!CommonUtils.isObjectNullOrEmpty(userBO.getId())) {
			user = userMstrRepository.findOne(userBO.getId());
		}
		if (CommonUtils.isObjectNullOrEmpty(user)) {
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
			user = new User();
			user.setCreatedDate(new Date());
			user.setCreatedBy(userId);
			user.setIsActive(true);
		} else {
			if (!user.getIsActive()) {
				logger.info("Current User is Inactive ------------------------->" + userBO.getMobile());
				return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "User is inactive");
			}
			// CHECK IF EMAIL IS EXIST
			if (userMstrRepository.checkEmailById(userBO.getEmail(), user.getId()) > 0) {
				logger.info("Email is already exist ------------------------->" + userBO.getEmail());
				return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Email is Already Exist");
			}

			// CHECK IF MOBILE IS EXIST
			if (userMstrRepository.checkMobileById(userBO.getMobile(), user.getId()) > 0) {
				logger.info("Mobile is already exist ------------------------->" + userBO.getMobile());
				return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Mobile is Already Exist");
			}
			user.setModifiedDate(new Date());
			user.setModifiedBy(userId);
		}
		BeanUtils.copyProperties(userBO, user, "id", "isActive", "createdDate", "createdBy");
		user.setIsAcceptTermCondition(true);
		user.setIsEmailVerified(
				!CommonUtils.isObjectNullOrEmpty(userBO.getIsEmailVerified()) ? userBO.getIsEmailVerified() : false);
		user.setIsOtpVerified(
				!CommonUtils.isObjectNullOrEmpty(userBO.getIsOtpVerified()) ? userBO.getIsOtpVerified() : false);

		if (!CommonUtils.isObjectNullOrEmpty(userBO.getUserType())) {
			UserType userType = Enums.UserType.getType(userBO.getUserType().intValue());
			if (CommonUtils.isObjectNullOrEmpty(userType) && userType.equals(Enums.UserType.LENDER)) {
				if (!CommonUtils.isObjectNullOrEmpty(userBO.getBank())) {
					user.setBank(bankMstrRepository.findOne(userBO.getBank().getId()));
				}
			}
		}
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

		List<AddressMstr> addressMstrList = addressMstrRepository.findByUserIdAndIsActive(user.getId(), true);
		for (AddressMstr addressMstr : addressMstrList) {
			AddressBO addressBO = new AddressBO();
			BeanUtils.copyProperties(addressMstr, addressBO);
			// Copy City
			CityMstr cityMstr = addressMstr.getCity();
			if (!CommonUtils.isObjectNullOrEmpty(cityMstr)) {
				CityBO cityBO = new CityBO();
				BeanUtils.copyProperties(cityMstr, cityBO);
				addressBO.setCity(cityBO);

				// Copy State
				StateMstr state = cityMstr.getState();
				if (!CommonUtils.isObjectNullOrEmpty(state)) {
					StateBO stateBO = new StateBO();
					BeanUtils.copyProperties(state, stateBO);
					addressBO.setState(stateBO);
					
					// Copy Country
					CountryMstr country = state.getCountry();
					if (!CommonUtils.isObjectNullOrEmpty(country)) {
						CountryBO coutryBO = new CountryBO();
						BeanUtils.copyProperties(country, coutryBO);
						addressBO.setCountry(coutryBO);
					}
				}
			}
			if(addressMstr.getAddType() == CommonUtils.AddressType.PERMANENT) {
				userBo.setPermanentAdd(addressBO);	
			} else if(addressMstr.getAddType() == CommonUtils.AddressType.COMMUNICATION) {
				userBo.setCommunicationAdd(addressBO);
			}
			
		}
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
			UserType userType = Enums.UserType.getType(userBO.getUserType().intValue());
			if (CommonUtils.isObjectNullOrEmpty(userType) && userType.equals(Enums.UserType.LENDER)) {
				if (!CommonUtils.isObjectNullOrEmpty(userBO.getBank())) {
					user.setBank(bankMstrRepository.findOne(userBO.getBank().getId()));
				}
			}
		}
		user.setModifiedDate(new Date());
		user.setModifiedBy(userBO.getId());
		user = userMstrRepository.save(user);
		// Setting Address
		if(!CommonUtils.isObjectNullOrEmpty(userBO.getPermanentAdd())) {
			addressService.saveAddress(userBO.getPermanentAdd(), userBO.getId(),CommonUtils.AddressType.PERMANENT);	
		}
		if(!CommonUtils.isObjectNullOrEmpty(userBO.getCommunicationAdd())) {
			addressService.saveAddress(userBO.getCommunicationAdd(), userBO.getId(),CommonUtils.AddressType.COMMUNICATION);	
		}
		

		logger.log(Level.INFO, "Successfully updated User Details for ID----" + user.getId());
		return new LamsResponse(HttpStatus.OK.value(), "Success", getUserById(user.getId()));
	}
}
