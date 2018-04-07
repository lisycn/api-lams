package com.lams.api.service;

import java.util.List;

import com.lams.model.bo.LenderApplicationMappingBO;

public interface LenderApplicationMappingService {

	/**
	 * Return List of Active Applications Mapped with Given UserId
	 * 
	 * @param userId
	 * @param isActive
	 * @return
	 */
	public List<LenderApplicationMappingBO> getApplicationTypeByUserIdAndIsActive(Long userId, Boolean isActive);
	
	/**
	 * Add new MMapping
	 * @param appTypeId
	 * @param userId
	 * @param actioner
	 */
	public void save(Long appTypeId,Long userId,Long actioner);

}
