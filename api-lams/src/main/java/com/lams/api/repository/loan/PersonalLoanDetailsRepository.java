package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.loan.PersonalLoanDetails;

public interface PersonalLoanDetailsRepository extends JpaRepository<PersonalLoanDetails, Long> {

	public PersonalLoanDetails findByIdAndIsActive(Long id,Boolean isActive);
}
