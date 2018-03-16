package com.lams.api.service;

import com.lams.model.bo.NotificationMainBO;
import com.lams.model.bo.SMSResponse;

/**
 * @author Akshay
 *
 */
public interface SmsService {

	public SMSResponse sendSMS(NotificationMainBO smsRequest) throws Exception;

}
