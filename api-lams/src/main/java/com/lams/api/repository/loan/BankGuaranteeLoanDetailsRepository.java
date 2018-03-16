package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.loan.BankGuaranteeLoanDetails;

public interface BankGuaranteeLoanDetailsRepository extends JpaRepository<BankGuaranteeLoanDetails, Long> {

	public BankGuaranteeLoanDetails findByIdAndIsActive(Long id,Boolean isActive);
}
