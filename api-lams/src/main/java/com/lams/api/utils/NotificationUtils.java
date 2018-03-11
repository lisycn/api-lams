package com.lams.api.utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.lams.api.boot.NotificationContext;
import com.lams.model.bo.ContentAttachmentBO;
import com.lams.model.bo.EmailResponse;
import com.lams.model.bo.NotificationMainBO;
import com.lams.model.bo.SMSResponse;
import com.lams.model.utils.CommonUtils;

/**
 * @author Akshay
 *
 */
public class NotificationUtils {

	private final Logger log = LoggerFactory.getLogger(NotificationUtils.class);

	public static final String userId = "userId";
	public static final String userType = "userType";

	public SMSResponse sendSMS(String recipent, String message, String username, String password, String originator,
			String reqURL) throws Exception {
		SMSResponse response = new SMSResponse();

		try {
			String requestUrl = reqURL + "username=" + URLEncoder.encode(username, "UTF-8") + "&password="
					+ URLEncoder.encode(password, "UTF-8") + "&source=" + URLEncoder.encode(originator, "UTF-8")
					+ "&dmobile=" + URLEncoder.encode(recipent, "UTF-8") + "&message="
					+ URLEncoder.encode(message, "UTF-8") + "&username=" + URLEncoder.encode(username, "UTF-8")
					+ "&password=" + URLEncoder.encode(password, "UTF-8") + "&recipient="
					+ URLEncoder.encode(recipent, "UTF-8") + "&messagetype=SMS:TEXT" + "&messagedata="
					+ URLEncoder.encode(message, "UTF-8") + "&originator=" + URLEncoder.encode(originator, "UTF-8")
					+ "&serviceprovider=GSMModem1" + "&responseformat=html";
			log.info("Request URL :==" + requestUrl);
			URL url = new URL(requestUrl);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();

			log.info("SMS Send Successfull : " + uc.getResponseMessage());
			if (uc.getResponseMessage().equalsIgnoreCase("OK")) {
				uc.disconnect();
				response.setSmsMessage(message);
				response.setMessage(uc.getResponseMessage());
				response.setStatus(CommonUtils.NotificationProperty.STATUS_SUCCESSFULL);
			} else {
				log.error("SMS Send Failure");
				response.setSmsMessage(message);
				response.setMessage("Failure While Sending SMS to number : " + recipent);
				response.setStatus(CommonUtils.NotificationProperty.STATUS_FAILURE);
				throw new Exception("Failure While Sending SMS to number : " + recipent);
			}

			return response;
		} catch (Exception e) {
			response.setSmsMessage(message);
			response.setMessage("SMS Send Failure  to number :" + recipent);
			response.setStatus(CommonUtils.NotificationProperty.STATUS_SMS_SENDING_FAILURE);
			throw new Exception("Failure While Sending SMS to number : " + e.getMessage());
		}

	}

	public static String convertTOCommaSpe(String[] name) {
		if (name.length > 0) {
			StringBuilder nameBuilder = new StringBuilder();

			for (String n : name) {
				nameBuilder.append("").append(n.replace("'", "\\'")).append(",");
			}
			nameBuilder.deleteCharAt(nameBuilder.length() - 1);
			return nameBuilder.toString();
		} else {
			return "";
		}

	}

	public EmailResponse sendEmail(MimeMessage mimeMessage, JavaMailSender javaMailSender, NotificationMainBO emailRequest,
			String text) throws MessagingException, Exception {
		EmailResponse response = new EmailResponse();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			if (emailRequest.getFrom() != null) {
				mimeMessageHelper.setFrom(emailRequest.getFrom());
			}
			mimeMessageHelper.setTo(emailRequest.getTo());
			mimeMessageHelper.setSubject(emailRequest.getSubject());
			mimeMessageHelper.setText(text, true);
			if (emailRequest.getCc() != null) {
				mimeMessageHelper.setCc(emailRequest.getCc());
			}
			if (emailRequest.getBcc() != null) {
				mimeMessageHelper.setBcc(emailRequest.getBcc());
			}
			if (emailRequest.getContentInBytes() != null) {
				if (Integer.valueOf(NotificationContext.MAX_ATTACHMETNT_SIZE
						.trim()) > (emailRequest.getContentInBytes().length / 1000000)) {
					log.info("Size of content in bytes :" + emailRequest.getContentInBytes().length);
					log.info("Size of content in mbs :" + emailRequest.getContentInBytes().length / 1000000);
					ByteArrayDataSource arrayDataSource = new ByteArrayDataSource(emailRequest.getContentInBytes(),
							"application/mime");
					mimeMessageHelper.addAttachment(emailRequest.getFileName(), arrayDataSource);
				}
			}

			for (ContentAttachmentBO attachment : emailRequest.getContentAttachments()) {
				if (attachment != null) {
					if (Integer.valueOf(NotificationContext.MAX_ATTACHMETNT_SIZE
							.trim()) > (attachment.getContentInByte().length / 1000000)) {
						log.info("Size of content in bytes :" + attachment.getContentInByte().length);
						log.info("Size of content in mbs :" + attachment.getContentInByte().length / 1000000);
						ByteArrayDataSource arrayDataSource = new ByteArrayDataSource(attachment.getContentInByte(),
								"application/mime");
						mimeMessageHelper.addAttachment(attachment.getFileName(), arrayDataSource);
					}
				}
			}

			javaMailSender.send(mimeMessage);
			log.info("Mail Sent Successfull to emaildId : " + convertTOCommaSpe(emailRequest.getTo()));

			response.setMessage("Email Sent Successfully");
			response.setStatus(CommonUtils.NotificationProperty.STATUS_SUCCESSFULL);
			response.setEmailMessage(text);
			return response;
		} catch (MessagingException e) {
			log.error("Failure While Sending Email to emaildId : " + convertTOCommaSpe(emailRequest.getTo())
					+ " : " + e.getMessage());
			throw new Exception("Failure While Sending Email to emaildId : "
					+ convertTOCommaSpe(emailRequest.getTo()) + " : " + e.getMessage());

		}
	}

}
