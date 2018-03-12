package com.lams.api.boot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.lams.api.service.NotificationService;
import com.lams.model.bo.NotificationBO;
import com.lams.model.bo.NotificationMainBO;
import com.lams.model.utils.Enums.ContentType;
import com.lams.model.utils.Enums.NotificationType;
import com.lams.model.utils.NotificationAlias;

@SpringBootApplication
@ComponentScan(basePackages = { "com.lams" })
@EnableAsync
@EnableScheduling
public class LamsMain {

	@Autowired
	private NotificationService notificationService;

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(LamsMain.class, args);
		 applicationContext.getBean(LamsMain.class).sendMail();
	}

	public void sendMail() throws Exception {
		System.out.println("Entry in sendMail");
		NotificationBO notificationBO = new NotificationBO();
		notificationBO.setClientRefId(String.valueOf(1l));

		List<NotificationMainBO> mainBolist = new ArrayList<>();
		NotificationMainBO mainBO = new NotificationMainBO();
		String to[] = { "harshsuhagiya10@gmail.com" };
		mainBO.setTo(to);
		mainBO.setFrom("patelakshay168@gmail.com");
		mainBO.setContentType(ContentType.TEMPLATE);
		mainBO.setType(NotificationType.EMAIL);
		mainBO.setTemplateName(NotificationAlias.EMAIL);
		Map<String, Object> data = new HashMap<>();
		data.put("title", "Hello Modern Saheb Farithi Testing Kru chu");
		data.put("msg", "Kem cho ?");
		mainBO.setParameters(data);
		mainBO.setSubject("Testing Email");
		mainBolist.add(mainBO);
		notificationBO.setNotifications(mainBolist);
		notificationService.sendNotification(notificationBO);
		System.out.println("Exit in sendMail");
		// // Map<>
	}
}
