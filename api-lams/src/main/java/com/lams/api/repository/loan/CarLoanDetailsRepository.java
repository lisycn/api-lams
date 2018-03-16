package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.loan.CarLoanDetails;

public interface CarLoanDetailsRepository extends JpaRepository<CarLoanDetails, Long> {

	public CarLoanDetails findByIdAndIsActive(Long id,Boolean isActive);
}

