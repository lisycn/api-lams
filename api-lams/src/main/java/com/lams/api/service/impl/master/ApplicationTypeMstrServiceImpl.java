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

import com.lams.api.domain.master.ApplicationTypeMstr;
import com.lams.api.repository.master.ApplicationTypeMstrRepository;
import com.lams.api.service.master.ApplicationTypeMstrService;
import com.lams.model.bo.master.MasterBaseBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.Enums;
import com.lams.model.utils.Enums.Mode;

@Service
@Transactional
public class ApplicationTypeMstrServiceImpl implements ApplicationTypeMstrService{

	public static final Logger logger = Logger.getLogger(ApplicationTypeMstrServiceImpl.class.getName());
	
	@Autowired
	private ApplicationTypeMstrRepository appTypeRepository; 
	
	@Override
	public List<MasterBaseBO> getApplicationTypeByMode(Integer mode) {
		logger.log(Level.INFO, "Entry in getApplicationTypeByMode");
		List<ApplicationTypeMstr> applicationTypes = null;
		Mode type = Enums.Mode.getType(mode);
		switch (type) {
		case ACTIVE:
			applicationTypes = appTypeRepository.findByIsActive(true);
			break;
		case INACTIVE:
			applicationTypes = appTypeRepository.findByIsActive(false);
			break;
		case BOTH:
			applicationTypes = appTypeRepository.findAll();
			break;
		default:
			break;
		}
		if (CommonUtils.isListNullOrEmpty(applicationTypes)) {
			logger.log(Level.WARNING, "No ApplicationType found for MODE===>{0}", mode);
			return Collections.emptyList();
		}

		List<MasterBaseBO> response = new ArrayList<>(applicationTypes.size());
		for (ApplicationTypeMstr applicationType : applicationTypes) {
			MasterBaseBO baseBO = new MasterBaseBO();
			BeanUtils.copyProperties(applicationType, baseBO);
			response.add(baseBO);
		}
		logger.log(Level.INFO, "Exit in getApplicationTypeByMode");
		return response;
	}

}
