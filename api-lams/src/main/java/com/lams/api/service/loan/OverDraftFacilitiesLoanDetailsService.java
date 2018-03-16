package com.lams.api.service.loan;

import com.lams.model.loan.bo.OverDraftFacilitiesLoanDetailsBO;

public interface OverDraftFacilitiesLoanDetailsService {
	
	public OverDraftFacilitiesLoanDetailsBO get(Long id);
	
	public Long save(OverDraftFacilitiesLoanDetailsBO requestLoanDetailsBO);

}
