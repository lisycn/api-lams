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
 * The persistent class for the notification_template database table.
 * 
 */
/**
 * @author Akshay
 *
 */
@Entity
@Table(name = "notification_template")
@NamedQuery(name = "NotificationTemplate.findAll", query = "SELECT n FROM NotificationTemplate n")
public class NotificationTemplate extends Auditor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "notification_type")
	private String notificationType;

	@Column(name = "alias")
	private String alias;

	public NotificationTemplate() {
		super();
	}

	protected Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected String getAlias() {
		return alias;
	}

	protected void setAlias(String alias) {
		this.alias = alias;
	}

	protected String getNotificationType() {
		return notificationType;
	}

	protected void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

}