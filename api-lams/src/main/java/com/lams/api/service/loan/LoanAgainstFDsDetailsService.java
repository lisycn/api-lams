package com.lams.api.service.loan;

import com.lams.model.loan.bo.LoanAgainstFDsDetailsBO;

public interface LoanAgainstFDsDetailsService {

	public LoanAgainstFDsDetailsBO get(Long id);
	
	public Long save(LoanAgainstFDsDetailsBO requestLoanDetailsBO);
}

