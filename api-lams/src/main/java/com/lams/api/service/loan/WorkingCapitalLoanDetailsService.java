package com.lams.api.service.loan;

import com.lams.model.loan.bo.WorkingCapitalLoanDetailsBO;

public interface WorkingCapitalLoanDetailsService {
	
	public WorkingCapitalLoanDetailsBO get(Long id);
	
	public Long save(WorkingCapitalLoanDetailsBO requestLoanDetailsBO);

}
