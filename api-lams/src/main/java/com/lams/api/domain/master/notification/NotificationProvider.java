package com.lams.api.domain.master.notification;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.lams.api.domain.master.Auditor;

/**
 * The persistent class for the notification_provider database table.
 * 
 */
/**
 * @author Akshay
 *
 */
@Entity
@Table(name = "notification_provider")
@NamedQuery(name = "NotificationProvider.findAll", query = "SELECT n FROM NotificationProvider n")
public class NotificationProvider extends Auditor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String password;

	@Column(name = "request_url")
	private String requestUrl;

	private String username;

	@Column(name = "notification_type")
	private String notificationType;

	@Column(name = "property")
	private String property;

	public NotificationProvider() {
		super();
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRequestUrl() {
		return this.requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	protected String getNotificationType() {
		return notificationType;
	}

	protected void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
}