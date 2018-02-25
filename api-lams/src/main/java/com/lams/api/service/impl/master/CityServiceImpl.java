package com.lams.api.service.impl.master;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lams.api.domain.master.CityMstr;
import com.lams.api.repository.master.CityMstrRepository;
import com.lams.api.service.master.CityService;
import com.lams.model.bo.master.CityBO;
import com.lams.model.utils.CommonUtils;

@Service
@Transactional
public class CityServiceImpl implements CityService {

	public static final Logger logger = Logger.getLogger(CityServiceImpl.class.getName());

	@Autowired
	private CityMstrRepository cityMstrRepository;

	@Override
	public List<CityBO> getCitiesByStateId(Long stateId) {
		logger.log(Level.INFO, "Entry in getCitiesByStateId");
		List<CityMstr> cities = cityMstrRepository.findByStateId(stateId);
		if (CommonUtils.isListNullOrEmpty(cities)) {
			logger.log(Level.WARNING, "No Cities found for stateId===>{}", stateId);
			return Collections.emptyList();
		}
		List<CityBO> response = new ArrayList<>(cities.size());
		for (CityMstr city : cities) {
			CityBO cityBO = new CityBO();
			BeanUtils.copyProperties(city, cityBO);
			response.add(cityBO);
		}
		logger.log(Level.INFO, "Exit in getCitiesByStateId");
		return response;
	}
}
