package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.loan.OthersLoanDetails;

public interface OthersLoanDetailsRepository extends JpaRepository<OthersLoanDetails, Long> {

	public OthersLoanDetails findByIdAndIsActive(Long id,Boolean isActive);

}
