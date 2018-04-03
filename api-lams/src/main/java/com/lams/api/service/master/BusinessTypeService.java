package com.lams.api.service.master;

import java.util.List;

import com.lams.model.bo.master.BusinessTypeBO;

public interface BusinessTypeService {

	/**
	 * Getting BusinessTypes by Mode as (Contains Active(0), Inactive(1), Both(2))
	 * @param mode as (Contains Active(0), Inactive(1), Both(2))
	 * @return
	 */
	public List<BusinessTypeBO> getBusinessTypesByMode(Integer mode);
}
