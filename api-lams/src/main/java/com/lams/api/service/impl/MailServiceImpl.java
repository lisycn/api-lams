package com.lams.api.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lams.api.boot.NotificationContext;
import com.lams.api.domain.Notification;
import com.lams.api.domain.master.notification.NotificationProvider;
import com.lams.api.repository.NotificationRepository;
import com.lams.api.repository.master.notification.NotificationProviderRepository;
import com.lams.api.service.MailService;
import com.lams.api.utils.MailUtils;
import com.lams.api.utils.NotificationUtils;
import com.lams.model.bo.EmailResponse;
import com.lams.model.bo.NotificationMainBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.Enums.ContentType;
import com.lams.model.utils.Enums.NotificationType;

import freemarker.template.Configuration;

/**
 * @author Akshay
 *
 */
@Transactional
@Service
public class MailServiceImpl implements MailService {

	protected static final String EMAIL = "email.ftl";

	private final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);
	// @Autowired
	// private VelocityEngine velocityEngine;

	@Autowired
	private Configuration fmConfiguration;

	@Autowired
	private NotificationProviderRepository repository;

	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	public EmailResponse sendEmail(NotificationMainBO emailRequest) throws Exception {
		EmailResponse response = new EmailResponse();
		String message = null;
		MailUtils utils = new MailUtils(NotificationContext.javaMailSender, fmConfiguration);
		try {
			NotificationProvider provider = repository
					.findByNotificationTypeAndIsActive(NotificationType.EMAIL.getValue(), true);
			message = utils.generateMessageFromTemplate(emailRequest);
			response.setEmailMessage(message);
			response = utils.sendEmail(emailRequest);

			Notification notification = utils.generateNotificationToSave(response, provider,
					CommonUtils.NotificationProperty.STATUS_SUCCESSFULL, emailRequest, 0L);

			response.setMessage("Notification Sent and saved");
			response.setStatus(CommonUtils.NotificationProperty.STATUS_SUCCESSFULL);
			notificationRepository.save(notification);
			log.info("Notification saved for email Id : " + NotificationUtils.convertTOCommaSpe(emailRequest.getTo()));
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setEmailMessage(message);
			response.setMessage(e.getMessage());
			response.setStatus(CommonUtils.NotificationProperty.STATUS_NOTIFICATION_SENDING_FAILURE);
			Notification notification = utils.generateNotificationToSave(response,
					repository.findByNotificationTypeAndIsActive(NotificationType.EMAIL.getValue(), true),
					CommonUtils.NotificationProperty.STATUS_NOTIFICATION_SENDING_FAILURE, emailRequest, 0L);

			notificationRepository.save(notification);
			response.setMessage(e.getMessage());
			response.setStatus(CommonUtils.NotificationProperty.STATUS_NOTIFICATION_SENDING_FAILURE);
			return response;

		}

	}

	@Override
	public void sendUnsentEmail() {
		log.info("Entry in sendUnsentEmail");
		// List<Notification> notifications =
		// notificationRepository.getUnsentEmail(200L, NOTIFICATION_TYPE_ID);
		// MailUtils utils = new MailUtils(NotificationContext.javaMailSender,
		// fmConfiguration);
		// for (Notification notification : notifications) {
		// for (NotificationLog emailLog : notification.getNotificationLogs()) {
		// EmailResponse response = new EmailResponse();
		// try {
		// response = utils.sendUnsentEmail(emailLog.getToEmail(),
		// emailLog.getSubject(),
		// notification.getMessage());
		//
		// emailLog.setModifiedDate(CommonUtils.getCurrentTimeStamp());
		// emailLog.setNotificationEmailLogMessage(response.getMessage());
		// emailLog.setNotificationEmailLogResponse(
		// response.getStatus() != null ? response.getStatus().toString() : null);
		// emailLog.setStatus(response.getStatus());
		// emailLog.setResentCount(emailLog.getResentCount() + 1);
		// emailLog.setIsSent(NotificationProperty.SUCCESSFUL);
		// } catch (Exception e) {
		// emailLog.setModifiedDate(CommonUtils.getCurrentTimeStamp());
		// emailLog.setNotificationEmailLogMessage(response.getMessage());
		// emailLog.setNotificationEmailLogResponse(
		// response.getStatus() != null ? response.getStatus().toString() : null);
		// emailLog.setStatus(response.getStatus());
		// emailLog.setResentCount(emailLog.getResentCount() + 1);
		// emailLog.setIsSent(NotificationProperty.FAILURE);
		// e.printStackTrace();
		// }
		// }
		//
		// }
		//
		// notificationRepository.save(notifications);
		log.info("Exit from sendUnsentEmail");
	}

	@Override
	public void sendFailureEmailForSMS(String exception) {
		try {
			MailUtils mailUtils = new MailUtils(NotificationContext.javaMailSender, fmConfiguration);
			NotificationMainBO request = new NotificationMainBO();
			String tokens = NotificationContext.FAILURE_INFO_EMAIL_TO;
			String[] emailTo = tokens.split(",", -1);

			request.setTo(emailTo);
			request.setSubject(
					NotificationContext.FAILURE_INFO_EMAIL_SUBJECT + " at " + CommonUtils.getCurrentTimeStamp());
			request.setTemplateName(NotificationContext.FAILURE_INFO_EMAIL_TEMPLATE);
			request.setParameters(null);
			request.setContentType(ContentType.TEMPLATE);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("exception_notification", exception);
			request.setParameters(parameters);
			mailUtils.sendEmail(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
