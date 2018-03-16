package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.loan.WorkingCapitalLoanDetails;

public interface WorkingCapitalLoanDetailsRepository extends JpaRepository<WorkingCapitalLoanDetails, Long> {
	public WorkingCapitalLoanDetails findByIdAndIsActive(Long id,Boolean isActive);
}
