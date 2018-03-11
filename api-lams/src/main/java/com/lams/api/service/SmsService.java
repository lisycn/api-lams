package com.lams.api.service;

import com.lams.api.domain.master.notification.NotificationProvider;
import com.lams.model.bo.SMSRequest;
import com.lams.model.bo.SMSResponse;

/**
 * @author Akshay
 *
 */
public interface SmsService {

	public SMSResponse sendSMS(SMSRequest smsRequest) throws Exception;
	
	public NotificationProvider getNotificationProvider();

	public void sendUnsentSMS();

}
