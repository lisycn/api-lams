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
