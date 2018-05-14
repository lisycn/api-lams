package com.lams.api.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lams.api.service.UserMstrService;
import com.lams.model.bo.EmailBo;
import com.lams.model.bo.LamsResponse;
import com.lams.model.bo.UserBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.Enums.OTPType;

//@CrossOrigin(origins = {"http://localhost:*","http://localhost:*"})
@RestController
public class UserMstrController {

	public final static Logger logger = Logger.getLogger(UserMstrController.class.getName());

	@Autowired
	private UserMstrService userMstrService;

	@RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> registration(@RequestBody UserBO userBO) {
		logger.info("Enter in registration process");
		if (CommonUtils.isObjectNullOrEmpty(userBO.getEmail())) {
			logger.info("Email is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Email is Null Or Empty"), HttpStatus.OK);
		}

		if (CommonUtils.isObjectNullOrEmpty(userBO.getMobile())) {
			logger.info("Mobile is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Mobile is Null Or Empty"), HttpStatus.OK);
		}

		if (CommonUtils.isObjectNullOrEmpty(userBO.getPassword())) {
			logger.info("Password is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Password is Null Or Empty"), HttpStatus.OK);
		}

		try {
			return new ResponseEntity<LamsResponse>(userMstrService.registration(userBO, null), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while registrion ---------------->" + userBO.getEmail());
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getUsersByType/{userType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getUsersByType(@PathVariable(value = "userType") Long userType) {
		logger.info("Enter in get users by user type");
		try {
			List<UserBO> userList = userMstrService.getUsersByUserType(userType);
			logger.info("Successfully get users data by type id ------------>" + userType);
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.OK.value(), "Successfully get userList", userList), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while get users by user type");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/get_user_details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getLoggedInUserDetails(HttpServletRequest request) {
		logger.info("=======>> Enter in get users by user type");
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		try {
			if (CommonUtils.isObjectNullOrEmpty(userId)) {
				logger.log(Level.WARNING, "UserId must not be null while getting Loggedin User Details ------------>{}",
						userId);
			}
			UserBO userData = userMstrService.getUserById(userId);
			logger.log(Level.INFO, "Successfully get Logged in User Details ------------>{}", userData.toString());

			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.OK.value(), "Success", userData),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while Getting user Details based on UserId===>{}", userId);
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/get_user_details/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getLoggedInUserDetailsById(@PathVariable("userId")Long userId,HttpServletRequest request) {
		logger.info("=======>> Enter in get users by user type");
		try {
			if (CommonUtils.isObjectNullOrEmpty(userId)) {
				logger.log(Level.WARNING, "UserId must not be null while getting User Details By IDs------------>{}",
						userId);
			}
			UserBO userData = userMstrService.getUserById(userId);
			logger.log(Level.INFO, "Successfully get Logged in User Details ------------>{}", userData.toString());

			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.OK.value(), "Success", userData),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while Getting user Details based on UserId===>{}", userId);
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/update_user_details", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> updateUserDetails(@RequestBody UserBO userBO, HttpServletRequest request) {
		logger.info("Enter in get users by user type");
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		try {
			if (CommonUtils.isObjectNullOrEmpty(userId)) {
				logger.log(Level.WARNING, "UserId must not be null while Updating  User Details ------------>{}",
						userId);
			}
			userBO.setId(userId);
			return new ResponseEntity<LamsResponse>(userMstrService.updateUserDetails(userBO), HttpStatus.OK);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while updating User Details based on UserId===>{}", userId);
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/update_lender_details", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> updateLenderDetails(@RequestBody UserBO userBO, HttpServletRequest request) {
		logger.info("Enter in update lender details process");

		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		if (CommonUtils.isObjectNullOrEmpty(userBO.getEmail())) {
			logger.info("Email is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Email is Null Or Empty"), HttpStatus.OK);
		}

		if (CommonUtils.isObjectNullOrEmpty(userBO.getMobile())) {
			logger.info("Mobile is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Mobile is Null Or Empty"), HttpStatus.OK);
		}

		if (CommonUtils.isObjectNullOrEmpty(userBO.getPassword())) {
			logger.info("Password is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Password is Null Or Empty"), HttpStatus.OK);
		}

		try {
			return new ResponseEntity<LamsResponse>(userMstrService.registration(userBO, userId), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while update lender details ---------------->" + userBO.getEmail());
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/save_cp_borrower", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> saveCPBorrower(@RequestBody UserBO userBO,
			HttpServletRequest httpServletRequest) {
		Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		if (CommonUtils.isObjectNullOrEmpty(userId)) {
			logger.info("User Id Null Or Empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "UnAuthorized! Please try again to ReLogin"),
					HttpStatus.OK);
		}
		
		try {
			return new ResponseEntity<LamsResponse>(userMstrService.addCpBorrower(userBO, userId), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while Saving Borrower From Channel Partnert----------------> For UserId===" + userId);
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/get_cp_users/{userType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getCPBorrower(@PathVariable("userType")Long userType ,HttpServletRequest httpServletRequest) {
		Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		if (CommonUtils.isObjectNullOrEmpty(userId)) {
			logger.info("User Id Null Or Empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "UnAuthorized! Please try again to ReLogin"),
					HttpStatus.OK);
		}
		
		try {
			return new ResponseEntity<LamsResponse>(userMstrService.getCpUsersByUserType(userId, userType), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while Saving Borrower From Channel Partnert----------------> For UserId===" + userId);
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/get_cp_users/{userType}/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getCPBorrowerForAdmin(@PathVariable("userType")Long userType ,@PathVariable("userId")Long userId, HttpServletRequest httpServletRequest) {
		if (CommonUtils.isObjectNullOrEmpty(userId)) {
			logger.info("User Id Null Or Empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "UnAuthorized! Please try again to ReLogin"),
					HttpStatus.OK);
		}
		
		try {
			return new ResponseEntity<LamsResponse>(userMstrService.getCpUsersByUserType(userId, userType), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while Saving Borrower From Channel Partnert----------------> For UserId===" + userId);
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}
	

	@RequestMapping(value = "/invite_lender", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> inviteLender(@RequestBody UserBO userBO, HttpServletRequest request) {
		logger.info("Enter in update lender details process");

		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		if (CommonUtils.isObjectNullOrEmpty(userBO.getEmail())) {
			logger.info("Email is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Email Must not be Empty"), HttpStatus.OK);
		}

		if (CommonUtils.isObjectNullOrEmpty(userBO.getMobile())) {
			logger.info("Mobile is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Mobile Must not be Empty"), HttpStatus.OK);
		}

		try {
			userBO = userMstrService.inviteLender(userBO, userId);
			logger.log(Level.INFO, "Response After Invited to Lender===>{0}", userBO.toString());
			LamsResponse lamsResponse = new LamsResponse(HttpStatus.OK.value(), "Invitation successfully sent.", userBO);
			return new ResponseEntity<LamsResponse>(lamsResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while update lender details ---------------->" + userBO.getEmail());
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/verify_otp/{type}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> verifyOTP(@RequestBody UserBO userBO, @PathVariable("type") String typeCode,
			HttpServletRequest request) {
		logger.info("Enter in update lender details process");

		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		if (CommonUtils.isObjectNullOrEmpty(userBO.getId())) {
			userBO.setId(userId);
		}

		if (CommonUtils.isObjectNullOrEmpty(userBO.getId())) {
			logger.info("ID is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.INVALID_REQUEST), HttpStatus.OK);
		}

		if (CommonUtils.isObjectNullOrEmpty(userBO.getOtp())) {
			logger.info("OTP is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "OTP Must not be Empty."), HttpStatus.OK);
		}

		if (CommonUtils.isObjectNullOrEmpty(typeCode)) {
			logger.info("TYPE is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.INVALID_REQUEST), HttpStatus.OK);
		}

		OTPType otpType = OTPType.getType(typeCode);
		if (CommonUtils.isObjectNullOrEmpty(typeCode)) {
			logger.info("otpType Enum Object is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.INVALID_REQUEST), HttpStatus.OK);
		}
		try {
			return new ResponseEntity<LamsResponse>(userMstrService.verifyOTP(userBO, otpType), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while update lender details ---------------->" + userBO.getEmail());
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/resend_otp/{type}/{templateName}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> resendOTP(@RequestBody UserBO userBO, @PathVariable("type") String typeCode,
			@PathVariable("templateName") String templateName, HttpServletRequest request) {
		logger.info("Enter in resendOTP");

		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		if (CommonUtils.isObjectNullOrEmpty(userBO.getId())) {
			userBO.setId(userId);
		}

		if (CommonUtils.isObjectNullOrEmpty(userBO.getId())) {
			logger.info("UsesrId is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.INVALID_REQUEST), HttpStatus.OK);
		}

		if (CommonUtils.isObjectNullOrEmpty(templateName)) {
			logger.info("templateName is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.INVALID_REQUEST), HttpStatus.OK);
		}

		if (CommonUtils.isObjectNullOrEmpty(typeCode)) {
			logger.info("TYPE is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.INVALID_REQUEST), HttpStatus.OK);
		}

		OTPType otpType = OTPType.getType(typeCode);
		if (CommonUtils.isObjectNullOrEmpty(typeCode)) {
			logger.info("otpType Enum Object is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.INVALID_REQUEST), HttpStatus.OK);
		}
		try {
			return new ResponseEntity<LamsResponse>(userMstrService.resendOtp(userBO, otpType, templateName),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while update lender details ---------------->" + userBO.getEmail());
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/change_password", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> changePassword(@RequestBody UserBO userBO, HttpServletRequest request) {
		logger.info("Enter in resendOTP");

		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		if (CommonUtils.isObjectNullOrEmpty(userBO.getId())) {
			userBO.setId(userId);
		}

		if (CommonUtils.isObjectNullOrEmpty(userBO.getId())) {
			logger.info("UsesrId is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.INVALID_REQUEST), HttpStatus.OK);
		}

		if (CommonUtils.isObjectNullOrEmpty(userBO.getTempPassword())) {
			logger.info("Temp (Current) password is NUll");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Please Enter Current Password."), HttpStatus.OK);
		}

		if (CommonUtils.isObjectNullOrEmpty(userBO.getPassword())) {
			logger.info("New password is NUll");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Please Enter New Password."), HttpStatus.OK);
		}
		try {
			return new ResponseEntity<LamsResponse>(userMstrService.changePassword(userBO), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while update lender details ---------------->" + userBO.getEmail());
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/verify_email/{link}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> verifyEmail(@PathVariable("link") String link, HttpServletRequest request) {
		logger.info("Enter in verifyAccount");
		try {
			if (CommonUtils.isObjectNullOrEmpty(link)) {
				logger.info("Link is NUll");
				return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.BAD_REQUEST.value(),
						CommonUtils.INVALID_REQUEST + " Please try Again!"), HttpStatus.OK);
			}
			logger.log(Level.INFO, "Response After Invited to Lender===>{0}", new Object[] { link });
			return new ResponseEntity<LamsResponse>(userMstrService.verifyEmail(link), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while Verifying Email ---------------->{0}" + new Object[] { link });
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/send_link", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> sendLink(@RequestBody UserBO userBO, HttpServletRequest request) {
		logger.info("Enter in sendLink");
		try {
			if (CommonUtils.isObjectNullOrEmpty(userBO.getEmail())) {
				logger.info("email is NUll");
				return new ResponseEntity<LamsResponse>(
						new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Please Enter Email."), HttpStatus.OK);
			}
			logger.log(Level.INFO, "Email for Forgot Password===>{0}", new Object[] { userBO.getEmail() });
			return new ResponseEntity<LamsResponse>(userMstrService.sendForgotPasswordLink(userBO.getEmail()), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while Sending Link on Forgot Password ---------------->{0}"
					+ new Object[] { userBO.getEmail()});
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/reset_password/{link}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> resetPassword(@RequestBody UserBO userBO,@PathVariable("link")String link, HttpServletRequest request) {
		logger.info("Enter in resetPassword");
		try {
			if (CommonUtils.isObjectNullOrEmpty(link)) {
				logger.info("Link is NUll");
				return new ResponseEntity<LamsResponse>(
						new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Email"), HttpStatus.OK);
			}
			
			if (CommonUtils.isObjectNullOrEmpty(userBO.getPassword())) {
				logger.info("Password is NUll");
				return new ResponseEntity<LamsResponse>(
						new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Password must not be Empty."), HttpStatus.OK);
			}
			logger.log(Level.INFO, "Link for Reset Password===>{0}", new Object[] { link });
			return new ResponseEntity<LamsResponse>(userMstrService.resetPassword(userBO,link), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while updating Password on Link ---------------->{0}"
					+ new Object[] { link});
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value = "/get_user_details_by_id/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getUserDetailsById(@PathVariable Long userId, HttpServletRequest request) {
//		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		try {
			UserBO userData = userMstrService.getUserById(userId);
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.OK.value(), "Success", userData),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while Getting user Details based on UserId===>{}", userId);
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/sendMailRequestFromSite", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> sendMailRequestFromSite(@RequestBody EmailBo emailBO, HttpServletRequest request) {
		logger.info("In Sending mail from wesite");
		try {
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.OK.value(), "Success", 
					userMstrService.sendMailRequestFromSite(emailBO.getEmail(), emailBO.getName(), emailBO.getMsg(), emailBO.getContact())),
					HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}
	
	
}
