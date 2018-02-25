package com.lams.api.service.master;

import java.util.List;

import com.lams.model.bo.master.CountryBO;

public interface CountryService {
	
	/**
	 * Getting Countries by Mode as (Contains Active(0), Inactive(1), Both(2))
	 * @param mode as (Contains Active(0), Inactive(1), Both(2))
	 * @return
	 */
	public List<CountryBO> getCountriesByMode(Integer mode);
}
