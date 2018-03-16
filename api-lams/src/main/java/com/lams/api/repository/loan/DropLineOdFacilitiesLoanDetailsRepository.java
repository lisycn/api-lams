package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.loan.DropLineOdFacilitiesLoanDetails;

public interface DropLineOdFacilitiesLoanDetailsRepository extends JpaRepository<DropLineOdFacilitiesLoanDetails, Long>  {
	
	public DropLineOdFacilitiesLoanDetails findByIdAndIsActive(Long id,Boolean isActive);
}

