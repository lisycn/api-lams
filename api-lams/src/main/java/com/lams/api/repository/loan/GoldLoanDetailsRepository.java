package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.loan.GoldLoanDetails;

public interface GoldLoanDetailsRepository extends JpaRepository<GoldLoanDetails, Long> {
	public GoldLoanDetails findByIdAndIsActive(Long id, Boolean isActive);
}
