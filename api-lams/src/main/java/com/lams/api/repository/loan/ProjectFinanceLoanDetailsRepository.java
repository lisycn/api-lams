package com.lams.api.repository.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.loan.ProjectFinanceLoanDetails;

public interface ProjectFinanceLoanDetailsRepository extends JpaRepository<ProjectFinanceLoanDetails, Long>{

	public ProjectFinanceLoanDetails findByIdAndIsActive(Long id,Boolean isActive);

}

