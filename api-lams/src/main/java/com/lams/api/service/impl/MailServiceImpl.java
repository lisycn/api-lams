package com.lams.api.service.impl;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lams.api.service.MailService;

import freemarker.template.Configuration;

/**
 * @author Akshay
 *
 */

@Service
@Transactional
public class MailServiceImpl implements MailService {

	protected static final Long NOTIFICATION_TYPE_ID = 1L;

	private final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	private Configuration fmConfiguration;

	@Override
	public boolean sendEmail() throws MessagingException {
		return false;
	}
}
