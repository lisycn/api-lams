package com.lams.api.service.loan;

import com.lams.model.loan.bo.HomeLoanDetailsBO;

public interface HomeLoanDetailsService {
	
	public Long save(HomeLoanDetailsBO requestLoanDetailsBO);
	
	public HomeLoanDetailsBO get(Long id);

}
