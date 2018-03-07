package com.lams.api.service;

import java.io.IOException;

import javax.mail.MessagingException;

import com.lams.model.bo.NotificationBO;
import com.lams.model.bo.NotificationResponse;

import freemarker.template.TemplateException;

/**
 * @author Akshay
 *
 */
public interface NotificationService {

	public NotificationResponse sendNotification(NotificationBO notificationRequest)
			throws MessagingException, IOException, TemplateException;

}
