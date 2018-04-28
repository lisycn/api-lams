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
	public List<LenderBorrowerConnectionBO> getConnections(Long applicationId,String status);
	
	public List<LenderBorrowerConnectionBO> getConnections(Long userId,Long applicationId, Boolean isActive);

	/**
	 * 
	 * @param lenderBorrowerConnectionBO
	 */
	public Long save(LenderBorrowerConnectionBO lenderBorrowerConnectionBO);
	
	public List<LenderBorrowerConnectionBO> getRespondedApplication(Long lrId, Long brId, Long applicationId);
	
	public List<LenderBorrowerConnectionBO> getConnectionByLenderId(Long lrId);
}
