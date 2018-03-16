package com.lams.api.service.loan;

import com.lams.model.loan.bo.DropLineOdFacilitiesLoanDetailsBO;

public interface DropLineOdFacilitiesLoanDetailsService {
	
	public Long save(DropLineOdFacilitiesLoanDetailsBO requestLoanDetailsBO);
	
	public DropLineOdFacilitiesLoanDetailsBO get(Long id);

}

