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

import com.lams.api.domain.master.SalutaionMstr;
import com.lams.api.repository.master.SalutaionMstrRepository;
import com.lams.api.service.master.SalutationService;
import com.lams.model.bo.master.MasterBaseBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.CommonUtils.Mode;

@Service
@Transactional
public class SalutationServiceImpl implements SalutationService {

	public static final Logger logger = Logger.getLogger(SalutationServiceImpl.class.getName());

	@Autowired
	private SalutaionMstrRepository salutaionMstrRepository;

	@Override
	public List<MasterBaseBO> getSalutationByMode(Integer mode) {
		logger.log(Level.INFO, "Entry in getSalutationByMode");
		List<SalutaionMstr> salutaions = null;
		Mode type = CommonUtils.Mode.getType(mode);
		switch (type) {
		case ACTIVE:
			salutaions = salutaionMstrRepository.findByIsActive(true);
			break;
		case INACTIVE:
			salutaions = salutaionMstrRepository.findByIsActive(false);
			break;
		case BOTH:
			salutaions = salutaionMstrRepository.findAll();
			break;
		default:
			break;
		}
		if (CommonUtils.isListNullOrEmpty(salutaions)) {
			logger.log(Level.WARNING, "No salutaions found for MODE===>{0}", mode);
			return Collections.emptyList();
		}

		List<MasterBaseBO> response = new ArrayList<>(salutaions.size());
		for (SalutaionMstr salutaion : salutaions) {
			MasterBaseBO baseBO = new MasterBaseBO();
			BeanUtils.copyProperties(salutaion, baseBO);
			response.add(baseBO);
		}
		logger.log(Level.INFO, "Exit in getSalutationByMode");
		return response;
	}
}
