package com.lams.api.service.loan;

import com.lams.model.loan.bo.CCFacilitiesLoanDetailsBO;

public interface CCFacilitiesLoanDetailsService{

	public Long save(CCFacilitiesLoanDetailsBO requestLoanDetailsBO);
	
	public CCFacilitiesLoanDetailsBO get(Long id);

}
