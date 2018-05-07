package com.lams.model.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CommonUtils {

	public static List<String> lamsUrls = null;
	public static final String USER_ID = "userId";
	public static final String USER_TYPE = "userType";
	public static final String VS = "VS";
	public static final String VSCP = "VSCP";	
	public static final String SOMETHING_WENT_WRONG = "Something Went Wrong !";
	public static final String INVALID_REQUEST = "Invalid Request !";
	public static final String DEFAULT_FORMATE = "MM/dd/yyyy HH:mm:ss";
	public static final String CHAR_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public interface Status {
		public static final String OPEN = "OPEN";
		public static final String RESPONDED = "RESPONDED";
		public static final String ACCEPTED = "ACCEPTED";
		public static final String REJECTED = "REJECTED";
		public static final String NOTINTERESTED = "NOTINTERESTED";
	}

	static {
		lamsUrls = new ArrayList<String>();
		lamsUrls.add("/login");
		lamsUrls.add("/logout");
		lamsUrls.add("/registration");
		lamsUrls.add("/resend_otp/{type}/{templateName}");
		lamsUrls.add("/verify_otp/{type}");
		lamsUrls.add("/verify_email/{link}");
		lamsUrls.add("/send_link");
		lamsUrls.add("/reset_password/{link}");
	}

	private CommonUtils() {

	}

	public static final String[] skipFieldsForCreateApp = { "id", "createdBy", "modifiedDate", "createdDate",
			"modifiedBy", "userId", "isActive", "leadReferenceNo" };

	public static boolean isListNullOrEmpty(Collection<?> data) {
		return (data == null || data.isEmpty());
	}

	public static boolean isMapNullOrEmpty(Map<?, ?> data) {
		return (data == null || data.isEmpty());
	}

	public static boolean isObjectNullOrEmpty(Object value) {
		return (value == null
				|| (value instanceof String
						? (((String) value).isEmpty() || "".equals(((String) value).trim()) || "null".equals(value)
								|| "undefined".equals(value))
						: false));
	}

	public static boolean isObjectListNull(Object... args) {
		for (Object object : args) {
			boolean flag = false;
			if (object instanceof List) {
				flag = isListNullOrEmpty((List<?>) object);
				if (flag)
					return true;
				else
					continue;
			} else if (object instanceof Map) {
				flag = isMapNullOrEmpty((Map<?, ?>) object);
				if (flag)
					return true;
				else
					continue;
			}
			flag = isObjectNullOrEmpty(object);
			if (flag)
				return true;
		}
		return false;
	}

	public static float findDiffBetTwoDate(Date dateFrom) throws ParseException {
		Date dateTo = new Date();
		long difference = dateTo.getTime() - dateFrom.getTime();
		return (difference / (1000 * 60 * 60 * 24));
	}

	public static String generateRandomToken() {
		return UUID.randomUUID().toString();
	}

	public static Timestamp getCurrentTimeStamp() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}

	public interface NotificationProperty {
		public static final Long STATUS_SUCCESSFULL = 200L;
		public static final Long STATUS_FAILURE = 500L;
		public static final Long STATUS_NOTIFICATION_SENDING_FAILURE = 401L;
		public static final Long STATUS_MAIL_TEMPLATE_NOTFOUND = 402L;

		public static final Long STATUS_SMS_SENDING_FAILURE = 403L;
		public static final Long STATUS_SMS_TEMPLATE_NOTFOUND = 405L;

		public static final Long RECENT_VIEW_NOTIFICATION_LIMIT = 4L;

		public static final Long SUCCESSFUL = 1L;
		public static final Long FAILURE = 0L;

	}

	public interface AddressType {
		public static final int PERMANENT = 1;
		public static final int COMMUNICATION = 2;
		public static final int EMPLOYMENT_ADD = 3;
	}

	public interface LoanType {
		public static final int EXISTING_LOAN = 22;
		public static final int CURRENT_LOAN = 23;
		public static final int CLOSED_LOAN = 25;
	}

	public interface ApplicationType {

		public static final int HOME_LOAN = 5;
		public static final int LOAN_AGAINST_PROPERTY = 6;
		public static final int SECURED_BUSINESS_LOAN = 7;
		public static final int WORKING_CAPITAL_LOAN = 8;
		public static final int EDUCATION_LOAN = 9;
		public static final int CAR_LOAN = 10;
		public static final int OVERDRAFT_FACILITIES_LOAN = 11;
		public static final int DROPLINE_OVERDRAFT_FACILITIES_LOAN = 12;
		public static final int BANK_GUARANTEE_LOAN = 13;
		public static final int CC_FACILITIES_LOAN = 14;
		public static final int TERM_LOAN = 15;
		public static final int LOAN_AGAINST_FDS = 16;
		public static final int LOAN_AGAINST_SECURITIS = 17;
		public static final int PROJECT_FINANCE_LOAN = 18;
		public static final int PRIVATE_EQUITY_FINANCE_LOAN = 19;
		public static final int GOLD_LOAN = 20;
		public static final int OTHER_LOAN = 21;
		public static final int PERSONAL_LOAN = 27;

	}

	public interface DateTime {
		public static final int DAY = 1;
		public static final int HOUR = 2;
		public static final int MINUTES = 3;
		public static final int SECONDS = 4;
		public static final int MILISECONDS = 5;
	}

	public static long getDateDifference(Date toDate, Date fromDate, int returnType) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMATE);

		fromDate = format.parse(format.format(fromDate));
		toDate = format.parse(format.format(toDate));
		long diff = toDate.getTime() - fromDate.getTime();
		long result = 0l;

		switch (returnType) {
		case DateTime.DAY:
			result = diff / (24 * 60 * 60 * 1000);
			break;
		case DateTime.HOUR:
			result = diff / (60 * 60 * 1000) % 24;
			break;
		case DateTime.MINUTES:
			result = diff / (60 * 1000) % 60;
			break;
		case DateTime.SECONDS:
			result = diff / 1000 % 60;
			break;
		case DateTime.MILISECONDS:
			result = diff;
			break;
		default:
			break;
		}
		return result;
	}

	public interface ApplicationTypeCode {

		public static final String HOME_LOAN = "HL";
		public static final String LOAN_AGAINST_PROPERTY = "LAP";
		public static final String SECURED_BUSINESS_LOAN = "SBL";
		public static final String WORKING_CAPITAL_LOAN = "WC";
		public static final String EDUCATION_LOAN = "EL";
		public static final String CAR_LOAN = "CL";
		public static final String OVERDRAFT_FACILITIES_LOAN = "ODL";
		public static final String DROPLINE_OVERDRAFT_FACILITIES_LOAN = "DLOF";
		public static final String BANK_GUARANTEE_LOAN = "BG";
		public static final String CC_FACILITIES_LOAN = "CCF";
		public static final String TERM_LOAN = "TL";
		public static final String LOAN_AGAINST_FDS = "LAF";
		public static final String LOAN_AGAINST_SECURITIS = "LAS";
		public static final String PROJECT_FINANCE_LOAN = "PFL";
		public static final String PRIVATE_EQUITY_FINANCE_LOAN = "PEF";
		public static final String GOLD_LOAN = "GL";
		public static final String OTHER_LOAN = "OL";
		public static final String PERSONAL_LOAN = "PL";

	}

	public static String generateRefNo(String applicationCode, String code) {
		if (!CommonUtils.isObjectNullOrEmpty(code)) {
			String[] codes = code.split("-");
			if (codes.length > 2) {
				return VS + "-" + applicationCode + "-" + String.format("%06d", Integer.valueOf(codes[2]) + 1);
			}
		}
		return VS + "-" + applicationCode + "-" + String.format("%06d", 1);
	}
	
	public static String generateRefNoFromCP(String applicationCode, String code,String cpCode) {
		if (!CommonUtils.isObjectNullOrEmpty(code)) {
			String[] codes = code.split("-");
			if (codes.length > 2) {
				return VSCP + "-" + codes[1] + "-" + applicationCode + "-" + String.format("%06d", Integer.valueOf(codes[codes.length - 1]) + 1);
			}
		}
		return cpCode + "-" + applicationCode + "-" + String.format("%06d", 1);
	}
	
	public static String generateCPCode(Long count) {
			return VSCP + "-" + String.format("%03d", count + 1);
	}
	
	public interface EmploymentType {
		public static final Long SALARIED = 1l;
		public static final Long SELF_EMPLOYED = 2l;
	}

	public enum DocumentType {

		PAN_CARD(1l, "Pan Card"), AADHAR_CARD(2l, "Aadhaar Card"), 
		LAST_3_MONTH_SALARY_SLIP(3l,"Last 3 Month Salary Slip"), 
		LAST_6_MONTHS_BANK_ACCOUNT_STATEMENT(4l, "Last 6 Months Bank Account Statement"), 
		FORM_16_OR_APPOIMENT_LETTER(5l, "Form 16 or Appointment Letter"), 
		INVESTMENT_PROOFS(6l, "Investment Proofs"), 
		EXISTING_LOAN_DOCUMENT(7l,"Existing Loan Document"), 
		OTHER_DOCUMENT(8l,"Others Documemnts"), 
		CORPORATE_ITR_SET_YEAR1(10l,"Corporate Income Tax Return Set Year 1 ( ITR / Computation of Income / Financials )"), 
		CORPORATE_ITR_SET_YEAR2(11l,"Corporate Income Tax Return Set Year 2 ( ITR / Computation of Income / Financials )"), 
		CORPORATE_ITR_SET_YEAR3(12l,"Corporate Income Tax Return Set Year 3 ( ITR / Computation of Income / Financials )"), 
		CORPORATE_BANK_ACCOUNT_STATEMENT(13l,"Corporate Bank Account Statements"), 
		INDIVIDUAL_ITR_SET_YEAR1(14l,"Individual Income Tax Return Set Year 1 ( ITR / Computation of Income / Financials )"), 
		INDIVIDUAL_ITR_SET_YEAR2(15l,"Individual Income Tax Return Set Year 2 ( ITR / Computation of Income / Financials )"), 
		INDIVIDUAL_ITR_SET_YEAR3(16l,"Individual Income Tax Return Set Year 3 ( ITR / Computation of Income / Financials )"), 
		INDIVIDUAL_BANK_ACCOUNT_STATEMENT(17l,"Individual Bank Account Statements");

		private Long id;
		private String name;

		private DocumentType(Long id, String name) {
			this.id = id;
			this.name = name;
		}

		public Long getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public static DocumentType getDocumentType(Long id) {
			if (id == PAN_CARD.getId()) {
				return DocumentType.PAN_CARD;
			} else if (id == AADHAR_CARD.getId()) {
				return DocumentType.AADHAR_CARD;
			} else if (id == LAST_3_MONTH_SALARY_SLIP.getId()) {
				return DocumentType.LAST_3_MONTH_SALARY_SLIP;
			} else if (id == LAST_6_MONTHS_BANK_ACCOUNT_STATEMENT.getId()) {
				return DocumentType.LAST_6_MONTHS_BANK_ACCOUNT_STATEMENT;
			} else if (id == FORM_16_OR_APPOIMENT_LETTER.getId()) {
				return DocumentType.FORM_16_OR_APPOIMENT_LETTER;
			} else if (id == INVESTMENT_PROOFS.getId()) {
				return DocumentType.INVESTMENT_PROOFS;
			} else if (id == EXISTING_LOAN_DOCUMENT.getId()) {
				return DocumentType.EXISTING_LOAN_DOCUMENT;
			} else if (id == OTHER_DOCUMENT.getId()) {
				return DocumentType.OTHER_DOCUMENT;
			} else if (id == CORPORATE_ITR_SET_YEAR1.getId()) {
				return DocumentType.CORPORATE_ITR_SET_YEAR1;
			} else if (id == CORPORATE_ITR_SET_YEAR2.getId()) {
				return DocumentType.CORPORATE_ITR_SET_YEAR2;
			} else if (id == CORPORATE_ITR_SET_YEAR3.getId()) {
				return DocumentType.CORPORATE_ITR_SET_YEAR3;
			} else if (id == CORPORATE_BANK_ACCOUNT_STATEMENT.getId()) {
				return DocumentType.CORPORATE_BANK_ACCOUNT_STATEMENT;
			} else if (id == INDIVIDUAL_ITR_SET_YEAR1.getId()) {
				return DocumentType.INDIVIDUAL_ITR_SET_YEAR1;
			} else if (id == INDIVIDUAL_ITR_SET_YEAR2.getId()) {
				return DocumentType.INDIVIDUAL_ITR_SET_YEAR2;
			} else if (id == INDIVIDUAL_ITR_SET_YEAR3.getId()) {
				return DocumentType.INDIVIDUAL_ITR_SET_YEAR3;
			} else if (id == INDIVIDUAL_BANK_ACCOUNT_STATEMENT.getId()) {
				return DocumentType.INDIVIDUAL_BANK_ACCOUNT_STATEMENT;
			}

			return null;
		}

		public static DocumentType[] getAll() {
			return DocumentType.values();
		}
		
		public static List<DocumentType> getAllByEmpType(Long employmentType) {
			List<DocumentType> docList = new ArrayList();
			if(EmploymentType.SALARIED == employmentType) {
				docList.add(PAN_CARD);
				docList.add(AADHAR_CARD);
				docList.add(LAST_3_MONTH_SALARY_SLIP);
				docList.add(LAST_6_MONTHS_BANK_ACCOUNT_STATEMENT);
				docList.add(FORM_16_OR_APPOIMENT_LETTER);
				docList.add(INVESTMENT_PROOFS);
				docList.add(EXISTING_LOAN_DOCUMENT);
				docList.add(OTHER_DOCUMENT);
			} else if(EmploymentType.SELF_EMPLOYED == employmentType) {
				docList.add(PAN_CARD);
				docList.add(AADHAR_CARD);
				docList.add(INDIVIDUAL_ITR_SET_YEAR1);
				docList.add(INDIVIDUAL_ITR_SET_YEAR2);
				docList.add(INDIVIDUAL_ITR_SET_YEAR3);
				docList.add(CORPORATE_BANK_ACCOUNT_STATEMENT);
				docList.add(CORPORATE_ITR_SET_YEAR1);
				docList.add(CORPORATE_ITR_SET_YEAR2);
				docList.add(CORPORATE_ITR_SET_YEAR3);
				docList.add(INVESTMENT_PROOFS);
				docList.add(EXISTING_LOAN_DOCUMENT);
				docList.add(OTHER_DOCUMENT);
			}
			return docList;
		}

	}

	public interface PaymentStatus {
		public static final String SUCCESS = "Success";
		public static final String PENDING = "Pending";
		public static final String FAILED = "Failed";

	}

	public interface PaymentType {

		public static final String CASH = "Cash";
		public static final String CHEQUE = "Cheque";
		public static final String ONLINE = "Online";
		public static final Long PAYMENTTO = 1L;
		public static final Long SERVICE_PROVIDER = 1L;
	}
}
