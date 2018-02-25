package com.lams.model.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CommonUtils {

	public static List<String> lamsUrls = null;
	public static final String USER_ID = "userId";
	public static final String USER_TYPE = "userType";
	public static final String SOMETHING_WENT_WRONG = "Something Went Wrong !";
	public static final String INVALID_REQUEST = "Invalid Request !";

	static {
		lamsUrls = new ArrayList<String>();
		lamsUrls.add("/login");
		lamsUrls.add("/logout");
		lamsUrls.add("/registration");

	}

	public static boolean isListNullOrEmpty(Collection<?> data) {
		return (data == null || data.isEmpty());
	}

	public static boolean isObjectNullOrEmpty(Object value) {
		return (value == null
				|| (value instanceof String
						? (((String) value).isEmpty() || "".equals(((String) value).trim()) || "null".equals(value)
								|| "undefined".equals(value))
						: false));
	}

	public static boolean isObjectNullOrEmptyOrDash(Object value) {
		return (value == null || (value instanceof String
				? (((String) value).isEmpty() || "".equals(((String) value).trim()) || "null".equals(value)
						|| "-".equals(value) || "undefined".equals(value))
				: false));
	}

	public enum UserType {
		ALL(-1, "All"), BORROWER(1, "Borrower"), LENDER(2, "Lender");

		private int id;
		private String value;

		private UserType(int id, String value) {
			this.id = id;
			this.value = value;
		}

		public int getId() {
			return id;
		}

		public String getValue() {
			return value;
		}

		public static UserType getType(Integer x) {
			switch (x) {
			case -1:
				return ALL;
			case 1:
				return BORROWER;
			case 2:
				return LENDER;
			default:
				return null;
			}
		}

		public static UserType[] getAll() {
			return UserType.values();
		}

	}

	public enum Mode {
		ACTIVE(0, "Active"), INACTIVE(1, "InActive"), BOTH(2, "Both");

		private Integer id;
		private String value;

		private Mode(Integer id, String value) {
			this.id = id;
			this.value = value;
		}

		public int getId() {
			return id;
		}

		public String getValue() {
			return value;
		}

		public static Mode getType(Integer x) {
			switch (x) {
			case 0:
				return ACTIVE;
			case 1:
				return INACTIVE;
			case 2:
				return BOTH;
			default:
				return null;
			}
		}

		public static Mode[] getAll() {
			return Mode.values();
		}
	}

	public static float findDiffBetTwoDate(Date dateFrom) throws ParseException {
		Date dateTo = new Date();
		long difference = dateTo.getTime() - dateFrom.getTime();
		float daysBetween = (difference / (1000 * 60 * 60 * 24));
		return daysBetween;
	}

	public static String generateRandomToken() {
		return UUID.randomUUID().toString();
	}

}
