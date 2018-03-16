package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.loan.SecuredBusinessLoanDetails;

public interface SecuredBusinessLoanDetailsRepository extends JpaRepository<SecuredBusinessLoanDetails, Long> {

	public SecuredBusinessLoanDetails findByIdAndIsActive(Long id,Boolean isActive);
}
