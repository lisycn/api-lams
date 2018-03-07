package com.lams.api.service;

import javax.mail.MessagingException;

/**
 * @author Akshay
 *
 */
public interface MailService {
	public boolean sendEmail() throws MessagingException;

}
