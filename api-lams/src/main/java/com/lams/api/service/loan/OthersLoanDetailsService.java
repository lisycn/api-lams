package com.lams.api.service.loan;

import com.lams.model.loan.bo.OthersLoanDetailsBO;

public interface OthersLoanDetailsService{
	
	public OthersLoanDetailsBO get(Long id);
	
	public Long save(OthersLoanDetailsBO requestLoanDetailsBO);


}
