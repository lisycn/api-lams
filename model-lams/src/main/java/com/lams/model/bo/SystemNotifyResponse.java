package com.lams.model.bo;

import java.sql.Timestamp;

/**
 * @author Akshay
 *
 */
public class SystemNotifyResponse {

	private Long status;
	private String message;
	private String sysNotifyMessage;
	private Long id;
	private Long productId;
	private Long applicationId;
	private Long userId;
	private Timestamp appCreatedDate;

	public Timestamp getAppCreatedDate() {
		return appCreatedDate;
	}

	public void setAppCreatedDate(Timestamp appCreatedDate) {
		this.appCreatedDate = appCreatedDate;
	}

	public Long getProductId() {
		return productId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSysNotifyMessage() {
		return sysNotifyMessage;
	}

	public void setSysNotifyMessage(String sysNotifyMessage) {
		this.sysNotifyMessage = sysNotifyMessage;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
