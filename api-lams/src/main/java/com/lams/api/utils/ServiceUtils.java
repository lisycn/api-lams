package com.lams.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;

public class ServiceUtils {

//	public final static String = "";	
//	public static String generateEncryptString(Date signUp, String email) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
//		Date parsedDate;
//		String signUpDate = null;
//		try {
//			parsedDate = sdf.parse(signUp.toString());
//			SimpleDateFormat print = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			signUpDate = print.format(parsedDate);
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		String stringToEncrypt = email + "$" + signUpDate + "$" + UsersUtils.EMAIL_VERIFICATION_URL;
//		String finalString = "";
//		try {
//			finalString = Base64.getEncoder().encodeToString(stringToEncrypt.getBytes("utf-8"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return finalString;
//	}
}
