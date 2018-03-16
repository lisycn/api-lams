package com.lams.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lams.api.domain.OtpLoggingDetail;

public interface OTPLoggingRepository extends JpaRepository<OtpLoggingDetail, Long> {

	@Query("select count(log.id) from OtpLoggingDetail log where log.otp =:otp and log.isActive = true and log.isExpired = false and log.isVerified = false and log.type.id =:typeId and log.masterId =:masterId and log.mobileNo =:mobileNo")
	public Long getCountOfValidOTP(@Param("otp") String otp, @Param("typeId") Long typeId,
			@Param("masterId") Long masterId, @Param("mobileNo") String mobileNo);

	@Query("select log from OtpLoggingDetail log where log.otp =:otp and log.isActive = true and log.isExpired = false and log.isVerified = false and log.type.id =:typeId and log.masterId =:masterId and log.mobileNo =:mobileNo")
	public OtpLoggingDetail getOTPDetails(@Param("otp") String otp, @Param("typeId") Long typeId,
			@Param("masterId") Long masterId, @Param("mobileNo") String mobileNo);
	
	@Modifying
	@Query("update OtpLoggingDetail log set log.isActive = false where log.isActive = true and log.isExpired = false and log.isVerified = false and log.type.id =:typeId and log.masterId =:masterId and log.mobileNo =:mobileNo")
	public int inActivePreviousOTP(@Param("typeId") Long typeId,
			@Param("masterId") Long masterId, @Param("mobileNo") String mobileNo);
}
