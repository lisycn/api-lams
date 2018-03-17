package com.lams.api.service.impl.loan;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lams.api.domain.loan.WorkingCapitalLoanDetails;
import com.lams.api.repository.ApplicationsRepository;
import com.lams.api.repository.loan.WorkingCapitalLoanDetailsRepository;
import com.lams.api.repository.master.ApplicationTypeMstrRepository;
import com.lams.api.repository.master.LoanTypeMstrRepository;
import com.lams.api.service.loan.WorkingCapitalLoanDetailsService;
import com.lams.model.loan.bo.WorkingCapitalLoanDetailsBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.CommonUtils.ApplicationType;
import com.lams.model.utils.CommonUtils.ApplicationTypeCode;

@Service
@Transactional
public class WorkingCapitalLoanDetailsServiceImpl implements WorkingCapitalLoanDetailsService {

	public static final Logger logger = Logger.getLogger(WorkingCapitalLoanDetailsServiceImpl.class);
	
	@Autowired
	private WorkingCapitalLoanDetailsRepository repository;
	
	@Autowired
	private LoanTypeMstrRepository loanTypeMstrRepository;
	
	@Autowired
	private ApplicationTypeMstrRepository applicationTypeMstrRepository;
	
	@Autowired 
	private ApplicationsRepository applicationsRepository;

	@Override
	public Long save(WorkingCapitalLoanDetailsBO requestLoanDetailsBO) {
		WorkingCapitalLoanDetails domainObj = null;
		if(!CommonUtils.isObjectNullOrEmpty(requestLoanDetailsBO.getId())) {
			domainObj = repository.findByIdAndIsActive(requestLoanDetailsBO.getId(), true);
		}
		if(CommonUtils.isObjectNullOrEmpty(domainObj)) {
			domainObj = new WorkingCapitalLoanDetails();
			domainObj.setCreatedBy(requestLoanDetailsBO.getUserId());
			domainObj.setCreatedDate(new Date());
			domainObj.setIsActive(true);
			String lastLeadReferenceNo = applicationsRepository.getLastLeadReferenceNo(Long.valueOf(ApplicationType.WORKING_CAPITAL_LOAN));
			domainObj.setLeadReferenceNo(CommonUtils.generateRefNo(ApplicationTypeCode.WORKING_CAPITAL_LOAN, lastLeadReferenceNo));
			domainObj.setUserId(requestLoanDetailsBO.getUserId());
		} else {
			domainObj.setModifiedBy(requestLoanDetailsBO.getUserId());
			domainObj.setModifiedDate(new Date());
		}
		BeanUtils.copyProperties(requestLoanDetailsBO, domainObj,CommonUtils.skipFieldsForCreateApp);
		if(!CommonUtils.isObjectNullOrEmpty(requestLoanDetailsBO.getApplicationTypeId())) {
			domainObj.setApplicationTypeId(applicationTypeMstrRepository.findOne(requestLoanDetailsBO.getApplicationTypeId()));
		}
		if(!CommonUtils.isObjectNullOrEmpty(requestLoanDetailsBO.getLoanTypeId())) {
			domainObj.setLoanTypeId(loanTypeMstrRepository.findOne(requestLoanDetailsBO.getLoanTypeId()));
		}
		domainObj = repository.save(domainObj);
		return domainObj.getId();
	}
	
	@Override
	public WorkingCapitalLoanDetailsBO get(Long id) {
		WorkingCapitalLoanDetails loanDetails = repository.findByIdAndIsActive(id, true);
		WorkingCapitalLoanDetailsBO response = new WorkingCapitalLoanDetailsBO();
		if(!CommonUtils.isObjectNullOrEmpty(loanDetails)) {
			BeanUtils.copyProperties(loanDetails, response);
			if(!CommonUtils.isObjectNullOrEmpty(loanDetails.getApplicationTypeId())) {
				response.setApplicationTypeId(loanDetails.getApplicationTypeId().getId());
				response.setApplicationTypeName(loanDetails.getApplicationTypeId().getName());	
			}
			if(!CommonUtils.isObjectNullOrEmpty(loanDetails.getLoanTypeId())) {
				response.setLoanTypeId(loanDetails.getLoanTypeId().getId());
				response.setLoanTypeName(loanDetails.getLoanTypeId().getName());	
			}
		}
		return response;
	}
}

