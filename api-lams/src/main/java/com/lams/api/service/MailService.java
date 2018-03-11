package com.lams.api.service;

import com.lams.model.bo.EmailResponse;
import com.lams.model.bo.NotificationMainBO;


/**
 * @author Akshay
 *
 */
public interface MailService {

	public EmailResponse sendEmail(NotificationMainBO emailRequest) throws Exception;
	
	public void sendUnsentEmail();
	
	public void sendFailureEmailForSMS(String exception);
	
}
