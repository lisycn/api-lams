package com.lams.model.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Akshay
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 668251732769674954L;
	private Long status;
	private String message;
	private String sentMessage;
	private List<SysNotifyResponse> sysNotification;
	private Long count;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<SysNotifyResponse> getSysNotification() {
		return sysNotification;
	}

	public void setSysNotification(List<SysNotifyResponse> sysNotification) {
		this.sysNotification = sysNotification;
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

	public String getSentMessage() {
		return sentMessage;
	}

	public void setSentMessage(String sentMessage) {
		this.sentMessage = sentMessage;
	}

	@Override
	public String toString() {
		return "NotificationResponse [status=" + status + ", message=" + message + ", sentMessage=" + sentMessage
				+ ", sysNotification=" + sysNotification + ", count=" + count + "]";
	}
}
