package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.loan.LoanAgainstSecuritiesLoanDetails;

public interface LoanAgainstSecuritiesLoanDetailsRepository extends JpaRepository<LoanAgainstSecuritiesLoanDetails, Long> {

	public LoanAgainstSecuritiesLoanDetails findByIdAndIsActive(Long id,Boolean isActive);

}

