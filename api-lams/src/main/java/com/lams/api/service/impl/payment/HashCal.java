package com.lams.api.service.impl.payment;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lams.model.bo.PaymentBO;

@Component
public class HashCal {

	@Value("${com.lams.payment.salt}")
	private String salt;

	@Value("${com.lams.payment.key}")
	private String key;

	@Value("${com.lams.payment.successUrl}")
	private String successUrl;

	@Value("${com.lams.payment.failureUrl}")
	private String failureUrl;

	@Value("${com.lams.payment.serviceProvider}")
	private String serviceProvider;

	public boolean empty(String s) {
		if (s == null || s.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public String hashCal(String type, String str) {
		byte[] hashseq = str.getBytes();
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest algorithm = MessageDigest.getInstance(type);
			algorithm.reset();
			algorithm.update(hashseq);
			byte messageDigest[] = algorithm.digest();
			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1) {
					hexString.append("0");
				}
				hexString.append(hex);
			}

		} catch (NoSuchAlgorithmException nsae) {
		}
		return hexString.toString();
	}

	protected Map<String, String> hashCalMethod(PaymentBO request) throws ServletException, IOException {

		String hash = "";

		String action1 = "";

		String base_url = "https://secure.payu.in";

		// String base_url = "https://sandboxsecure.payu.in";

		String hashString = "";
		String txnid = "";

		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> urlParams = new HashMap<String, String>();

		params.put("key", key);
		params.put("txnid", "");
		params.put("amount", Double.toString(request.getAmount()));
		params.put("productinfo", request.getProductInfo());
		params.put("firstname", request.getFirstName());
		params.put("email", request.getEmail());
		params.put("phone", request.getPhone());
		params.put("hash", "");
		params.put("service_provider", serviceProvider);
		params.put("udf1",request.getApplicationId().toString());
		System.out.println("-----------------------------------");
		System.out.println("Salt is:------------" + salt);

		if (empty(params.get("txnid"))) {
			Random rand = new Random();
			String rndm = Integer.toString(rand.nextInt()) + (System.currentTimeMillis() / 1000L);
			txnid = rndm;
			params.remove("txnid");
			params.put("txnid", txnid);
			txnid = hashCal("SHA-256", rndm).substring(0, 20);
		} else {
			txnid = params.get("txnid");
		}
		params.put("surl", successUrl );//+ "?txnId=" + txnid + "&result=" + CommonUtils.PaymentStatus.SUCCESS + "&app=" + request.getApplicationId());
		params.put("furl", failureUrl);// + "?txnId=" + txnid + "&result=" + CommonUtils.PaymentStatus.FAILED+ "&app=" + request.getApplicationId());

		String otherPostParamSeq = "phone|surl|furl";
		String hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
		if (empty(params.get("hash")) && params.size() > 0) {
			if (empty(params.get("key")) || empty(txnid) || empty(params.get("amount"))
					|| empty(params.get("firstname")) || empty(params.get("email")) || empty(params.get("phone"))
					|| empty(params.get("productinfo")) || empty(params.get("surl")) || empty(params.get("furl"))
					|| empty(params.get("service_provider"))) {
			} else {

				String[] hashVarSeq = hashSequence.split("\\|");
				for (String part : hashVarSeq) {
					if (part.equals("txnid")) {
						hashString = hashString + txnid;
						urlParams.put("txnid", txnid);
					} else {
						hashString = (empty(params.get(part))) ? hashString.concat("")
								: hashString.concat(params.get(part).trim());
						urlParams.put(part, empty(params.get(part)) ? "" : params.get(part).trim());
					}
					hashString = hashString.concat("|");
				}

				hashString = hashString.concat(salt);
				hash = hashCal("SHA-512", hashString);
				action1 = base_url.concat("/_payment");
				String[] otherPostParamVarSeq = otherPostParamSeq.split("\\|");
				for (String part : otherPostParamVarSeq) {
					urlParams.put(part, empty(params.get(part)) ? "" : params.get(part).trim());
				}

			}
		} else if (!empty(params.get("hash"))) {
			hash = params.get("hash");
			action1 = base_url.concat("/_payment");
		}

		urlParams.put("hash", hash);
		urlParams.put("action", action1);
		urlParams.put("hashString", hashString);
		return urlParams;
	}

	public static void trustSelfSignedSSL() {
		try {
			final SSLContext ctx = SSLContext.getInstance("TLS");
			final X509TrustManager tm = new X509TrustManager() {
				@Override
				public void checkClientTrusted(final X509Certificate[] xcs, final String string)
						throws CertificateException {
					// do nothing
				}

				@Override
				public void checkServerTrusted(final X509Certificate[] xcs, final String string)
						throws CertificateException {
					// do nothing
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLContext.setDefault(ctx);
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}

}