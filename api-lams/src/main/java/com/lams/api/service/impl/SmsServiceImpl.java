package com.lams.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lams.api.boot.NotificationContext;
import com.lams.api.domain.Notification;
import com.lams.api.domain.master.notification.NotificationProvider;
import com.lams.api.repository.NotificationRepository;
import com.lams.api.repository.master.notification.NotificationProviderRepository;
import com.lams.api.service.SmsService;
import com.lams.api.utils.NotificationUtils;
import com.lams.api.utils.SmsUtils;
import com.lams.model.bo.NotificationMainBO;
import com.lams.model.bo.SMSResponse;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.CommonUtils.NotificationProperty;
import com.lams.model.utils.Enums.NotificationType;

import freemarker.template.Configuration;

/**
 * @author Akshay
 *
 */
@Service
public class SmsServiceImpl implements SmsService {

	private final Logger log = LoggerFactory.getLogger(SmsServiceImpl.class);

	@Autowired
	private NotificationProviderRepository repository;

	@Autowired
	private Configuration fmConfiguration;

	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	public SMSResponse sendSMS(NotificationMainBO smsRequest) throws Exception {

		SMSResponse response = null;
		SmsUtils utils = new SmsUtils(NotificationContext.smsProperty, fmConfiguration);
		String message = null;
		try {
			NotificationProvider provider = repository
					.findByNotificationTypeAndIsActive(NotificationType.SMS.getValue(), true);
			message = utils.generateMessageFromTemplate(smsRequest);
			response = utils.sendSMS(smsRequest, message);

			Notification notification = utils.generateNotificationToSave(response, provider,
					CommonUtils.NotificationProperty.STATUS_SUCCESSFULL, smsRequest, 0L);

			response.setMessage("Notification Sent and saved");
			response.setStatus(CommonUtils.NotificationProperty.STATUS_SUCCESSFULL);
			notificationRepository.save(notification);
			log.info("Notification Sent and saved for SMS : "
					+ NotificationUtils.convertTOCommaSpe(smsRequest.getPhoneNumber()));

			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setSmsMessage(message);
			response.setMessage(
					"SMS Send Failure  to number :" + NotificationUtils.convertTOCommaSpe(smsRequest.getPhoneNumber()));
			response.setStatus(NotificationProperty.STATUS_SMS_SENDING_FAILURE);
			Notification notification = utils.generateNotificationToSave(response,
					repository.findByNotificationTypeAndIsActive(NotificationType.SMS.getValue(), true),
					CommonUtils.NotificationProperty.STATUS_NOTIFICATION_SENDING_FAILURE, smsRequest, 0L);

			notificationRepository.save(notification);
			log.error("Notification was not Sent or saved for email Id : " + smsRequest.getPhoneNumber()
					+ " caused Exception :  " + e.getMessage());
			throw new Exception("Notification was not Sent or saved for email Id "
					+ NotificationUtils.convertTOCommaSpe(smsRequest.getPhoneNumber()));
		}

	}

}
