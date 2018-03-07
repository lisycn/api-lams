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

import com.lams.api.domain.master.CountryMstr;
import com.lams.api.repository.master.CountryMstrRepository;
import com.lams.api.service.master.CountryService;
import com.lams.model.bo.master.CountryBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.Enums;
import com.lams.model.utils.Enums.Mode;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

	public static final Logger logger = Logger.getLogger(CountryServiceImpl.class.getName());

	@Autowired
	private CountryMstrRepository countryMstrRepository;

	@Override
	public List<CountryBO> getCountriesByMode(Integer mode) {
		logger.log(Level.INFO, "Entry in getCountriesByMode");
		List<CountryMstr> countries = null;
		Mode type = Enums.Mode.getType(mode);
		switch (type) {
		case ACTIVE:
			countries = countryMstrRepository.findByIsActive(true);
			break;
		case INACTIVE:
			countries = countryMstrRepository.findByIsActive(false);
			break;
		case BOTH:
			countries = countryMstrRepository.findAll();
			break;
		default:
			break;
		}
		if (CommonUtils.isListNullOrEmpty(countries)) {
			logger.log(Level.WARNING, "No Countries found for MODE===>{}", mode);
			return Collections.emptyList();
		}

		List<CountryBO> response = new ArrayList<>(countries.size());
		for (CountryMstr country : countries) {
			CountryBO countryBO = new CountryBO();
			BeanUtils.copyProperties(country, countryBO);
			response.add(countryBO);
		}
		logger.log(Level.INFO, "Exit in getCountriesByMode");
		return response;
	}
}
