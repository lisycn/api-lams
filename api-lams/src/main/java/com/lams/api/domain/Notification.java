package com.lams.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.lams.api.domain.master.Auditor;
import com.lams.api.domain.master.notification.NotificationProvider;

/**
 * The persistent class for the notification database table.
 * 
 */
/**
 * @author Akshay
 * 
 *
 */
@Entity
@Table(name = "notification")
@NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
public class Notification extends Auditor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "message")
	private String message;

	@ManyToOne
	@JoinColumn(name = "provider")
	private NotificationProvider provider;

	@Column(name = "notification_type")
	private String notificationType;

	@Column(name = "template_name")
	private String templateName;

	@Column(name = "user_id")
	private Long userId;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "notification_id")
	private List<NotificationLog> notificationLogs = new ArrayList<NotificationLog>();

	public Notification() {
		super();
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NotificationProvider getProvider() {
		return provider;
	}

	public void setProvider(NotificationProvider provider) {
		this.provider = provider;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	
	public List<NotificationLog> getNotificationLogs() {
		return this.notificationLogs;
	}

	public void setNotificationLogs(List<NotificationLog> notificationLogs) {
		this.notificationLogs = notificationLogs;
	}

	public NotificationLog addNotificationLog(NotificationLog notificationLog) {
		getNotificationLogs().add(notificationLog);
		notificationLog.setNotification(this);

		return notificationLog;
	}

	public NotificationLog removeNotificationLog(NotificationLog notificationLog) {
		getNotificationLogs().remove(notificationLog);
		notificationLog.setNotification(null);

		return notificationLog;
	}

}