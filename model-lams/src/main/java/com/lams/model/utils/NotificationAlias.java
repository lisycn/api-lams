package com.lams.model.utils;

/**
 * @author Akshay
 *
 */
public class NotificationAlias {
	public static final String EMAIL = "email.ftl";
	public static final String EMAIL_LENDER_INVITATION = "email_lender_invitation.ftl";
	public static final String SMS = "sms.ftl";
	public static final String EMAIL_VERIFY_ACCOUNT = "email_verify_account.ftl";
	public static final String FORGOT_PASSWORD_EMAIL = "forgot_password_email.ftl";

	
	public static final String EMAIL_TO_LENDER_WHEN_BR_SUBMIT_FORM = "email_to_lender_when_br_submit_form.ftl";
	public static final String EMAIL_TO_BR_WHEN_BR_SUBMIT_FORM = "email_to_br_when_br_submit_form.ftl";
	public static final String EMAIL_TO_BR_WHEN_LENDER_REVERT_BACK = "email_to_br_when_lender_revert_back.html";
	public static final String EMAIL_TO_LENDER_WHEN_LENDER_REVERT_BACK = "email_to_lender_when_lender_revert_back.html";
	
	public enum DurationType {
		WEEK, MONTH, EARLIER
	}

}
