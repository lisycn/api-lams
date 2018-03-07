package com.lams.api.service.impl;

import java.io.IOException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lams.api.service.MailService;
import com.lams.api.service.NotificationService;
import com.lams.model.bo.NotificationBO;
import com.lams.model.bo.NotificationResponse;

import freemarker.template.TemplateException;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

	private final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);
	@Autowired
	private MailService mailService;

//	@Autowired
//	private SmsService smsService;
//
//	@Autowired
//	private SystemNotifyService systemNotifyService;

	@Autowired
	private Environment environment;

	private static final String LOGIN_URL = "capitaworld.login.url";
	private static final String SUPPORT_EMAIL = "capitaworld.support.email";
	private static final String SUPPORT_MOBILE = "capitaworld.support.mobile";

	@Override
	public NotificationResponse sendNotification(NotificationBO notificationRequest)
			throws MessagingException, IOException, TemplateException {
		return null;

	}
}
