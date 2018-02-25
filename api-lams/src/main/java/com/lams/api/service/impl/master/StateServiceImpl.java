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

import com.lams.api.domain.master.StateMstr;
import com.lams.api.repository.master.StateMstrRepository;
import com.lams.api.service.master.StateService;
import com.lams.model.bo.master.StateBO;
import com.lams.model.utils.CommonUtils;

@Service
@Transactional
public class StateServiceImpl implements StateService {

	public static final Logger logger = Logger.getLogger(StateServiceImpl.class.getName());

	@Autowired
	private StateMstrRepository stateMstrRepository;

	@Override
	public List<StateBO> getStatesByCountryId(Long countryId) {
		logger.log(Level.INFO, "Entry in getStatesByCountryId");
		List<StateMstr> states = stateMstrRepository.findByCountryId(countryId);
		if (CommonUtils.isListNullOrEmpty(states)) {
			logger.log(Level.WARNING, "No States found for CountryId===>{}", countryId);
			return Collections.emptyList();
		}
		List<StateBO> response = new ArrayList<>(states.size());
		for (StateMstr state : states) {
			StateBO stateBO = new StateBO();
			BeanUtils.copyProperties(state, stateBO);
			response.add(stateBO);
		}
		logger.log(Level.INFO, "Exit in getStatesByCountryId");
		return response;
	}
}
