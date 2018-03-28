package com.lams.api.repository.payment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lams.api.domain.payment.PaymentHistory;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {

	public PaymentHistory findByTxnIdAndApplicationIdAndIsActive(String txnId, Long applicationId, Boolean isActive);

	public PaymentHistory findByApplicationIdAndStatusAndIsActive(Long applicationId, String status, Boolean isActive);

	public PaymentHistory findByApplicationIdAndIsActive(Long applicationId, Boolean isActive);

	@Modifying
	@Query("update PaymentHistory pm set pm.isActive =false,pm.modifiedDate = NOW(),pm.modifiedBy=:userId where pm.applicationId =:id ")
	public int setIsActiveFalse(@Param("id") Long applicationId, @Param("userId") Long userId);

	@Query("select ph from PaymentHistory ph where ph.status = :status and ph.isPaymentValidated= :isPayValidated  and ph.isActive=true")
	public List<PaymentHistory> findListOfOnlinePayments(@Param("status") String status,
			@Param("isPayValidated") Boolean isPayValidated);
}