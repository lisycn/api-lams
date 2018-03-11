package com.lams.model.utils;

import java.sql.Timestamp;
import java.text.ParseException;
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
	public static final String SOMETHING_WENT_WRONG = "Something Went Wrong !";
	public static final String INVALID_REQUEST = "Invalid Request !";

	static {
		lamsUrls = new ArrayList<String>();
		lamsUrls.add("/login");
		lamsUrls.add("/logout");
		lamsUrls.add("/registration");

	}

	private CommonUtils() {

	}

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
}
