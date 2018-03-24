package com.lams.api.service;

import java.util.List;

import com.lams.model.bo.ApplicationRequestBO;
import com.lams.model.bo.ApplicationsBO;
import com.lams.model.bo.LamsResponse;

public interface ApplicationsService {

	public List<ApplicationsBO> getAll(Long userId);
	
	public ApplicationsBO get(Long id);
	
	public Long save(ApplicationRequestBO applicationsBO);
	
	public LamsResponse getLoanApplicationDetails(Long id,Long applicationTypeId,Long userId);
	
	public LamsResponse getApplicationsForLender(Long userId);
	
	public LamsResponse getApplicationsForLenderByApplicationId(Long appId);
	
//	public LamsResponse getApplicationDetailsByApplicationTypeIdAndUserId(Long appId, Long userId);
}
