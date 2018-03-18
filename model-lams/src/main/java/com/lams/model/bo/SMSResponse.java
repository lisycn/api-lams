package com.lams.model.bo;

/**
 * @author Akshay
 *
 */
public class SMSResponse extends NotificationResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -251269077023346693L;
	private String smsMessage;

	public String getSmsMessage() {
		return smsMessage;
	}

	public void setSmsMessage(String smsMessage) {
		this.smsMessage = smsMessage;
	}

	@Override
	public String toString() {
		return "SMSResponse [smsMessage=" + smsMessage + "]";
	}
}
