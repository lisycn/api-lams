package com.lams.api.service;

import java.util.List;

import com.lams.model.bo.ApplicationsBO;

public interface ApplicationsService {

	public List<ApplicationsBO> getAll(Long userId);
	
	public ApplicationsBO get(Long id);
	
	public Long save(ApplicationsBO applicationsBO);
}
