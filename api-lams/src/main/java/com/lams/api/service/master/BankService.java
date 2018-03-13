package com.lams.api.service.master;

import java.util.List;

import com.lams.model.bo.master.CountryBO;
import com.lams.model.bo.master.MasterBaseBO;

public interface BankService {
	
	public List<MasterBaseBO> getBanksByMode(Integer mode);
}
