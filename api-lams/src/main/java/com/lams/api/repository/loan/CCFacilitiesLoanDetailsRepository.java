package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository; 
import com.lams.api.domain.loan.CCFacilitiesLoanDetails;

public interface CCFacilitiesLoanDetailsRepository  extends JpaRepository<CCFacilitiesLoanDetails, Long> {

	public CCFacilitiesLoanDetails findByIdAndIsActive(Long id,Boolean isActive);
}
