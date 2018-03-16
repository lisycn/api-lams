package com.lams.api.service.loan;

import com.lams.model.loan.bo.SecuredBusinessLoanDetailsBO;

public interface SecuredBusinessLoanDetailsService {

	public SecuredBusinessLoanDetailsBO get(Long id);
	
	public Long save(SecuredBusinessLoanDetailsBO requestLoanDetailsBO);
}
