package com.lams.api.service.master;

import java.util.List;

import com.lams.model.bo.master.MasterBaseBO;

public interface ApplicationTypeMstrService {

	public List<MasterBaseBO> getApplicationTypeByMode(Integer mode);
}
