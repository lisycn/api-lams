package com.lams.api.utils;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.lams.api.domain.Notification;
import com.lams.api.domain.NotificationLog;
import com.lams.api.domain.master.notification.NotificationProvider;
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
public class MailUtils {

	private JavaMailSender javaMailSender;

	private Configuration fmConfiguration;

	private final Logger log = LoggerFactory.getLogger(MailUtils.class);

	public MailUtils(JavaMailSender javaMailSender, Configuration fmConfiguration) {
		super();
		this.javaMailSender = javaMailSender;
		this.fmConfiguration = fmConfiguration;
	}

	public String generateMessageFromTemplate(NotificationMainBO emailRequest) throws Exception {
		String text = null;
		try {
			if (ContentType.TEMPLATE.equals(emailRequest.getContentType())) {
				text = FreeMarkerTemplateUtils.processTemplateIntoString(
						fmConfiguration.getTemplate(emailRequest.getTemplateName()), emailRequest.getParameters());
				return text;
			} else if (ContentType.CONTENT.equals(emailRequest.getContentType())) {
				text = emailRequest.getContent();
				return text;
			} else {
				log.error("Content Selection Error");
				throw new Exception("Content Selection Error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}

	public EmailResponse sendEmail(NotificationMainBO emailRequest) throws Exception {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		String text = null;
		try {
			text = generateMessageFromTemplate(emailRequest);
			NotificationUtils notificationUtils = new NotificationUtils();
			return notificationUtils.sendEmail(mimeMessage, javaMailSender, emailRequest, text);

		} catch (Exception e) {
			e.printStackTrace();
			EmailResponse response = new EmailResponse();
			response.setEmailMessage(text);
			response.setMessage("Failure While Sending Email");
			response.setStatus(CommonUtils.NotificationProperty.STATUS_FAILURE);
			return response;
		}
	}

	public Notification generateNotificationToSave(EmailResponse response, NotificationProvider notificationProvider,
			Long status, NotificationMainBO request, Long recentCount) {
		NotificationLog notificationLog = new NotificationLog();
		notificationLog.setCreatedBy(1L);
		notificationLog.setCreatedDate(CommonUtils.getCurrentTimeStamp());
		notificationLog.setIsActive(true);
		notificationLog.setModifiedBy(1L);
		notificationLog.setModifiedDate(CommonUtils.getCurrentTimeStamp());
		notificationLog.setLogMessage(response.getMessage());
		notificationLog
				.setLogResponse(response.getStatus() != null ? response.getStatus().toString() : status.toString());
		notificationLog.setStatus(status);
		notificationLog.setSubject(request.getSubject());
		notificationLog.setToEmail(NotificationUtils.convertTOCommaSpe(request.getTo()));
		notificationLog.setResentCount(recentCount);
		if (status != CommonUtils.NotificationProperty.STATUS_SUCCESSFULL) {
			notificationLog.setIsSent(CommonUtils.NotificationProperty.SUCCESSFUL);
		} else {
			notificationLog.setIsSent(CommonUtils.NotificationProperty.FAILURE);
		}
		
		Notification notification = new Notification();
		notification.setCreatedBy(1L);
		notification.setCreatedDate(CommonUtils.getCurrentTimeStamp());
		notification.setIsActive(true);
		notification.setModifiedBy(1L);
		notification.setModifiedDate(CommonUtils.getCurrentTimeStamp());
		notification.setMessage(response.getEmailMessage());
		notification.setProvider(notificationProvider);
		notification.setNotificationType(NotificationType.EMAIL.getValue());
		notification.setUserId(request.getUserId());
		notification.addNotificationLog(notificationLog);
		return notification;
	}

	public EmailResponse sendUnsentEmail(String toEmail, String subject, String text) {
		log.info("In sending Email");
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		NotificationMainBO emailRequest = new NotificationMainBO();

		String[] toEmails = toEmail.split(",", -1);
		emailRequest.setTo(toEmails);
		emailRequest.setSubject(subject);
		// emailRequest.setFromEmail(javaMailSender.);
		NotificationUtils notificationUtils = new NotificationUtils();
		try {
			return notificationUtils.sendEmail(mimeMessage, javaMailSender, emailRequest, text);
		} catch (Exception e) {
			e.printStackTrace();
			EmailResponse response = new EmailResponse();
			response.setEmailMessage(text);
			response.setMessage("Email Send Failure  to emailId :" + toEmail);
			response.setStatus(CommonUtils.NotificationProperty.STATUS_FAILURE);
			return response;
		}
	}

}
