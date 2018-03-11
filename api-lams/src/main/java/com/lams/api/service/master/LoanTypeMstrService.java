package com.lams.api.service.master;

import java.util.List;

import com.lams.model.bo.master.MasterBaseBO;

public interface LoanTypeMstrService {

	public List<MasterBaseBO> getLoanTypeByMode(Integer mode);
}
