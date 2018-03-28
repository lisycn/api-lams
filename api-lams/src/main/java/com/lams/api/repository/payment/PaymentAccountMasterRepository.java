package com.lams.api.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.payment.PaymentAccountMaster;


public interface PaymentAccountMasterRepository extends JpaRepository<PaymentAccountMaster, Long> {

	public PaymentAccountMaster findOneByIdAndIsActive(Long applicationId,Boolean isActive);

	
	

}
