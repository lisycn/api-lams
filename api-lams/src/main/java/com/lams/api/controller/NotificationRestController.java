package com.lams.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lams.api.service.NotificationService;
import com.lams.model.bo.NotificationBO;
import com.lams.model.bo.NotificationResponse;
import com.lams.model.utils.CommonUtils;

/**
 * @author Akshay
 *
 */
@RestController
public class NotificationRestController {
	//
	private final Logger logger = LoggerFactory.getLogger(NotificationRestController.class);
	@Autowired
	private NotificationService notificationService;

	@RequestMapping("version")
	@ResponseStatus(HttpStatus.OK)
	public String version() {
		return "[OK] Welcome to withdraw Restful version 1.0";
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public NotificationResponse sendSimpleMail(@RequestBody NotificationBO notificationBO) throws Exception {
		logger.info("Entry in Notification");
		try {
			return notificationService.sendNotification(notificationBO);
		} catch (Exception e) {
			e.printStackTrace();
			NotificationResponse response = new NotificationResponse();
			response.setMessage(e.getMessage());
			response.setSentMessage("");
			response.setStatus(CommonUtils.NotificationProperty.STATUS_FAILURE);
			logger.error("Notification Service executed unsuccessfully because of : " + e.getMessage());
			return response;
		}
	}
}
