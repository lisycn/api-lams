package com.lams.api.service.loan;

import com.lams.model.loan.bo.BankGuaranteeLoanDetailsBO;

public interface BankGuaranteeLoanDetailsService{

	public Long save(BankGuaranteeLoanDetailsBO requestLoanDetailsBO);
	
	public BankGuaranteeLoanDetailsBO get(Long id);
}

