package com.lams.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lams.api.domain.Applications;
import com.lams.api.repository.ApplicationsRepository;
import com.lams.api.repository.master.ApplicationTypeMstrRepository;
import com.lams.api.repository.master.LoanTypeMstrRepository;
import com.lams.api.service.ApplicationsService;
import com.lams.model.bo.ApplicationsBO;
import com.lams.model.utils.CommonUtils;

@Service
@Transactional
public class ApplicationsServiceImpl implements ApplicationsService{

	public static final Logger logger = Logger.getLogger(ApplicationsServiceImpl.class);
	
	@Autowired
	private ApplicationsRepository applicationsRepository;
	
	@Autowired
	private ApplicationTypeMstrRepository applicationTypeMstrRepository;
	
	@Autowired
	private LoanTypeMstrRepository loanTypeMstrRepository;

	/*
	 *GET ALL APPLICATIONS BY USER ID AND IS ACTIVE TRUE 
	 */
	@Override
	public List<ApplicationsBO> getAll(Long userId){
		List<Applications> applicationsList = applicationsRepository.findByUserIdAndIsActive(userId, true);
		List<ApplicationsBO> applicationsBOList = new ArrayList<>(applicationsList.size());
		for(Applications applications : applicationsList) {
			applicationsBOList.add(convertDomainToBO(applications));
		}
		return applicationsBOList;
	}
	
	/**
	 * GET APPLICATION DETAILS BY APPLICATION ID
	 */
	@Override
	public ApplicationsBO get(Long id){
		Applications applications = applicationsRepository.findByIdAndIsActive(id, true);
		return convertDomainToBO(applications);
	}
	
	/**
	 * SAVE AND UPDATE APPLICATION DATA
	 */
	@Override
	public Long save(ApplicationsBO applicationsBO){
		Applications applications = null;
		if(!CommonUtils.isObjectNullOrEmpty(applicationsBO.getId())) {
			applications = applicationsRepository.findByIdAndIsActive(applicationsBO.getId(), true);
		}
		if(CommonUtils.isObjectNullOrEmpty(applications)) {
			applications = new Applications();
			applications.setCreatedBy(applicationsBO.getUserId());
			applications.setCreatedDate(new Date());
			applications.setIsActive(true);
			applications.setUserId(applicationsBO.getUserId());
		} else {
			applications.setModifiedBy(applicationsBO.getUserId());
			applications.setModifiedDate(new Date());
		}
		BeanUtils.copyProperties(applicationsBO, applications,"id","createdBy","modifiedDate","createdDate","modifiedBy","userId","isActive");
		if(!CommonUtils.isObjectNullOrEmpty(applicationsBO.getApplicationTypeId())) {
			applications.setApplicationTypeId(applicationTypeMstrRepository.findOne(applicationsBO.getApplicationTypeId()));
		}
		if(!CommonUtils.isObjectNullOrEmpty(applicationsBO.getLoanTypeId())) {
			applications.setLoanTypeId(loanTypeMstrRepository.findOne(applicationsBO.getLoanTypeId()));
		}
		applications = applicationsRepository.save(applications);
		return applications.getId();
	}
	
	
	/**
	 * CONVERT APPLICATIONS DOMAIN OBJ TO BO OBJ
	 * @param applications
	 * @return
	 */
	public ApplicationsBO convertDomainToBO(Applications applications) {
		ApplicationsBO applicationsBO = new ApplicationsBO();
		if(CommonUtils.isObjectNullOrEmpty(applications)) {
			return applicationsBO;
		}
		BeanUtils.copyProperties(applications, applicationsBO);
		if(!CommonUtils.isObjectNullOrEmpty(applications.getApplicationTypeId())) {
			applicationsBO.setApplicationTypeId(applications.getApplicationTypeId().getId());
			applicationsBO.setApplicationTypeName(applications.getApplicationTypeId().getName());	
		}
		if(!CommonUtils.isObjectNullOrEmpty(applications.getLoanTypeId())) {
			applicationsBO.setLoanTypeId(applications.getLoanTypeId().getId());
			applicationsBO.setLoanTypeName(applications.getLoanTypeId().getName());	
		}
		return applicationsBO;
	}
	
}
