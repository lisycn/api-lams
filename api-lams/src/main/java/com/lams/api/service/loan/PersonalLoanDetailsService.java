package com.lams.api.service.loan;

import com.lams.model.loan.bo.PersonalLoanDetailsBO;

public interface PersonalLoanDetailsService {

	public PersonalLoanDetailsBO get(Long id);
	
	public Long save(PersonalLoanDetailsBO requestLoanDetailsBO);

}
