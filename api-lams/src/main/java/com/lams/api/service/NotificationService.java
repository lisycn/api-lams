package com.lams.api.service;

import com.lams.model.bo.NotificationBO;
import com.lams.model.bo.NotificationResponse;

/**
 * @author Akshay
 *
 */
public interface NotificationService {

	public NotificationResponse sendNotification(NotificationBO notification)
			throws Exception;

}
