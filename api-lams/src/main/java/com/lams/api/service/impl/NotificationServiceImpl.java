package com.lams.api.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lams.api.service.MailService;
import com.lams.api.service.NotificationService;
import com.lams.api.service.SmsService;
import com.lams.model.bo.EmailResponse;
import com.lams.model.bo.NotificationBO;
import com.lams.model.bo.NotificationMainBO;
import com.lams.model.bo.NotificationResponse;
import com.lams.model.bo.SMSRequest;
import com.lams.model.bo.SMSResponse;
import com.lams.model.utils.CommonUtils;

/**
 * @author Akshay
 *
 */
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

	private final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@Autowired
	private MailService mailService;

	@Autowired
	private SmsService smsService;

	@Value("${com.lams.login.url}")
	private String LOGIN_URL;

	@Value("${com.lams.support.email}")
	private String SUPPORT_EMAIL;

	@Value("${com.lams.support.mobile}")
	private String SUPPORT_MOBILE;

	@Override
	public NotificationResponse sendNotification(NotificationBO notification) throws Exception {
		NotificationResponse response = null;
		for (NotificationMainBO request : notification.getNotifications()) {
			logger.info("Notification Object == {}", request.toString());
			if (CommonUtils.isObjectNullOrEmpty(request.getType())) {
				logger.warn("Notification Type is NUll");
				continue;
			}
			try {
				switch (request.getType()) {
				case EMAIL:
					if (CommonUtils.isObjectListNull(request.getContentType(), request.getTemplateName(),
							request.getParameters(), request.getSubject(), request.getTo(),
							notification.getClientRefId())) {
						logger.warn("Some parameter values Missing for Notification Type: EMAIL");
						continue;
					}
					// EmailRequest emailRequest = new EmailRequest();
					// BeanUtils.copyProperties(request, emailRequest);
					Map<String, Object> parameters = new HashMap<>(request.getParameters());
					parameters.put("login_url", LOGIN_URL);
					parameters.put("support_email", SUPPORT_EMAIL);
					parameters.put("support_mobile", SUPPORT_MOBILE);
					request.setParameters(parameters);
					response = mailService.sendEmail(request);
					logger.info("Notification Service executed successfully For EMAIL");
					// return response;
					break;
				case SMS:
					if (CommonUtils.isObjectListNull(request.getContentType(), request.getTemplateName(),
							request.getParameters(), request.getContent(), request.getTo(),
							notification.getClientRefId())) {
						logger.warn("Some parameter values Missing for Notification Type: EMAIL");
						continue;
					}
					SMSRequest smsRequest = new SMSRequest();
					smsRequest.setPhoneNumber(request.getTo());
					smsRequest.setTemplateName(request.getTemplateName());
					smsRequest.setContentType(request.getContentType());
					smsRequest.setParameters(request.getParameters());
					smsRequest.setContent(request.getContent());
					smsRequest.setUserId(Long.valueOf(notification.getClientRefId()));
					response = smsService.sendSMS(request);
					logger.info("Notification Service executed successfully For SMS");
					// return response;
					break;
				case SYSTEM:
					break;
				}
			} catch (Exception e) {
				logger.error("Notification Service executed unsuccessfully because of : " + e.getMessage());
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
		}
		return response;
	}

}
