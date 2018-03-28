package com.lams.api.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.payment.PaymentDetails;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {

	public PaymentDetails findOneByApplicationIdAndIsActive(Long applicationId, Boolean isActive);

}
