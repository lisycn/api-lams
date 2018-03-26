package com.lams.api.service.payment;

import java.util.Map;

import com.lams.model.bo.PaymentBO;

public interface PaymentService {

	public Map<String, String> payout(PaymentBO request) throws Exception;

	public Boolean updateStatus(PaymentBO request) throws Exception;
	
	public PaymentBO getPaymentByStatus(PaymentBO request) throws Exception;

}
