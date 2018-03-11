package com.lams.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.lams.api.domain.Notification;
import com.lams.api.domain.NotificationLog;
import com.lams.api.domain.master.notification.NotificationProvider;
import com.lams.model.bo.SMSRequest;
import com.lams.model.bo.SMSResponse;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.Enums.ContentType;
import com.lams.model.utils.Enums.NotificationType;

import freemarker.template.Configuration;

/**
 * @author Akshay
 *
 */
public class SmsUtils {

	private SMSProperty smsProperty;

	private Configuration fmConfiguration;

	private final Logger log = LoggerFactory.getLogger(SmsUtils.class);

	public SmsUtils(SMSProperty smsProperty, Configuration fmConfiguration) {
		super();
		this.smsProperty = smsProperty;
		this.fmConfiguration = fmConfiguration;
	}

	public String generateMessageFromTemplate(SMSRequest smsRequest) throws Exception {
		String message = null;
		try {
			if (ContentType.TEMPLATE.equals(smsRequest.getContentType())) {
				message = FreeMarkerTemplateUtils.processTemplateIntoString(
						fmConfiguration.getTemplate(smsRequest.getTemplateName()), smsRequest.getParameters());
				return message;
			} else if (ContentType.CONTENT.equals(smsRequest.getContentType())) {
				message = smsRequest.getContent();
				return message;
			} else {
				log.warn("Invalid Content Type");
				throw new Exception("Invalid Content Type");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}

	public SMSResponse sendSMS(SMSRequest smsRequest, String message) throws Exception {
		String recipient = null;
		try {
			recipient = NotificationUtils.convertTOCommaSpe(smsRequest.getPhoneNumber());
			NotificationUtils notificationUtils = new NotificationUtils();
			return notificationUtils.sendSMS(recipient, message, smsProperty.getUsername(), smsProperty.getPassword(),
					smsProperty.getOriginator(), smsProperty.getRequestURL());
		} catch (Exception e) {
			SMSResponse response = new SMSResponse();
			response.setSmsMessage(message);
			response.setMessage("SMS Send Failure  to number :" + recipient);
			response.setStatus(CommonUtils.NotificationProperty.STATUS_FAILURE);
			return response;
		}
	}

	public Notification generateNotificationToSave(SMSResponse response, NotificationProvider notificationProvider,
			Long status, SMSRequest request, Long resentCount) {

		NotificationLog notificationSmsLog = new NotificationLog();
		notificationSmsLog.setCreatedBy(1L);
		notificationSmsLog.setCreatedDate(CommonUtils.getCurrentTimeStamp());
		notificationSmsLog.setIsActive(true);
		notificationSmsLog.setModifiedBy(1L);
		notificationSmsLog.setModifiedDate(CommonUtils.getCurrentTimeStamp());
		notificationSmsLog.setLogMessage(
				response.getMessage() + " : " + NotificationUtils.convertTOCommaSpe(request.getPhoneNumber()));
		notificationSmsLog
				.setLogResponse(response.getStatus() != null ? response.getStatus().toString() : status.toString());
		notificationSmsLog.setStatus(status);
		notificationSmsLog.setMobile(NotificationUtils.convertTOCommaSpe(request.getPhoneNumber()));
		notificationSmsLog.setResentCount(resentCount);
		if (status != CommonUtils.NotificationProperty.STATUS_SUCCESSFULL) {
			notificationSmsLog.setIsSent(CommonUtils.NotificationProperty.SUCCESSFUL);
		} else {
			notificationSmsLog.setIsSent(CommonUtils.NotificationProperty.FAILURE);
		}
		Notification notification = new Notification();
		notification.setCreatedBy(1L);
		notification.setCreatedDate(CommonUtils.getCurrentTimeStamp());
		notification.setIsActive(true);
		notification.setModifiedBy(1L);
		notification.setModifiedDate(CommonUtils.getCurrentTimeStamp());
		notification.setMessage(response.getSmsMessage());
		notification.setProvider(notificationProvider);
		notification.setNotificationType(NotificationType.SMS.getValue());
		notification.setUserId(1L);
		notification.setUserId(request.getUserId());
		return notification;
	}

	public SMSResponse sendUnsentSMS(String recipent, String message) throws Exception {
		log.info("In sending SMS ");
		try {
			NotificationUtils notificationUtils = new NotificationUtils();
			return notificationUtils.sendSMS(recipent, message, smsProperty.getUsername(), smsProperty.getPassword(),
					smsProperty.getOriginator(), smsProperty.getRequestURL());
		} catch (Exception e) {
			e.printStackTrace();
			SMSResponse response = new SMSResponse();
			response.setSmsMessage(message);
			response.setMessage("SMS Send Failure  to number :" + recipent);
			response.setStatus(CommonUtils.NotificationProperty.STATUS_SMS_SENDING_FAILURE);
			return response;
		}

	}

}
