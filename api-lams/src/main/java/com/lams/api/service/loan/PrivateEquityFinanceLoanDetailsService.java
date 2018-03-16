package com.lams.api.service.loan;

import com.lams.model.loan.bo.PrivateEquityFinanceLoanDetailsBO;

public interface PrivateEquityFinanceLoanDetailsService{
	
	public PrivateEquityFinanceLoanDetailsBO get(Long id);

	public Long save(PrivateEquityFinanceLoanDetailsBO requestLoanDetailsBO);
}
