package com.lams.api.utils;

import java.util.Random;

public class ServiceUtils {

	private static String CHAR_LIST_DEFAULT = "0123456789";

	private static int generate(String CHAR_LIST) {
		if (CHAR_LIST == null || CHAR_LIST.isEmpty() || CHAR_LIST == "") {
			CHAR_LIST = CHAR_LIST_DEFAULT;
		}

		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	/**
	 * generate OTP based on passed string by user and digit length.
	 * 
	 * @param digitLength
	 *            OTP length
	 * @param CHAR_LIST
	 *            from String it will generate OTP
	 * @return
	 */
	public static String generateOTP(int digitLength, String CHAR_LIST) {
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < digitLength; i++) {
			int number = generate(CHAR_LIST);
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	/**
	 * generate OTP based on passed string by user and digit length.
	 * 
	 * @param digitLength
	 *            OTP length with default Character List
	 * @return
	 */
	public static String generateOTP(int digitLength) {
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < digitLength; i++) {
			int number = generate(CHAR_LIST_DEFAULT);
			char ch = CHAR_LIST_DEFAULT.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}
}
