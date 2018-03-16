package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.loan.TermLoanDetails;

public interface TermLoanDetailsRepository extends JpaRepository<TermLoanDetails, Long>{

	public TermLoanDetails findByIdAndIsActive(Long id,Boolean isActive);
}
