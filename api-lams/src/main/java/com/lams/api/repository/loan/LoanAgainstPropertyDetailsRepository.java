package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.loan.LoanAgainstPropertyDetails;

public interface LoanAgainstPropertyDetailsRepository extends JpaRepository<LoanAgainstPropertyDetails, Long>{
	public LoanAgainstPropertyDetails findByIdAndIsActive(Long id,Boolean isActive);
}
