package com.lams.api.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
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
import com.lams.api.domain.master.CityMstr;
import com.lams.api.domain.master.CountryMstr;
import com.lams.api.domain.master.StateMstr;
import com.lams.api.repository.LenderApplicationMappingRepository;
import com.lams.api.repository.UserMstrRepository;
import com.lams.api.repository.master.AddressMstrRepository;
import com.lams.api.repository.master.BankMstrRepository;
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
import com.lams.model.bo.NotificationBO;
import com.lams.model.bo.NotificationMainBO;
import com.lams.model.bo.OTPRequest;
import com.lams.model.bo.UserBO;
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
	private BankMstrRepository bankMstrRepository;

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

	@Value("${com.lams.login.url}")
	private String loginUrl;

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
					return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "User is inactive");
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
				user.setIsEmailVerified(
						!CommonUtils.isObjectNullOrEmpty(userBO.getIsEmailVerified()) ? userBO.getIsEmailVerified()
								: true);
				user.setIsOtpVerified(
						!CommonUtils.isObjectNullOrEmpty(userBO.getIsOtpVerified()) ? userBO.getIsOtpVerified() : true);
				if (CommonUtils.isObjectNullOrEmpty(userBO.getIsActive())) {
					user.setIsActive(false);
				} else {
					user.setIsActive(userBO.getIsActive());
				}

				if (!CommonUtils.isObjectNullOrEmpty(userBO.getBank())
						&& !CommonUtils.isObjectNullOrEmpty(userBO.getBank().getId())) {
					user.setBank(new BankMstr(userBO.getBank().getId()));
				}
			} else if (!CommonUtils.isObjectNullOrEmpty(userType) && userType.equals(Enums.UserType.BORROWER)) {
				user.setIsEmailVerified(false);
				user.setIsOtpVerified(true);
			}
		}
		user.setPassword(DigestUtils.md5DigestAsHex(userBO.getPassword().getBytes()).toString());
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
				&& Enums.UserType.BORROWER.getId() == user.getUserType().intValue()) {
			boolean sendOtp = sendOtp(user, OTPType.REGISTRATION, NotificationAlias.SMS);
			logger.log(Level.INFO, "Is Otp Sent===>{0}", sendOtp);
			if (sendOtp) {
				logger.log(Level.INFO, "OTP Sent For Mobile===>{0}=====Email=={1}",
						new Object[] { user.getMobile(), user.getEmail() });
				userBO.setId(user.getId());
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
			} else {
				// Set Lender Applications
				List<ApplicationTypeMstr> list = lenderApplicationMappingRepository
						.getApplicationTypesByUserIdAndIsActive(user.getId(), true);
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
			if (addressMstr.getAddType() == CommonUtils.AddressType.PERMANENT) {
				userBo.setPermanentAdd(addressBO);
			} else if (addressMstr.getAddType() == CommonUtils.AddressType.COMMUNICATION) {
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
		if (!CommonUtils.isObjectNullOrEmpty(userBO.getPermanentAdd())) {
			addressService.saveAddress(userBO.getPermanentAdd(), userBO.getId(), CommonUtils.AddressType.PERMANENT);
		}
		if (!CommonUtils.isObjectNullOrEmpty(userBO.getCommunicationAdd())) {
			addressService.saveAddress(userBO.getCommunicationAdd(), userBO.getId(),
					CommonUtils.AddressType.COMMUNICATION);
		}

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

	private boolean sendOtp(User user, OTPType type, String templateName) {
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
			return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Request");
		}

		OTPRequest otpRequest = new OTPRequest();
		otpRequest.setEmailId(user.getEmail());
		otpRequest.setMasterId(user.getId());
		otpRequest.setMobileNo(user.getMobile());
		otpRequest.setRequestType(type.getId());
		otpRequest.setOtp(userBO.getOtp());
		boolean isVerified = oTPLoggingService.verifyOTP(otpRequest);
		if (isVerified) {
			new LamsResponse(HttpStatus.OK.value(), "Mobile No " + user.getMobile() + "SuccessFully Verified");
		}
		return new LamsResponse(HttpStatus.OK.value(), "Invalid or Expired OTP.");
	}

	@Override
	public LamsResponse resendOtp(UserBO userBO, OTPType type, String templateName) {
		User user = userMstrRepository.findOne(userBO.getId());
		if (CommonUtils.isObjectNullOrEmpty(user)) {
			return new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Request");
		}
		boolean sendOtp = sendOtp(user, type, templateName);
		String msg = null;
		if (sendOtp) {
			msg = "Otp Successfully Sent on " + user.getMobile();
			return new LamsResponse(HttpStatus.OK.value(), msg);
		}
		msg = CommonUtils.SOMETHING_WENT_WRONG + " Please try again after Sometime!";
		return new LamsResponse(HttpStatus.OK.value(), msg);
	}

}
