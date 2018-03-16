package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.loan.HomeLoanDetails;

public interface HomeLoanDetailsRepository extends JpaRepository<HomeLoanDetails, Long> {
	public HomeLoanDetails findByIdAndIsActive(Long id,Boolean isActive);
}
