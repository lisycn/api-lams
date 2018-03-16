package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.loan.EducationLoanDetails;

public interface EducationLoanDetailsRepository extends JpaRepository<EducationLoanDetails, Long> {

	public EducationLoanDetails findByIdAndIsActive(Long id,Boolean isActive);
}
