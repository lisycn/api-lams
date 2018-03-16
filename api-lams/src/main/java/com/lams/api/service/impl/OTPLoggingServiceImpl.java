package com.lams.api.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lams.api.domain.OtpLoggingDetail;
import com.lams.api.domain.master.OtpTypeMaster;
import com.lams.api.repository.OTPLoggingRepository;
import com.lams.api.service.OTPLoggingService;
import com.lams.api.service.SmsService;
import com.lams.api.utils.ServiceUtils;
import com.lams.model.bo.NotificationMainBO;
import com.lams.model.bo.OTPRequest;
import com.lams.model.bo.SMSResponse;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.Enums.ContentType;
import com.lams.model.utils.Enums.NotificationType;

@Service
@Transactional
public class OTPLoggingServiceImpl implements OTPLoggingService {

	private static Logger logger = Logger.getLogger(OTPLoggingServiceImpl.class.getName());

	@Value("${lams.otp.expiry-time}")
	private String otpExpiryTime;

	@Value("${lams.otp.digits}")
	private String otpDigits;

	@Autowired
	private SmsService smsService;

	@Autowired
	private OTPLoggingRepository oTPLoggingRepository;

	/**
	 * Send OTP
	 */

	@Override
	public boolean sendOTP(OTPRequest request) {
		// INACTIVE previous OTP if user has send OTP request twice or more than
		// twice.
		try {
			oTPLoggingRepository.inActivePreviousOTP(request.getRequestType(), request.getMasterId(),
					request.getMobileNo());

			// saving new OTP
			OtpLoggingDetail loggingDetails = new OtpLoggingDetail();
			String otp = getOTP(request);
			logger.info("Generated OTP==>" + otp);
			loggingDetails.setOtp(otp);
			loggingDetails.setMasterId(request.getMasterId());
			loggingDetails.setMobileNo(request.getMobileNo());
			loggingDetails.setEmail(request.getEmailId());
			loggingDetails.setType(new OtpTypeMaster(request.getRequestType()));
			loggingDetails.setCreatedDate(new Date());
			loggingDetails.setCreatedBy(request.getMasterId());
			loggingDetails.setIsActive(true);
			loggingDetails.setExpired(false);
			loggingDetails.setVerified(false);
			oTPLoggingRepository.save(loggingDetails);
			// send OTP to Mobile

			String[] to = { "91" + loggingDetails.getMobileNo() };
			NotificationMainBO notificationRequest = new NotificationMainBO();
			notificationRequest.setContentType(ContentType.TEMPLATE);
			notificationRequest.setTemplateName(request.getTemplateName());
			notificationRequest.setPhoneNumber(to);
			notificationRequest.setType(NotificationType.SMS);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("emailAddress", loggingDetails.getEmail());
			parameters.put("otp", otp);
			notificationRequest.setParameters(parameters);
			SMSResponse response = smsService.sendSMS(notificationRequest);
			logger.info("OTP For Mobile==>" + loggingDetails.getMobileNo());
			return (response != null && response.getStatus() == CommonUtils.NotificationProperty.STATUS_SUCCESSFULL);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "Error while Sending OTP on Mobile==={0} and EMail====>{1}",
					new Object[] { request.getMobileNo(), request.getEmailId() });
			return false;
		}

	}

	/**
	 * generate and check OTP exists or not.
	 */
	@Override
	public String getOTP(OTPRequest request) {
		// TODO Auto-generated method stub
		Integer noOfDigits = Integer.parseInt(otpDigits);
		String otp = ServiceUtils.generateOTP(noOfDigits);
		request.setOtp(otp);
		if (isOTPExists(request)) {
			getOTP(request);
		}
		return otp;
	}

	/**
	 * check whether the OTP is already exists or not.
	 */
	@Override
	public boolean isOTPExists(OTPRequest request) {
		// TODO Auto-generated method stub
		return oTPLoggingRepository.getCountOfValidOTP(request.getOtp(), request.getRequestType(),
				request.getMasterId(), request.getMobileNo()) > 0;
	}

	/**
	 * verify otp and update the status of OTP like expired or verified
	 */
	@Override
	public boolean verifyOTP(OTPRequest request) throws ParseException {
		// TODO Auto-generated method stub
		boolean isExists = isOTPExists(request);
		if (!isExists) {
			return false;
		}
		OtpLoggingDetail otpDetails = oTPLoggingRepository.getOTPDetails(request.getOtp(), request.getRequestType(),
				request.getMasterId(), request.getMobileNo());
		if (otpDetails == null) {
			return false;
		}

		long expiryMinutes = Long.parseLong(otpExpiryTime);
		long originalMinutes = CommonUtils.getDateDifference(new Date(), otpDetails.getCreatedDate(),
				CommonUtils.DateTime.MINUTES);

		boolean result = true;
		if (originalMinutes > expiryMinutes) {
			otpDetails.setExpired(true);
			result = false;
		} else {
			otpDetails.setVerified(true);
		}
		otpDetails.setModifiedDate(new Date());
		otpDetails.setModifiedBy(request.getMasterId());
		oTPLoggingRepository.save(otpDetails);
		return result;
	}
}
