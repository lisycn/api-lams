package com.lams.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lams.api.domain.master.notification.NotificationProvider;
import com.lams.api.repository.NotificationRepository;
import com.lams.api.repository.master.notification.NotificationProviderRepository;
import com.lams.api.repository.master.notification.NotificationTemplateRepository;
import com.lams.api.service.MailService;
import com.lams.api.service.SmsService;
import com.lams.model.bo.SMSRequest;
import com.lams.model.bo.SMSResponse;

import freemarker.template.Configuration;

/**
 * @author Akshay
 *
 */
@Service
public class SmsServiceImpl implements SmsService {

	protected static final Long NOTIFICATION_TYPE_ID = 2L;

	private final Logger log = LoggerFactory.getLogger(SmsServiceImpl.class);

	@Autowired
	private NotificationProviderRepository repository;

	@Autowired
	private Configuration fmConfiguration;

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private NotificationTemplateRepository notificationTemplateRepository;

	@Autowired
	private MailService mailService;

	@Override
	public SMSResponse sendSMS(SMSRequest smsRequest) throws Exception {

		SMSResponse response = new SMSResponse();
		return response;
//		SmsUtils utils = new SmsUtils(NotificationContext.smsProperty, fmConfiguration);
//		String message = null;
//		try {
//
//			try {
//				smsRequest.setTemplateName(notificationTemplateRepository
//						.findByNameAndNotificationType(NOTIFICATION_TYPE_ID, smsRequest.getTemplateId())
//						.getTemplateName());
//			} catch (Exception e) {
//				throw new Exception(e.getMessage());
//			}
//
//			message = utils.generateMessageFromTemplate(smsRequest);
//			response = utils.sendSMS(smsRequest, message);
//
//			Notification notification = utils.generateNotificationToSave(response, getNotificationProvider(),
//					CommonUtils.NotificationProperty.STATUS_SUCCESSFULL, smsRequest, 0L);
//
//			response.setMessage("Notification Sent and saved");
//			response.setStatus(CommonUtils.NotificationProperty.STATUS_SUCCESSFULL);
//			notificationRepository.save(notification);
//			log.info("Notification Sent and saved for SMS : "
//					+ NotificationUtils.convertTOCommaSpe(smsRequest.getPhoneNumber()));
//
//			return response;
//		} catch (IOException e) {
//			e.printStackTrace();
//			response.setSmsMessage(message);
//			response.setMessage("SMS Gateway Connection Failure");
//			response.setStatus(NotificationProperty.STATUS_FAILURE);
//			mailService.sendFailureEmailForSMS(e.getMessage());
//			Notification notification = utils.generateNotificationToSave(response, getNotificationProvider(),
//					CommonUtils.NotificationProperty.STATUS_FAILURE, smsRequest, 0L);
//
//			notificationRepository.save(notification);
//			throw new Exception(e.getMessage());
//		} catch (Exception e) {
//			response.setSmsMessage(message);
//			response.setMessage(
//					"SMS Send Failure  to number :" + NotificationUtils.convertTOCommaSpe(smsRequest.getPhoneNumber()));
//			response.setStatus(NotificationProperty.STATUS_SMS_SENDING_FAILURE);
//			Notification notification = utils.generateNotificationToSave(response, getNotificationProvider(),
//					CommonUtils.NotificationProperty.STATUS_SMS_SENDING_FAILURE, smsRequest, 0L);
//
//			notificationRepository.save(notification);
//			throw new Exception(e.getMessage());
//		}
//
//		catch (SMSTemplateException e) {
//			response.setSmsMessage(message);
//			response.setMessage("SMS Template Failure  to number :"
//					+ NotificationUtils.convertTOCommaSpe(smsRequest.getPhoneNumber()));
//			response.setStatus(NotificationProperty.STATUS_SMS_SENDING_FAILURE);
//			Notification notification = utils.generateNotificationToSave(response, getNotificationProvider(),
//					CommonUtils.NotificationProperty.STATUS_SMS_TEMPLATE_NOTFOUND, smsRequest, 0L);
//
//			notificationRepository.save(notification);
//			throw new SMSTemplateException(e.getMessage());
//		} catch (Exception e) {
//			response.setSmsMessage(message);
//			response.setMessage(
//					"SMS Send Failure  to number :" + NotificationUtils.convertTOCommaSpe(smsRequest.getPhoneNumber()));
//			response.setStatus(NotificationProperty.STATUS_SMS_SENDING_FAILURE);
//			Notification notification = utils.generateNotificationToSave(response, getNotificationProvider(),
//					CommonUtils.NotificationProperty.STATUS_NOTIFICATION_SENDING_FAILURE, smsRequest, 0L);
//
//			notificationRepository.save(notification);
//			log.error("Notification was not Sent or saved for email Id : " + smsRequest.getPhoneNumber()
//					+ " caused Exception :  " + e.getMessage());
//			throw new Exception("Notification was not Sent or saved for email Id "
//					+ NotificationUtils.convertTOCommaSpe(smsRequest.getPhoneNumber()));
//		}

	}

	@Override
	public NotificationProvider getNotificationProvider() {
//		return repository.getNotificationProvider(2L, "Y");
		return null;
	}

	@Override
	public void sendUnsentSMS() {
		log.info("Entry in sendUnsentSMS");
//		List<Notification> notifications = notificationRepository.getUnsentSMS(200L, NOTIFICATION_TYPE_ID);
//		SmsUtils utils = new SmsUtils(NotificationContext.smsProperty, fmConfiguration);
//		for (Notification notification : notifications) {
//			for (NotificationSmsLog smsLog : notification.getNotificationSmsLogs()) {
//				SMSResponse response = new SMSResponse();
//				try {
//					response = utils.sendUnsentSMS(smsLog.getMobileNumber(), notification.getMessage());
//
//					smsLog.setModifiedDate(CommonUtils.getCurrentTimeStamp());
//					smsLog.setNotificationSmsLogMessage(response.getMessage());
//					smsLog.setNotificationSmsLogResponse(
//							response.getStatus() != null ? response.getStatus().toString() : null);
//					smsLog.setStatus(response.getStatus());
//					smsLog.setResentCount(smsLog.getResentCount() + 1);
//					smsLog.setIsSent(NotificationProperty.SUCCESSFUL);
//				} catch (IOException e) {
//					mailService.sendFailureEmailForSMS(e.getMessage());
//
//					smsLog.setModifiedDate(CommonUtils.getCurrentTimeStamp());
//					smsLog.setNotificationSmsLogMessage("SMS Gateway connection Failure ");
//					smsLog.setNotificationSmsLogResponse(String.valueOf(NotificationProperty.STATUS_FAILURE));
//					smsLog.setStatus(NotificationProperty.STATUS_FAILURE);
//					smsLog.setResentCount(smsLog.getResentCount() + 1);
//					smsLog.setIsSent(NotificationProperty.FAILURE);
//					e.printStackTrace();
//				} catch (Exception e) {
//					smsLog.setModifiedDate(CommonUtils.getCurrentTimeStamp());
//					smsLog.setNotificationSmsLogMessage("SMS sending Failure ");
//					smsLog.setNotificationSmsLogResponse(
//							String.valueOf(NotificationProperty.STATUS_SMS_SENDING_FAILURE));
//					smsLog.setStatus(NotificationProperty.STATUS_SMS_SENDING_FAILURE);
//					smsLog.setResentCount(smsLog.getResentCount() + 1);
//					smsLog.setIsSent(NotificationProperty.FAILURE);
//					e.printStackTrace();
//				}
//			}
//
//		}

//		notificationRepository.save(notifications);
		log.info("Exit from sendUnsentSMS");
	}

}
