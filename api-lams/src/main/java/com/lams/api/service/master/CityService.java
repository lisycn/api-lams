package com.lams.api.service.master;

import java.util.List;

import com.lams.model.bo.master.CityBO;

public interface CityService {

	/**
	 * Getting Cities Based on State Id
	 * 
	 * @param stateId
	 *            from where Cities are mapped
	 * @return
	 */
	public List<CityBO> getCitiesByStateId(Long stateId);
}
