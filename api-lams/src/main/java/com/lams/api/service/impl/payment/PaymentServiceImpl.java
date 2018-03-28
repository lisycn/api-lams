package com.lams.api.service.impl.payment;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lams.api.domain.payment.PaymentHistory;
import com.lams.api.domain.payment.ServiceProvider;
import com.lams.api.repository.payment.PaymentHistoryRepository;
import com.lams.api.service.payment.PaymentService;
import com.lams.model.bo.PaymentBO;
import com.lams.model.utils.CommonUtils;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentHistoryRepository paymentHistoryRepository;

	@Autowired
	private HashCal hashCal;

	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Override
	public Map<String, String> payout(PaymentBO request) throws Exception {
		logger.info("Start payout()");
		try {
			paymentHistoryRepository.setIsActiveFalse(request.getApplicationId(), request.getUserId());
			Map<String, String> values = hashCal.hashCalMethod(request);
			PaymentHistory p = new PaymentHistory();
			ServiceProvider provider = new ServiceProvider();
			BeanUtils.copyProperties(request, p);
			p.setProviderId(provider);
			p.setUserId(request.getUserId());
			p.setTxnId(values.get("txnid"));
			p.setStatus(CommonUtils.PaymentStatus.PENDING);
			p.setCreatedDate(new Date());
			p.setCreatedBy(request.getUserId());
			p.setIsActive(true);
			paymentHistoryRepository.save(p);
			return values;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error while Saving Payment Information");
			throw new Exception();
		}
	}

	@Override
	public Boolean updateStatus(PaymentBO request) throws Exception {
		logger.info("Start updateStatus()");
		try {
			PaymentHistory findByTxnId = paymentHistoryRepository
					.findByTxnIdAndApplicationIdAndIsActive(request.getTxnId(), request.getApplicationId(), true);
			if (findByTxnId != null) {
				findByTxnId.setStatus(request.getStatus());
				findByTxnId.setModifiedBy(request.getUserId());
				findByTxnId.setModifiedDate(new Date());
				if (CommonUtils.PaymentStatus.SUCCESS.equals(request.getStatus())) {
					findByTxnId.setDepositDate(new Date());
				}
				findByTxnId = paymentHistoryRepository.save(findByTxnId);
				logger.info("End updateStatus() with successfully updating ");
				return true;
			} else {
				logger.info("Invalid Transaction Id to update the Status===>" + request.getTxnId());
				logger.info("End updateStatus() with Failed updating ");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error while Updating Status");
			throw new Exception();
		}
	}

	@Override
	public PaymentBO getPaymentByStatus(PaymentBO request) throws Exception {
		logger.info("Start gatPaymentByStatus()");
		try {
			PaymentHistory paymentStatus = new PaymentHistory();
			if (!CommonUtils.isObjectNullOrEmpty(request.getStatus())) {
				paymentStatus = paymentHistoryRepository
						.findByApplicationIdAndStatusAndIsActive(request.getApplicationId(), request.getStatus(), true);
			} else {
				paymentStatus = paymentHistoryRepository.findByApplicationIdAndIsActive(request.getApplicationId(),
						true);
			}

			if (!CommonUtils.isObjectNullOrEmpty(paymentStatus)) {
				logger.info("Response==>{}" + paymentStatus.toString());
				BeanUtils.copyProperties(paymentStatus, request);
				return request;
			} else {
				logger.info("Not Found Record for Application==>and===>Status==>{},{}" + request.getApplicationId(),
						request.getStatus());
				logger.info("End updateStatus()");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error while Updating Payment Status");
			throw new Exception();
		}
	}

}