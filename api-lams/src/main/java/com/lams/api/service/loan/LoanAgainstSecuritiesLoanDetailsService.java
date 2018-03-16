package com.lams.api.service.loan;

import com.lams.model.loan.bo.LoanAgainstSecuritiesLoanDetailsBO;

public interface LoanAgainstSecuritiesLoanDetailsService{
	
	public LoanAgainstSecuritiesLoanDetailsBO get(Long id);

	public Long save(LoanAgainstSecuritiesLoanDetailsBO requestLoanDetailsBO);

}

