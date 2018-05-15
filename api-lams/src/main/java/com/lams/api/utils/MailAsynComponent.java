package com.lams.api.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.lams.api.service.ApplicationsService;
import com.lams.api.service.NotificationService;
import com.lams.api.service.UserMstrService;
import com.lams.model.bo.ApplicationsBO;
import com.lams.model.bo.NotificationBO;
import com.lams.model.bo.NotificationMainBO;
import com.lams.model.bo.UserBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.NotificationAlias;
import com.lams.model.utils.Enums.ContentType;
import com.lams.model.utils.Enums.NotificationType;

@Component
public class MailAsynComponent {

	
	private static final Logger logger = LoggerFactory.getLogger(MailAsynComponent.class);
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private UserMstrService userMstrService;
	
	@Autowired
	private ApplicationsService applicationsService; 
	
	@Value("${com.lams.login.url}")
	private String loginUrl;
	
	/**
	 * SEND MAIL TO LENDER WHEN BORROWER SUBMIT APPLICATION FORM 
	 * @param applicationId
	 * @param loggedInUserId
	 * @param lenderUserId
	 */
	@Async
	public void sendMailToLenderWhenBRSubmitForm(Long applicationId, Long loggedInUserId) {

		logger.info("ENTER IN SEND MAIL TO LENDER WHEN BORROWER SUBMIT FORM-----------------appID----->" + applicationId);
		
		ApplicationsBO applicationsBO = applicationsService.get(applicationId);
		if(CommonUtils.isObjectNullOrEmpty(applicationsBO)) {
			logger.info("END EMAIL,INVALID  AAPLICATION ID ");	
			return;
		}
		
		//GET ALL LENDER LIST BY APPLICATION TYPE FROM USER REPOSITORY
		List<UserBO> userBoList = userMstrService.getLenderUsersByApplicationType(applicationsBO.getApplicationTypeId());
		logger.info("TOTAL LENDER FOUND ------------------------------------------>" + userBoList.size());
		
		if(userBoList.size() < 1) {
			logger.info("NO LENDER FOUND IN SYSTEM");
			return;
		}
		
		//START CREATE NOTIFICATION OBJECTS
		NotificationBO notificationBO = new NotificationBO();
		notificationBO.setClientRefId(String.valueOf(loggedInUserId));
		List<NotificationMainBO> mainBolist = new ArrayList<>(userBoList.size());
		
		Map<String, Object> data = new HashMap<>();
		data.put("applicationCode", applicationsBO.getLeadReferenceNo());
		data.put("loginUrl",loginUrl);
		
		String subject = "VfinanceS – Loan Request – " +  applicationsBO.getLeadReferenceNo();
		NotificationMainBO mainBO = null;
		for(UserBO userBO : userBoList) {
		
			if(CommonUtils.isObjectNullOrEmpty(userBO.getEmail())) {
				logger.info("EMAIL ID IS NULL OR EMPTY ------------------------->");
				continue;
			}
			
			data.put("title", userBO.getFirstName() + " " + userBO.getLastName());
			mainBO = new NotificationMainBO();
			String to[] = { userBO.getEmail() };
			mainBO.setTo(to);
			mainBO.setContentType(ContentType.TEMPLATE);
			mainBO.setType(NotificationType.EMAIL);
			mainBO.setTemplateName(NotificationAlias.EMAIL_TO_LENDER_WHEN_BR_SUBMIT_FORM);
			mainBO.setParameters(data);
			mainBO.setSubject(subject);
			mainBolist.add(mainBO);
		}
		
		try {
			if(mainBolist.size() > 0) {
				notificationBO.setNotifications(mainBolist);
				notificationService.sendNotification(notificationBO);	
				logger.info("SUCCESSFULLY SENT MAILS TO LENDER");
			}
		} catch (Exception e) {
			logger.info("THROW EXCEPTION WHILE SENDING EMAIL");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * SEND MAIL TO BORROWER WHEN BORROWER SUBMIT APPLICATION FORM
	 * @param applicationId
	 * @param loggedInUserId
	 */
	@Async
	public void sendMailToBorrowerWhenBRSubmitForm(Long applicationId, Long loggedInUserId) {

		logger.info("ENTER IN SEND MAIL TO BORROWER WHEN BORROWER SUBMIT FORM-----------------appID----->" + applicationId);
		
		setNameAndCodeById(loggedInUserId, loggedInUserId, applicationId, NotificationAlias.EMAIL_TO_BR_WHEN_BR_SUBMIT_FORM);
		
	}
	
	/**
	 * SEND MAIL TO BORROER WHEN LENDER REVERT BACK TO BORROWER APPLICATION REQUEST
	 * @param borrowerUserId
	 * @param applicationId
	 * @param loggedInUserId
	 */
	public void sendMailToBorrowerWhenLenderRevertToBorrower(Long borrowerUserId, Long applicationId, Long loggedInUserId) {
		
		logger.info("ENTER IN SEND MAIL TO BORROWER WHEN BORROWER SUBMIT FORM-----------------appID----->" + applicationId);
		
		setNameAndCodeById(borrowerUserId, loggedInUserId, applicationId, NotificationAlias.EMAIL_TO_BR_WHEN_LENDER_REVERT_BACK);
		
	}
	
	/**
	 * SEND MAIL TO LENDER WHEN LENDER REVERT BACK TO BORROWER APPLICATION REQUEST
	 * @param applicationId
	 * @param loggedInUserId
	 */
	public void sendMailToLenderWhenLenderRevertToBorrower(Long applicationId,Long lenderId, Long loggedInUserId) {
		
		logger.info("ENTER IN SEND MAIL TO BORROWER WHEN BORROWER SUBMIT FORM-----------------appID----->" + applicationId);
		
		setNameAndCodeById(lenderId, loggedInUserId, applicationId, NotificationAlias.EMAIL_TO_LENDER_WHEN_LENDER_REVERT_BACK);
		
	}
	
	
	/**
	 * SEND MAIL TO BORROWER WHEN BORROWER ACCEPT THE LENDER
	 * @param borrowerUserId
	 * @param applicationId
	 * @param lenderId
	 */
	public void sendMailToBorrowerWhenBorrowerAcceptTheLender(Long borrowerUserId, Long applicationId,Long loggedInUserId) {
		
		logger.info("ENTER IN sendMailToBorrowerWhenBorrowerAcceptTheLender-----------------appID----->" + applicationId);
		
		setNameAndCodeById(borrowerUserId,loggedInUserId,applicationId,NotificationAlias.EMAIL_TO_BR_WHEN_BR_ACCEPT);
	}
	
	
	/**
	 * SEND MAIL TO LENDER WHEN BORROWER ACCEPT THE LENDER
	 * @param applicationId
	 * @param lenderId
	 */
	public void sendMailToLenderWhenBorrowerAcceptTheLender(Long lenderId,Long applicationId,Long loggedInUserId) {
		
		logger.info("ENTER IN sendMailToLenderWhenBorrowerAcceptTheLender-----------------appID----->" + applicationId);
		setNameAndCodeById(lenderId,loggedInUserId,applicationId,NotificationAlias.EMAIL_TO_LENDER_WHEN_BR_ACCEPT);
		
	}
	
//	public void setDataAndSendMail(Long toUserId, Long applicationId, String template,Long loggedInUserId) {
//		
//		UserBO userBO = userMstrService.getUserBasicDetails(toUserId);
//		if(CommonUtils.isObjectNullOrEmpty(userBO)) {
//			logger.info("END EMAIL,INVALID LEDNER USERID ");
//			return;
//		}
//		
//		
//		ApplicationsBO applicationsBO = applicationsService.get(applicationId);
//		if(CommonUtils.isObjectNullOrEmpty(applicationsBO)) {
//			logger.info("END EMAIL,INVALID  AAPLICATION ID ");	
//			return;
//		}
//		
//		Map<String, Object> data = new HashMap<>();
//		data.put("title", userBO.getFirstName() + " " + userBO.getLastName());
//		data.put("applicationCode", applicationsBO.getLeadReferenceNo());
//		data.put("loginUrl",loginUrl);
//		
//		
//		sendMail(data, userBO.getEmail(), loggedInUserId, template, "VfinanceS – Loan Request – " +  applicationsBO.getLeadReferenceNo());
//		
//	}
	
	
	/**
	 * SET ALL REQUIRED DATA TO MAP AND PASS TO SENT MAIL METHOD
	 * @param toUserId  THE ONE WHO WANTS TO SENT MAIL
	 * @param loggedInUserId    CURRENT USER LOGIN ID
	 * @param applicationId  BORROWER APPLCIATION ID
	 * @param template  TEMPLATE NAME
	 */
	public void setNameAndCodeById (Long toUserId, Long loggedInUserId, Long applicationId, String template) {
		
		UserBO userBO = userMstrService.getUserBasicDetails(toUserId);
		if(CommonUtils.isObjectNullOrEmpty(userBO)) {
			logger.info("END EMAIL,INVALID LEDNER USERID ");
			return;
		}
		
		
		ApplicationsBO applicationsBO = applicationsService.get(applicationId);
		if(CommonUtils.isObjectNullOrEmpty(applicationsBO)) {
			logger.info("END EMAIL,INVALID  AAPLICATION ID ");	
			return;
		}
		
		Map<String, Object> data = new HashMap<>();
		data.put("title", userBO.getFirstName() + " " + userBO.getLastName());
		data.put("applicationCode", applicationsBO.getLeadReferenceNo());
		data.put("loginUrl",loginUrl);
		
		
		sendMail(data, userBO.getEmail(), loggedInUserId, template, "VfinanceS – Loan Request – " +  applicationsBO.getLeadReferenceNo());
		
	}
	
	
	
	/**
	 * SENT MAIL 
	 * @param data
	 * @param toEmailId
	 * @param loggedInUserId
	 * @param templateName
	 * @param subject
	 */
	public void sendMail(Map<String, Object> data, String toEmailId, Long loggedInUserId, String templateName,String subject) {
		
		try {
			logger.info("START SENDING MAIL TO --------------------------------->", toEmailId);
			NotificationBO notificationBO = new NotificationBO();
			notificationBO.setClientRefId(String.valueOf(loggedInUserId));
			List<NotificationMainBO> mainBolist = new ArrayList<>();
			NotificationMainBO mainBO = new NotificationMainBO();
			String to[] = { toEmailId };
			mainBO.setTo(to);
			mainBO.setContentType(ContentType.TEMPLATE);
			mainBO.setType(NotificationType.EMAIL);
			mainBO.setTemplateName(templateName);
			mainBO.setParameters(data);
			mainBO.setSubject(subject);
			mainBolist.add(mainBO);
			notificationBO.setNotifications(mainBolist);
			notificationService.sendNotification(notificationBO);	
			logger.info("SUCCESSFULLY SEND EMAIL TO THIS EMAIL ADDRESS _----------------------------------->" + toEmailId);
		} catch (Exception e) {
			logger.info("THROW EXCEPTION WHILE SENDING MAIL ---------> EMAIL---------->"+ toEmailId + "--------------TEMPLATE------------->" + templateName);
			e.printStackTrace();
		}

	}
	
}
