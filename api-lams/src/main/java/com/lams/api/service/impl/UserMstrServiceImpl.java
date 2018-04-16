package com.lams.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.lams.api.domain.User;
import com.lams.api.domain.master.AddressMstr;
import com.lams.api.domain.master.ApplicationTypeMstr;
import com.lams.api.domain.master.BankMstr;
import com.lams.api.domain.master.BusinessTypeMstr;
import com.lams.api.domain.master.CityMstr;
import com.lams.api.domain.master.CountryMstr;
import com.lams.api.domain.master.StateMstr;
import com.lams.api.repository.LenderApplicationMappingRepository;
import com.lams.api.repository.UserMstrRepository;
import com.lams.api.repository.master.AddressMstrRepository;
import com.lams.api.repository.master.BusinessTypeMstrRepository;
import com.lams.api.service.ApplicationsService;
import com.lams.api.service.LenderApplicationMappingService;
import com.lams.api.service.NotificationService;
import com.lams.api.service.OTPLoggingService;
import com.lams.api.service.UserMstrService;
import com.lams.api.service.master.AddressService;
import com.lams.model.bo.AddressBO;
import com.lams.model.bo.ApplicationsBO;
import com.lams.model.bo.BankBO;
import com.lams.model.bo.LamsResponse;
import com.lams.model.bo.LoginResponse;
import com.lams.model.bo.NotificationBO;
import com.lams.model.bo.NotificationMainBO;
import com.lams.model.bo.NotificationResponse;
import com.lams.model.bo.OTPRequest;
import com.lams.model.bo.UserBO;
import com.lams.model.bo.master.BusinessTypeBO;
import com.lams.model.bo.master.CityBO;
import com.lams.model.bo.master.CountryBO;
import com.lams.model.bo.master.StateBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.Enums;
import com.lams.model.utils.Enums.ContentType;
import com.lams.model.utils.Enums.NotificationType;
import com.lams.model.utils.Enums.OTPType;
import com.lams.model.utils.Enums.UserType;
import com.lams.model.utils.NotificationAlias;

@Service
@Transactional
public class UserMstrServiceImpl implements UserMstrService {

	public final static Logger logger = Logger.getLogger(UserMstrServiceImpl.class.getName());

	@Autowired
	private UserMstrRepository userMstrRepository;

	@Autowired
	private AddressService addressService;

	@Autowired
	private AddressMstrRepository addressMstrRepository;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private LenderApplicationMappingService lenderApplicationMappingService;

	@Autowired
	private LenderApplicationMappingRepository lenderApplicationMappingRepository;

	@Autowired
	private ApplicationsService applicationsService;

	@Autowired
	private OTPLoggingService oTPLoggingService;

	@Autowired
	private BusinessTypeMstrRepository businessTypeMstrRepository;

	@Value("${com.lams.login.url}")
	private String loginUrl;

	@Value("${com.lams.email.base.url}")
	private String baseUrl;

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
			user.setInvitationCount(0);
		} else {
			if (Enums.UserType.LENDER.getId() != userBO.getUserType()) {
				if (!user.getIsActive()) {
					logger.info("Current User is Inactive ------------------------->" + userBO.getMobile());
					return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "You are not active user.");
				}
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
		BeanUtils.copyProperties(userBO, user, "id", "isActive", "createdDate", "createdBy", "invitationCount");
		user.setIsAcceptTermCondition(true);
		if (!CommonUtils.isObjectNullOrEmpty(userBO.getUserType())) {
			UserType userType = Enums.UserType.getType(userBO.getUserType().intValue());
			if (!CommonUtils.isObjectNullOrEmpty(userType) && userType.equals(Enums.UserType.LENDER)) {
				user.setIsEmailVerified(true);
				user.setIsOtpVerified(true);
				if (CommonUtils.isObjectNullOrEmpty(userBO.getIsActive())) {
					user.setIsActive(false);
				} else {
					user.setIsActive(userBO.getIsActive());
				}

				if (!CommonUtils.isObjectNullOrEmpty(userBO.getBank())
						&& !CommonUtils.isObjectNullOrEmpty(userBO.getBank().getId())) {
					user.setBank(new BankMstr(userBO.getBank().getId()));
				}
			} else if (!CommonUtils.isObjectNullOrEmpty(userType)
					&& (userType.equals(Enums.UserType.BORROWER) || userType.equals(Enums.UserType.CHANNEL_PARTNER))) {
				user.setIsEmailVerified(false);
				user.setIsOtpVerified(false);
			}
		}
		user.setPassword(DigestUtils.md5DigestAsHex(userBO.getPassword().getBytes()).toString());
		user.setTempPassword(userBO.getPassword());
		user.setIsProfileFilled(false);
		user = userMstrRepository.save(user);

		// Adding Mapping
		if (!CommonUtils.isListNullOrEmpty(userBO.getApplications())) {
			lenderApplicationMappingRepository.inActiveByUserId(user.getId(), userId);
			for (ApplicationsBO appBo : userBO.getApplications()) {
				lenderApplicationMappingService.save(appBo.getApplicationTypeId(), user.getId(), userId);
			}
		}

		// Sending OTP to Registered Email
		if (!CommonUtils.isObjectNullOrEmpty(user.getUserType())
				&& (Enums.UserType.BORROWER.getId() == user.getUserType().intValue()
						|| Enums.UserType.CHANNEL_PARTNER.getId() == user.getUserType().intValue())) {
			boolean isMailSend = false;
			boolean sendOtp = false;
			logger.log(Level.INFO, "Is Otp Sent===>{0}", sendOtp);

			try {
				sendOtp = sendOtp(user, OTPType.REGISTRATION, NotificationAlias.SMS);
				String subject = "VfinanceS – E Mail Verification";
				isMailSend = sendLinkOnMail(user, NotificationAlias.EMAIL_VERIFY_ACCOUNT, subject,
						"email-verification");
				BeanUtils.copyProperties(user, userBO, "tempPassword", "password");
				userBO.setIsSent(isMailSend);
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("Error while Sending Mail");
			}

			if (sendOtp || isMailSend) {
				logger.log(Level.INFO, "OTP Sent For Mobile===>{0}=====Email=={1}",
						new Object[] { user.getMobile(), user.getEmail() });
				userBO.setId(user.getId());
				if (sendOtp) {
					return new LoginResponse(HttpStatus.OK.value(), "We have sent OTP On " + user.getMobile() + ".",
							userBO);
				} else if (isMailSend) {
					return new LoginResponse(HttpStatus.OK.value(),
							"We have sent Email Verification Link on " + user.getEmail() + ".", userBO);
				}
			}

			if (Enums.UserType.CHANNEL_PARTNER.getId() == user.getUserType().intValue()) {
				Long codeCount = userMstrRepository.getCodeCount();
				logger.log(Level.INFO, "Total Channel Partener Users Count===>{0}", new Object[] { codeCount });
				String code = null;
				if (codeCount == null) {
					code = CommonUtils.generateCPCode(0l);
				} else {
					code = CommonUtils.generateCPCode(codeCount);
				}
				user.setCode(code);
			}
		}
		logger.info(
				"Successfully registration --------EMAIL---> " + userBO.getEmail() + "---------ID----" + user.getId());
		String msg = "Successfully Registration";
		if (!CommonUtils.isObjectNullOrEmpty(userBO.getId())) {
			msg = "Successfully Updated";
		}
		return new LamsResponse(HttpStatus.OK.value(), msg, userBO);
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
			if (!CommonUtils.isObjectNullOrEmpty(user.getBank())) {
				BankBO bankBO = new BankBO();
				BeanUtils.copyProperties(user.getBank(), bankBO);
				userBo.setBank(bankBO);
			}
			if (user.getUserType() == Enums.UserType.BORROWER.getId()) {
				// Set Borrower Applications
				userBo.setApplications(applicationsService.getAll(user.getId()));
			} else if (user.getUserType() == Enums.UserType.LENDER.getId()) {
				// Set Lender Applications
				List<ApplicationTypeMstr> list = lenderApplicationMappingRepository
						.getApplicationByUserIdAndIsActive(user.getId(), true);
				List<ApplicationsBO> apps = new ArrayList<>(list.size());
				for (ApplicationTypeMstr mstr : list) {
					ApplicationsBO bo = new ApplicationsBO();
					bo.setApplicationTypeId(mstr.getId());
					bo.setApplicationTypeName(mstr.getName());
					apps.add(bo);
				}
				userBo.setApplications(apps);
			}

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
		BeanUtils.copyProperties(user, userBo, "password", "tempPassword");
		if (user.getUserType() == Enums.UserType.BORROWER.getId()
				|| user.getUserType() == Enums.UserType.CHANNEL_PARTNER.getId()) {
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
				if (addressMstr.getAddType() == CommonUtils.AddressType.PERMANENT) {
					userBo.setPermanentAdd(addressBO);
				} else if (addressMstr.getAddType() == CommonUtils.AddressType.COMMUNICATION) {
					userBo.setCommunicationAdd(addressBO);
				}
			}
			BusinessTypeMstr businessTypeMstr = user.getBusinessTypeMstr();
			if (!CommonUtils.isObjectNullOrEmpty(businessTypeMstr)) {
				BusinessTypeBO bo = new BusinessTypeBO();
				BeanUtils.copyProperties(businessTypeMstr, bo);
				userBo.setBusinessType(bo);
			}
			userBo.setApplications(applicationsService.getAll(user.getId()));
		} else if (user.getUserType() == Enums.UserType.LENDER.getId()) {
			// Set Lender Applications
			List<ApplicationTypeMstr> list = lenderApplicationMappingRepository
					.getApplicationByUserIdAndIsActive(user.getId(), true);
			List<ApplicationsBO> apps = new ArrayList<>(list.size());
			for (ApplicationTypeMstr mstr : list) {
				ApplicationsBO bo = new ApplicationsBO();
				bo.setApplicationTypeId(mstr.getId());
				bo.setApplicationTypeName(mstr.getName());
				apps.add(bo);
			}
			if (!CommonUtils.isObjectNullOrEmpty(user.getBank())) {
				BankBO bankBO = new BankBO();
				BeanUtils.copyProperties(user.getBank(), bankBO);
				userBo.setBank(bankBO);
			}
			userBo.setApplications(apps);
		}
		return userBo;
	}

	@Override
	public LamsResponse addCpBorrower(UserBO userBO, Long userId) {
		logger.log(Level.INFO, "Enter in addCpBorrower");
		// CHECK IF EMAIL IS EXIST
		
		if(CommonUtils.isObjectNullOrEmpty(userBO.getId())) {
			if (userMstrRepository.checkEmail(userBO.getEmail()) > 0) {
				logger.info("Email is already exist ------------------------->" + userBO.getEmail());
				return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Email is Already Exist");
			}

			// CHECK IF MOBILE IS EXIST
			if (userMstrRepository.checkMobile(userBO.getMobile()) > 0) {
				logger.info("Mobile is already exist ------------------------->" + userBO.getMobile());
				return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Mobile is Already Exist");
			}			
		}else {
			if (userMstrRepository.checkEmailById(userBO.getEmail(),userBO.getId()) > 0) {
				logger.info("Email is already exist ------------------------->" + userBO.getEmail());
				return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Email is Already Exist");
			}

			// CHECK IF MOBILE IS EXIST
			if (userMstrRepository.checkEmailById(userBO.getMobile(),userBO.getId()) > 0) {
				logger.info("Mobile is already exist ------------------------->" + userBO.getMobile());
				return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Mobile is Already Exist");
			}
		}
		
		//Saving User Profile
		User user = null;
		if(!CommonUtils.isObjectNullOrEmpty(userBO.getId())) {
			user = userMstrRepository.findOne(userBO.getId());
			user.setModifiedBy(userId);
			user.setModifiedDate(new Date());
			if(CommonUtils.isObjectNullOrEmpty(user)) {
				logger.log(Level.INFO, "No User Detail Found for Given User Id. Something goes Wrong===={0}", new Object[] {userId});
				return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "No User Details Found for UserId");
			}
		}else {
			user = new User();
			user.setIsOtpVerified(true);
			user.setIsEmailVerified(true);
			user.setIsAcceptTermCondition(true);
			user.setIsActive(true);
			user.setChannelPartnerId(new User(userId));
			user.setUserType(userBO.getUserType());
			user.setCreatedDate(new Date());
		}
		
		//Create or Update User (Borrower)
		user.setEmail(userBO.getEmail());
		user.setMobile(userBO.getMobile());
		user.setName(userBO.getName());
		user.setSalutation(userBO.getSalutation());
		user = userMstrRepository.save(user);
		
		//Create Or Update Application
		if(!CommonUtils.isListNullOrEmpty(userBO.getApplications())) {
			for(ApplicationsBO applicationsBO : userBO.getApplications()) {
				applicationsService.saveFromCP(applicationsBO,user.getId(),userMstrRepository.findOne(userId).getCode());
			}
		}else {
			logger.log(Level.INFO, "No Application Selected by Channel Partner User =====>{0}", new Object[]{userId});
		}
		BeanUtils.copyProperties(user, userBO);
		userBO.setApplications(applicationsService.getAllByCP(user.getId(), userId));
		logger.log(Level.INFO, "Exit in addCpBorrower");
		return new LamsResponse(HttpStatus.OK.value(),"Success",userBO);
	}
	
	

	@Override
	public LamsResponse getCpUsersByUserType(Long cpUserId,Long userType) {
		List<User>  list = 	null;
		if(userType != null) {
			if(Enums.UserType.ALL.getId() == userType) {
				list = userMstrRepository.getUserByCpId(cpUserId);
			}else {
				list = userMstrRepository.getUserByCpIdAndUserType(cpUserId,userType);
			}
		}else {
			list = userMstrRepository.getUserByCpId(cpUserId);
		}
		if(CommonUtils.isListNullOrEmpty(list)) {
			return new LamsResponse(HttpStatus.OK.value(), "No Borrower Added Yet !",Collections.emptyList());
		}
		List<UserBO> userBOs = new ArrayList<>();
		for(User usr : list) {
			UserBO userBo  = new UserBO();
			BeanUtils.copyProperties(usr, userBo);
			userBo.setApplications(applicationsService.getAllByCP(usr.getId(), cpUserId));
			userBOs.add(userBo);
		}
		return new LamsResponse(HttpStatus.OK.value(), "Data Found",userBOs);
	}

	@Override
	public LamsResponse updateUserDetails(UserBO userBO) {
		User user = userMstrRepository.findOne(userBO.getId());
		if (CommonUtils.isObjectNullOrEmpty(user)) {
			logger.log(Level.INFO, "Invalid User Id while getting Object by Id==>{}", userBO.getId());
			return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "User not Found");
		}
		if (user.getUserType() == Enums.UserType.LENDER.getId()) {
			user.setFirstName(userBO.getFirstName());
			user.setMiddleName(userBO.getMiddleName());
			user.setLastName(userBO.getLastName());
			user.setSalutation(userBO.getSalutation());

		} else if (user.getUserType() == Enums.UserType.BORROWER.getId()
				|| user.getUserType() == Enums.UserType.CHANNEL_PARTNER.getId()) {
			BeanUtils.copyProperties(userBO, user, "password", "tempPassword", "email", "mobile");
			if (!CommonUtils.isObjectNullOrEmpty(userBO.getPermanentAdd())) {
				addressService.saveAddress(userBO.getPermanentAdd(), userBO.getId(), CommonUtils.AddressType.PERMANENT);
			}
			if (!CommonUtils.isObjectNullOrEmpty(userBO.getCommunicationAdd())) {
				addressService.saveAddress(userBO.getCommunicationAdd(), userBO.getId(),
						CommonUtils.AddressType.COMMUNICATION);
			}
			if (!CommonUtils.isObjectNullOrEmpty(userBO.getBusinessType())
					&& !CommonUtils.isObjectNullOrEmpty(userBO.getBusinessType().getId())) {
				user.setBusinessTypeMstr(new BusinessTypeMstr(userBO.getBusinessType().getId()));
			}
			// Updating already Existing Applications code
			if (CommonUtils.isObjectNullOrEmpty(userBO.getCode())
					&& Enums.UserType.CHANNEL_PARTNER.getId() == user.getUserType().intValue()) {
				Long codeCount = userMstrRepository.getCodeCount();
				logger.log(Level.INFO, "Total Channel Partener Users Count===>{0}", new Object[] { codeCount });
				String code = null;
				if (codeCount == null) {
					code = CommonUtils.generateCPCode(0l);
				} else {
					code = CommonUtils.generateCPCode(codeCount);
				}
				user.setCode(code);
			}
		}
		user.setModifiedDate(new Date());
		user.setModifiedBy(userBO.getId());
		user = userMstrRepository.save(user);
		// Setting Address
		logger.log(Level.INFO, "Successfully updated User Details for ID----" + user.getId());
		return new LamsResponse(HttpStatus.OK.value(), "Success", getUserById(user.getId()));
	}

	@Override
	public UserBO inviteLender(UserBO userBO, Long userId) throws Exception {
		logger.log(Level.INFO, "inviteLender==>{0}", userBO.toString());
		NotificationBO notificationBO = new NotificationBO();
		notificationBO.setClientRefId(String.valueOf(1l));
		List<NotificationMainBO> mainBolist = new ArrayList<>();
		NotificationMainBO mainBO = new NotificationMainBO();
		String to[] = { userBO.getEmail() };
		mainBO.setTo(to);
		mainBO.setContentType(ContentType.TEMPLATE);
		mainBO.setType(NotificationType.EMAIL);
		mainBO.setTemplateName(NotificationAlias.EMAIL_LENDER_INVITATION);
		Map<String, Object> data = new HashMap<>();
		data.put("title", "Hi," + userBO.getFirstName() + " " + userBO.getLastName());
		data.put("userName", userBO.getEmail());
		data.put("password", userBO.getTempPassword());
		data.put("loginUrl", loginUrl);
		mainBO.setParameters(data);
		mainBO.setSubject("Invitation From VFinance");
		mainBolist.add(mainBO);
		notificationBO.setNotifications(mainBolist);
		notificationService.sendNotification(notificationBO);

		// Increasing Invitation Count
		User user = userMstrRepository.findByEmailAndIsActive(userBO.getEmail(), true);
		user.setInvitationCount(user.getInvitationCount() + 1);
		user.setModifiedBy(userId);
		user.setModifiedDate(new Date());
		user = userMstrRepository.save(user);

		// Setting updated Fields
		userBO.setModifiedBy(userId);
		userBO.setInvitationCount(user.getInvitationCount());
		userBO.setModifiedDate(user.getModifiedDate());
		return userBO;
	}

	@Override
	public boolean sendOtp(User user, OTPType type, String templateName) {
		OTPRequest otpRequest = new OTPRequest();
		otpRequest.setMasterId(user.getId());
		otpRequest.setEmailId(user.getEmail());
		otpRequest.setMobileNo(user.getMobile());
		otpRequest.setRequestType(type.getId());
		otpRequest.setTemplateName(templateName);
		return oTPLoggingService.sendOTP(otpRequest);
	}

	@Override
	public LamsResponse verifyOTP(UserBO userBO, OTPType type) throws ParseException {
		// TODO Auto-generated method stub
		User user = userMstrRepository.findOne(userBO.getId());
		if (CommonUtils.isObjectNullOrEmpty(user)) {
			return new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.INVALID_REQUEST);
		}

		OTPRequest otpRequest = new OTPRequest();
		otpRequest.setEmailId(user.getEmail());
		otpRequest.setMasterId(user.getId());
		otpRequest.setMobileNo(user.getMobile());
		otpRequest.setRequestType(type.getId());
		otpRequest.setOtp(userBO.getOtp());
		boolean isVerified = oTPLoggingService.verifyOTP(otpRequest);
		user.setIsOtpVerified(isVerified);
		userMstrRepository.save(user);
		if (isVerified) {
			return new LamsResponse(HttpStatus.OK.value(), "Mobile No " + user.getMobile() + " SuccessFully Verified.");
		}
		return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid or Expired OTP.");
	}

	@Override
	public LamsResponse resendOtp(UserBO userBO, OTPType type, String templateName) {
		User user = userMstrRepository.findOne(userBO.getId());
		if (CommonUtils.isObjectNullOrEmpty(user)) {
			return new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.INVALID_REQUEST);
		}
		if (!templateName.contains("ftl")) {
			templateName = templateName.concat(".ftl");
		}
		boolean sendOtp = sendOtp(user, type, templateName);
		String msg = null;
		if (sendOtp) {
			msg = "Otp Successfully Sent on " + user.getMobile();
			return new LamsResponse(HttpStatus.OK.value(), msg);
		}
		msg = CommonUtils.SOMETHING_WENT_WRONG + " Please try again after Sometime!";
		return new LamsResponse(HttpStatus.BAD_REQUEST.value(), msg);
	}

	@Override
	public LamsResponse changePassword(UserBO userBO) {
		// TODO Auto-generated method stub
		logger.log(Level.INFO, "userBO===>", userBO.toString());
		User user = userMstrRepository.findOne(userBO.getId());
		if (CommonUtils.isObjectNullOrEmpty(user)) {
			logger.log(Level.WARNING, "Invalid User Id while Getting User by Id ==>{0}", userBO.getId());
			return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid UserId. Please try to Relogin!");
		}
		String currPass = DigestUtils.md5DigestAsHex(userBO.getTempPassword().getBytes()).toString();
		if (!user.getPassword().equals(currPass)) {
			return new LamsResponse(HttpStatus.BAD_REQUEST.value(),
					"Current Password does not Match with the Database Record.");
		}

		String newPass = DigestUtils.md5DigestAsHex(userBO.getPassword().getBytes()).toString();
		if (currPass.equals(newPass)) {
			return new LamsResponse(HttpStatus.BAD_REQUEST.value(),
					"Current Password And New Password Must not be Same!");
		}
		user.setTempPassword(userBO.getPassword());
		user.setPassword(newPass);
		user = userMstrRepository.save(user);
		BeanUtils.copyProperties(user, userBO, "password", "tempPassword");
		return new LamsResponse(HttpStatus.OK.value(), "Password Successfully Updated", userBO);
	}

	@Override
	public String generateEncryptString(Date signUp, String email) {
		logger.info("generateEncryptString=================>" + signUp);
		String signUpDate = null;
		SimpleDateFormat print = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		signUpDate = print.format(signUp);
		try {
			String encEmail = encrypt(email);
			String encDate = encrypt(signUpDate);
			return encEmail + "|" + encDate;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while encrypt url for email verification");
			e.printStackTrace();
			return null;
		}
	}

	private String encrypt(String data) throws UnsupportedEncodingException {
		return Base64.getEncoder().encodeToString(data.getBytes("utf-8"));
	}

	private String decrypt(String data) throws UnsupportedEncodingException {
		return new String(Base64.getDecoder().decode(data), "utf-8");
	}

	@Override
	public boolean sendLinkOnMail(User user, String template, String subject, String postUrl) throws Exception {
		logger.log(Level.INFO, "Send Email Verification OBject==>{0}", user.toString());
		NotificationBO notificationBO = new NotificationBO();
		notificationBO.setClientRefId(String.valueOf(1l));
		List<NotificationMainBO> mainBolist = new ArrayList<>();
		NotificationMainBO mainBO = new NotificationMainBO();
		String to[] = { user.getEmail() };
		mainBO.setTo(to);
		mainBO.setContentType(ContentType.TEMPLATE);
		mainBO.setType(NotificationType.EMAIL);
		mainBO.setTemplateName(template);
		Map<String, Object> data = new HashMap<>();
		data.put("user_name", user.getName());
		data.put("emailAddress", user.getEmail());
		String encryptString = generateEncryptString(user.getCreatedDate(), user.getEmail());
		logger.log(Level.INFO, "encryptString===>{0}", new Object[] { encryptString });
		logger.log(Level.INFO, "VerificationURL===>{0}", baseUrl + encryptString);
		data.put("link", baseUrl + postUrl + "/" + encryptString);
		mainBO.setParameters(data);
		mainBO.setSubject(subject);
		mainBolist.add(mainBO);
		notificationBO.setNotifications(mainBolist);
		NotificationResponse response = notificationService.sendNotification(notificationBO);
		if (CommonUtils.isObjectNullOrEmpty(response)) {
			logger.log(Level.SEVERE, "Something went wrong while Sending Email Verification on === >", user.getEmail());
			return false;
		}
		logger.log(Level.INFO, "Response while Sending Email Verification Mail===>{0}",
				new Object[] { response.toString() });

		if (CommonUtils.NotificationProperty.STATUS_SUCCESSFULL.equals(response.getStatus())) {
			return true;
		}
		return false;
	}

	@Override
	public LamsResponse verifyEmail(String link) {
		logger.log(Level.INFO, "Link From Web===>{0}", new Object[] { link });
		String[] arr = link.toString().split("\\|");
		logger.log(Level.INFO, "arrarrarrarrarr====={0}", new Object[] { arr.length });
		if (arr == null || arr.length < 2) {
			return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Link. Please try Again!");
		}
		try {
			String decEmail = decrypt(arr[0]);
			logger.log(Level.INFO, "Descrypted Email=======>{0}", new Object[] { decEmail });
			User user = userMstrRepository.findByEmailAndIsActive(decEmail, true);
			if (CommonUtils.isObjectNullOrEmpty(user)) {
				return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Email Id");
			}
			if (!CommonUtils.isObjectNullOrEmpty(user.getIsEmailVerified()) && user.getIsEmailVerified()) {
				return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Email already Verified.");
			}
			SimpleDateFormat print = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String encDate = encrypt(print.format(user.getCreatedDate()));
			logger.log(Level.INFO, "New Encrypted Date String === {0}", new Object[] { encDate });
			logger.log(Level.INFO, "Old Encrypted Date String === {0}", new Object[] { arr[1] });
			if (!encDate.equals(arr[1])) {
				return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Link. Please try Again!");
			}
			user.setIsEmailVerified(true);
			userMstrRepository.save(user);
			return new LamsResponse(HttpStatus.OK.value(),
					"Your Email " + user.getEmail() + " Successfully Verified !");
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "Error while Descrypting Email Verificaiton Link");
			return new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public LamsResponse sendForgotPasswordLink(String email) throws Exception {
		User user = userMstrRepository.findByEmailAndIsActive(email, true);
		if (CommonUtils.isObjectNullOrEmpty(user)) {
			return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Email Id");
		}
		String subject = "VfinanceS – Forgot Password";
		boolean onMail = sendLinkOnMail(user, NotificationAlias.FORGOT_PASSWORD_EMAIL, subject, "reset-password");
		if (onMail) {
			return new LamsResponse(HttpStatus.OK.value(),
					"We have Sent Link on " + user.getEmail() + " email to Reset Password.");
		}
		return new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.SOMETHING_WENT_WRONG);

	}

	@Override
	public LamsResponse resetPassword(UserBO userBO, String link) {
		logger.log(Level.INFO, "Link From Web===>{0}", new Object[] { link });
		String[] arr = link.toString().split("\\|");
		if (arr == null || arr.length < 2) {
			return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Link. Please try Again!");
		}
		try {
			String decEmail = decrypt(arr[0]);
			logger.log(Level.INFO, "Descrypted Email=======>{0}", new Object[] { decEmail });
			User user = userMstrRepository.findByEmailAndIsActive(decEmail, true);
			if (CommonUtils.isObjectNullOrEmpty(user)) {
				return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Email Id");
			}
			SimpleDateFormat print = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String encDate = encrypt(print.format(user.getCreatedDate()));
			logger.log(Level.INFO, "New Encrypted Date String === {0}", new Object[] { encDate });
			logger.log(Level.INFO, "Old Encrypted Date String === {0}", new Object[] { arr[1] });
			if (!encDate.equals(arr[1])) {
				return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Link. Please try Again!");
			}
			user.setTempPassword(userBO.getPassword());
			user.setPassword(DigestUtils.md5DigestAsHex(userBO.getPassword().getBytes()).toString());
			userMstrRepository.save(user);
			return new LamsResponse(HttpStatus.OK.value(),
					"Your Email " + user.getEmail() + " Password Successfully updated !");
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "Error while Descrypting Email Verificaiton Link");
			return new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

}
