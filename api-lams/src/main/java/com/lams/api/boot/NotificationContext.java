package com.lams.api.boot;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.lams.api.domain.master.notification.NotificationProvider;
import com.lams.api.repository.master.notification.NotificationProviderRepository;
import com.lams.api.utils.SMSProperty;
import com.lams.model.utils.Enums.NotificationType;


/**
 * @author Akshay
 *
 */
@Configuration
@EnableTransactionManagement
public class NotificationContext implements ApplicationListener<ContextRefreshedEvent> {

	private final Logger log = LoggerFactory.getLogger(NotificationContext.class);
	 @Autowired
	    private Environment environment;
	 	
	@Autowired
	private NotificationProviderRepository notificationProviderRepository; 
	
	public static JavaMailSenderImpl javaMailSender;
	
	public static SMSProperty smsProperty;
	
	protected static final String PROPERTY_NAME_EMAIL_PASSWORD = "password";
    protected static final String PROPERTY_NAME_EMAIL_HOST  = "host";
    protected static final String PROPERTY_NAME_EMAIL_USERNAME = "username";
    protected static final String PROPERTY_NAME_EMAIL_PORT = "port";
    protected static final String PROPERTY_NAME_EMAIL_PROTOCOL = "protocol";
    protected static final String PROPERTY_NAME_EMAIL_SMTP_AUTH = "mail.smtp.auth";
    protected static final String PROPERTY_NAME_EMAIL_SMTP_STARTTLS = "mail.smtp.starttls.enable";

	protected static final String PROPERTY_NAME_SMS_USERNAME= "username";
    protected static final String PROPERTY_NAME_SMS_PASSWORD = "password";
    protected static final String PROPERTY_NAME_SMS_ORIGINATOR = "originator";
    protected static final String PROPERTY_NAME_SMS_REQUESTURL= "requestUrl";
    
    protected static final String PROPERTY_NAME_EMAIL_ATTACHMENT_SIZE = "com.lams.notification.email.attachment.size";
    
    protected static final String PROPERTY_FAILURE_INFO_EMAIL_TO = "com.lams.notification.failure.info.email.to";
    
    protected static final String PROPERTY_FAILURE_INFO_EMAIL_SUBJECT = "com.lams.notification.failure.info.email.subject";
    
    protected static final String PROPERTY_FAILURE_INFO_EMAIL_TEMPLATE = "com.lams.notification.failure.info.email.template";
    
    public static String FAILURE_INFO_EMAIL_TO = "";
    
    public static String FAILURE_INFO_EMAIL_SUBJECT = "";
    
    public static String FAILURE_INFO_EMAIL_TEMPLATE = "";
    
    public static String MAX_ATTACHMETNT_SIZE = "";

    @Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		NotificationProvider notificationProvider = notificationProviderRepository.findByNotificationTypeAndIsActive(NotificationType.EMAIL.getValue(), true);
		javaMailSender= new JavaMailSenderImpl();
		log.info(notificationProvider.getProperty()+"."+PROPERTY_NAME_EMAIL_PORT);
		int port = Integer.parseInt(environment.getRequiredProperty(notificationProvider.getProperty()+"."+PROPERTY_NAME_EMAIL_PORT));
		 javaMailSender.setHost(environment.getRequiredProperty(notificationProvider.getProperty()+"."+PROPERTY_NAME_EMAIL_HOST));
		 javaMailSender.setPassword(environment.getRequiredProperty(notificationProvider.getProperty()+"."+PROPERTY_NAME_EMAIL_PASSWORD));
		 javaMailSender.setPort(port);
		 javaMailSender.setProtocol(environment.getRequiredProperty(notificationProvider.getProperty()+"."+PROPERTY_NAME_EMAIL_PROTOCOL));
		 javaMailSender.setUsername(environment.getRequiredProperty(notificationProvider.getProperty()+"."+PROPERTY_NAME_EMAIL_USERNAME));
		 Properties properties = new Properties();
			
			properties.setProperty(PROPERTY_NAME_EMAIL_SMTP_AUTH, environment.getRequiredProperty(notificationProvider.getProperty()+"."+PROPERTY_NAME_EMAIL_SMTP_AUTH));
			properties.setProperty(PROPERTY_NAME_EMAIL_SMTP_STARTTLS, environment.getRequiredProperty(notificationProvider.getProperty()+"."+PROPERTY_NAME_EMAIL_SMTP_STARTTLS));
		 javaMailSender.setJavaMailProperties(properties);
		 
		 
		 MAX_ATTACHMETNT_SIZE = environment.getRequiredProperty(PROPERTY_NAME_EMAIL_ATTACHMENT_SIZE);
		 
		 FAILURE_INFO_EMAIL_TO = environment.getRequiredProperty(PROPERTY_FAILURE_INFO_EMAIL_TO);
		 
		 FAILURE_INFO_EMAIL_SUBJECT = environment.getRequiredProperty(PROPERTY_FAILURE_INFO_EMAIL_SUBJECT);
		 
		 FAILURE_INFO_EMAIL_TEMPLATE = environment.getRequiredProperty(PROPERTY_FAILURE_INFO_EMAIL_TEMPLATE);
		 
		 NotificationProvider notificationProviderSMS = notificationProviderRepository.findByNotificationTypeAndIsActive(NotificationType.SMS.getValue(), true);
		 
		 System.out.println(environment.getRequiredProperty(notificationProviderSMS.getProperty()+"."+PROPERTY_NAME_SMS_USERNAME));
		 smsProperty = new SMSProperty();
		 smsProperty.setUsername(environment.getRequiredProperty(notificationProviderSMS.getProperty()+"."+PROPERTY_NAME_SMS_USERNAME));
         smsProperty.setPassword(environment.getRequiredProperty(notificationProviderSMS.getProperty()+"."+PROPERTY_NAME_SMS_PASSWORD));
         smsProperty.setOriginator(environment.getRequiredProperty(notificationProviderSMS.getProperty()+"."+PROPERTY_NAME_SMS_ORIGINATOR));
         smsProperty.setRequestURL(environment.getRequiredProperty(notificationProviderSMS.getProperty()+"."+PROPERTY_NAME_SMS_REQUESTURL));
		 
		 
	}
	
  }
