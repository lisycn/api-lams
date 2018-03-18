package com.lams.model.bo;

/**
 * @author Akshay
 *
 */
public class EmailResponse extends NotificationResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5755244717360011445L;
	private String emailMessage;

	public String getEmailMessage() {
		return emailMessage;
	}

	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}

	@Override
	public String toString() {
		return "EmailResponse [emailMessage=" + emailMessage + "]";
	}
}
