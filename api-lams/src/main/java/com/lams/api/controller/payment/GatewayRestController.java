package com.lams.api.controller.payment;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lams.api.service.payment.PaymentService;
import com.lams.model.bo.PaymentBO;

@RestController
public class GatewayRestController {

	private static final Logger logger = LoggerFactory.getLogger(GatewayRestController.class.getName());

	@Autowired
	private PaymentService gatewayservice;

	/*
	 * gets the Payment Gateway request parameters from the form and calls the
	 * service method
	 * 
	 */

	@RequestMapping(value = "/pay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> payMoney(@RequestBody PaymentBO request)
			throws ServletException, IOException {
		logger.info("Start payMoney()");
		Map<String, String> values = null;
		try {
			logger.info("Request in payUMoney===>{}", request.toString());
			values = gatewayservice.payout(request);
			logger.info("Response in payUMoney===>{}", values.toString());
			return new ResponseEntity<Map<String, String>>(values, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while calling the payUMoney service");
			return new ResponseEntity<Map<String, String>>(values, HttpStatus.OK);
		}
	}

	/*
	 * for updating the pending status of the transaction to success if transaction
	 * is successful otherwise failed
	 * 
	 */

	@RequestMapping(value = "/update_status", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.ALL_VALUE)
	public ResponseEntity<Boolean> updateStatus(@RequestBody PaymentBO request)
			throws ServletException, IOException {
		logger.info("Updating the status of payment from pending to success or failure==>");
		Boolean status = null;
		try {
			logger.info("Request in update Status==>{}", request.toString());
			System.out.println(request.toString());
			status = gatewayservice.updateStatus(request);
			logger.info("Response as status in update Status==>{}", status);
			return new ResponseEntity<Boolean>(status, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Updating Status");
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/get_payment_by_status", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.ALL_VALUE)
	public ResponseEntity<PaymentBO> getPaymentByStatus(@RequestBody PaymentBO request)
			throws ServletException, IOException {
		logger.info("Start getPaymentByStatus()==>");
		PaymentBO PaymentBO = null;
		try {
			logger.info("Request in getPaymentByStatus==>{}", request.toString());
			PaymentBO = gatewayservice.getPaymentByStatus(request);
			logger.info("Response in getPaymentByStatus==>{}", PaymentBO);
			logger.info("End getPaymentByStatus()==>");
			return new ResponseEntity<PaymentBO>(PaymentBO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while getPaymentByStatus()");
			return new ResponseEntity<PaymentBO>(PaymentBO, HttpStatus.OK);
		}
	}

}