package com.lams.api.service.loan;

import com.lams.model.loan.bo.ProjectFinanceLoanDetailsBO;

public interface ProjectFinanceLoanDetailsService{

	public ProjectFinanceLoanDetailsBO get(Long id);
	
	public Long save(ProjectFinanceLoanDetailsBO requestLoanDetailsBO);

}

