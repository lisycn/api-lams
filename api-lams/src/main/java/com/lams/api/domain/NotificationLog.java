package com.lams.api.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.lams.api.domain.master.Auditor;

/**
 * The persistent class for the notification_email_log database table.
 * 
 */
/**
 * @author Akshay
 *
 */
@Entity
@Table(name = "notification_log")
@NamedQuery(name = "NotificationEmailLog.findAll", query = "SELECT n FROM NotificationEmailLog n")
public class NotificationLog extends Auditor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "log_message")
	private String logMessage;

	@Column(name = "log_response")
	private String logResponse;

	@ManyToOne
	@JoinColumn(name = "notification_id")
	private Notification notification;

	@Column(name = "status")
	private Long status;

	@Column(name = "subject")
	private String subject;

	@Column(name = "to_email")
	private String toEmail;

	@Column(name = "resent_count")
	private Long resentCount;

	@Column(name = "is_sent")
	private Long isSent;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "notification_type")
	private String notificationType;

	public NotificationLog() {
		super();
	}

	public Long getIsSent() {
		return isSent;
	}

	public void setIsSent(Long isSent) {
		this.isSent = isSent;
	}

	public String getSubject() {
		return subject;
	}

	public String getToEmail() {
		return toEmail;
	}

	public Long getResentCount() {
		return resentCount;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public void setResentCount(Long resentCount) {
		this.resentCount = resentCount;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Notification getNotification() {
		return this.notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	protected String getLogMessage() {
		return logMessage;
	}

	protected void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

	protected String getLogResponse() {
		return logResponse;
	}

	protected void setLogResponse(String logResponse) {
		this.logResponse = logResponse;
	}

	protected String getMobile() {
		return mobile;
	}

	protected void setMobile(String mobile) {
		this.mobile = mobile;
	}

	protected String getNotificationType() {
		return notificationType;
	}

	protected void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
}