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

import com.lams.api.domain.master.BusinessTypeMstr;
import com.lams.api.repository.master.BusinessTypeMstrRepository;
import com.lams.api.service.master.BusinessTypeService;
import com.lams.model.bo.master.BusinessTypeBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.Enums;
import com.lams.model.utils.Enums.Mode;

@Service
@Transactional
public class BusinessTypeServiceImpl implements BusinessTypeService {

	public static final Logger logger = Logger.getLogger(BusinessTypeServiceImpl.class.getName());

	@Autowired
	private BusinessTypeMstrRepository businessTypeMstrRepository; 

	@Override
	public List<BusinessTypeBO> getBusinessTypesByMode(Integer mode) {
		logger.log(Level.INFO, "Entry in getCountriesByMode");
		List<BusinessTypeMstr> businesTypes = null;
		Mode type = Enums.Mode.getType(mode);
		switch (type) {
		case ACTIVE:
			businesTypes = businessTypeMstrRepository.findByIsActive(true);
			break;
		case INACTIVE:
			businesTypes = businessTypeMstrRepository.findByIsActive(false);
			break;
		case BOTH:
			businesTypes = businessTypeMstrRepository.findAll();
			break;
		default:
			break;
		}
		if (CommonUtils.isListNullOrEmpty(businesTypes)) {
			logger.log(Level.WARNING, "No businesTypes found for MODE===>{}", mode);
			return Collections.emptyList();
		}

		List<BusinessTypeBO> response = new ArrayList<>(businesTypes.size());
		for (BusinessTypeMstr busiType : businesTypes) {
			BusinessTypeBO busiTypeBo = new BusinessTypeBO();
			BeanUtils.copyProperties(busiType, busiTypeBo);
			response.add(busiTypeBo);
		}
		logger.log(Level.INFO, "Exit in getBusinessTypesByMode");
		return response;
	}
}
