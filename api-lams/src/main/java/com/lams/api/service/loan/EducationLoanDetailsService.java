package com.lams.api.service.loan;

import com.lams.model.loan.bo.EducationLoanDetailsBO;

public interface EducationLoanDetailsService {

	public EducationLoanDetailsBO get(Long id);
	
	public Long save(EducationLoanDetailsBO requestLoanDetailsBO);
}
