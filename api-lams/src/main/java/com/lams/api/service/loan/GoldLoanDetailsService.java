package com.lams.api.service.loan;

import com.lams.model.loan.bo.GoldLoanDetailsBO;

public interface GoldLoanDetailsService {

	public Long save(GoldLoanDetailsBO requestLoanDetailsBO);
	
	public GoldLoanDetailsBO get(Long id);
}

