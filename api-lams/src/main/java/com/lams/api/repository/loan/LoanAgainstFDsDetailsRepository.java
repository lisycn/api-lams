package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.loan.LoanAgainstFDsDetails;

public interface LoanAgainstFDsDetailsRepository extends JpaRepository<LoanAgainstFDsDetails, Long> {
	public LoanAgainstFDsDetails findByIdAndIsActive(Long id,Boolean isActive);
}

