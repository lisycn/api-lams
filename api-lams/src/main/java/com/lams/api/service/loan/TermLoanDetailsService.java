package com.lams.api.service.loan;

import com.lams.model.loan.bo.TermLoanDetailsBO;

public interface TermLoanDetailsService{

	public TermLoanDetailsBO get(Long id);
	
	public Long save(TermLoanDetailsBO requestLoanDetailsBO);
}
