package com.lams.api.service.master;

import java.util.List;

import com.lams.model.bo.master.StateBO;

public interface StateService {

	/**
	 * Getting States Based on Country Id
	 * @param countryId from where States are mapped
	 * @return
	 */
	public List<StateBO> getStatesByCountryId(Long countryId);
}
