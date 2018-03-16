package com.lams.api.service.loan;

import com.lams.model.loan.bo.LoanAgainstPropertyDetailsBO;

public interface LoanAgainstPropertyDetailsService {
	
	public LoanAgainstPropertyDetailsBO get(Long id);
	
	public Long save(LoanAgainstPropertyDetailsBO requestLoanDetailsBO);

}
