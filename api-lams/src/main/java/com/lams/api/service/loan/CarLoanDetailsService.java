package com.lams.api.service.loan;

import com.lams.model.loan.bo.CarLoanDetailsBO;

public interface CarLoanDetailsService{

	public Long save(CarLoanDetailsBO requestLoanDetailsBO);
	
	public CarLoanDetailsBO get(Long id);
}

