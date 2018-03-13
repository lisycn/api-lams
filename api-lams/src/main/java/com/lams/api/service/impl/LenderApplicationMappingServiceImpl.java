package com.lams.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lams.api.domain.LenderApplicationMapping;
import com.lams.api.domain.master.ApplicationTypeMstr;
import com.lams.api.repository.LenderApplicationMappingRepository;
import com.lams.api.service.LenderApplicationMappingService;
import com.lams.model.bo.master.ApplicationTypeMstrBO;

@Service
@Transactional
public class LenderApplicationMappingServiceImpl implements LenderApplicationMappingService {

	public static final Logger logger = Logger.getLogger(LenderApplicationMappingServiceImpl.class.getName());

	@Autowired
	private LenderApplicationMappingRepository applicationMappingRepository;

	@Override
	public List<ApplicationTypeMstrBO> getApplicationTypeByUserIdAndIsActive(Long userId, Boolean isActive) {
		logger.log(Level.INFO, "Entry in getApplicationTypeByUserIdAndIsActive() Method");
		List<ApplicationTypeMstr> list = applicationMappingRepository.getApplicationTypesByUserIdAndIsActive(userId,
				isActive);
		logger.log(Level.INFO, "Application Type List size==={0}===of UserId==={1}",
				new Object[] { list.size(), userId });
		List<ApplicationTypeMstrBO> response = new ArrayList<>(list.size());
		for (ApplicationTypeMstr app : list) {
			ApplicationTypeMstrBO baseBO = new ApplicationTypeMstrBO();
			BeanUtils.copyProperties(app, baseBO);
			response.add(baseBO);
		}
		logger.log(Level.INFO, "Exit From getApplicationTypeByUserIdAndIsActive() Method");
		return response;
	}

	@Override
	public void save(Long appTypeId, Long userId,Long actioner) {
		logger.log(Level.INFO, "Entry in save() Method");
		LenderApplicationMapping applicationMapping = new LenderApplicationMapping();
		applicationMapping.setApplicationTypeId(new ApplicationTypeMstr(appTypeId));
		applicationMapping.setUserId(userId);
		applicationMapping.setCreatedBy(actioner);
		applicationMapping.setCreatedDate(new Date());
		applicationMapping.setIsActive(true);
		applicationMappingRepository.save(applicationMapping);
		logger.log(Level.INFO, "Exit From save() Method");
	}

}
