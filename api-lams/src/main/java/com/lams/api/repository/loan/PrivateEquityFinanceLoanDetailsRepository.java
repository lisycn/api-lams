package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.loan.PrivateEquityFinanceLoanDetails;

public interface PrivateEquityFinanceLoanDetailsRepository extends JpaRepository<PrivateEquityFinanceLoanDetails, Long> {

	public PrivateEquityFinanceLoanDetails findByIdAndIsActive(Long id,Boolean isActive);
}
