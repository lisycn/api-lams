package com.lams.api.service;

import java.util.List;

import com.lams.model.bo.LenderBorrowerConnectionBO;

public interface LenderBorrowerConnectionService {

	/**
	 * 
	 * @param userId
	 * @param isActive
	 * @return
	 */
	public List<LenderBorrowerConnectionBO> getConnections(Long userId, Boolean isActive);
	
	public List<LenderBorrowerConnectionBO> getConnections(Long userId,Long applicationId, Boolean isActive);

	/**
	 * 
	 * @param lenderBorrowerConnectionBO
	 */
	public void save(LenderBorrowerConnectionBO lenderBorrowerConnectionBO);
}
