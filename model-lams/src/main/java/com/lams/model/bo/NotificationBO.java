package com.lams.model.bo;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Akshay
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationBO implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1571195060508109481L;
	private String clientRefId;
	private int retryCount;
	private Long startTime;
	private String fromName;
	private List<NotificationMainBO> notifications = null;;
	private Long applicationId;
	private Long productId;
	private String userType;
	private Integer pageIndex;
	private Integer size;
	private Long clientId;

	public NotificationBO() {
		notifications = Collections.emptyList();
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public Integer getSize() {
		return size;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getStartTime() {
		return startTime;
	}

	public String getClientRefId() {
		return clientRefId;
	}

	public void setClientRefId(String clientRefId) {
		this.clientRefId = clientRefId;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public List<NotificationMainBO> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<NotificationMainBO> notifications) {
		this.notifications = notifications;
	}
}
