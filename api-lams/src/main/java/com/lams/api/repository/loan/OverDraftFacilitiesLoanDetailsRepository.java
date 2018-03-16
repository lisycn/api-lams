package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.loan.OverDraftFacilitiesLoanDetails;

public interface OverDraftFacilitiesLoanDetailsRepository extends JpaRepository<OverDraftFacilitiesLoanDetails, Long> {

	public OverDraftFacilitiesLoanDetails findByIdAndIsActive(Long id,Boolean isActive);
}
