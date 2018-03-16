package com.lams.api.service.impl.loan;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lams.api.domain.loan.GoldLoanDetails;
import com.lams.api.repository.loan.GoldLoanDetailsRepository;
import com.lams.api.repository.master.ApplicationTypeMstrRepository;
import com.lams.api.repository.master.LoanTypeMstrRepository;
import com.lams.api.service.loan.GoldLoanDetailsService;
import com.lams.model.loan.bo.GoldLoanDetailsBO;
import com.lams.model.utils.CommonUtils;

@Service
@Transactional
public class GoldLoanDetailsServiceImpl implements GoldLoanDetailsService{

	public static final Logger logger = Logger.getLogger(GoldLoanDetailsServiceImpl.class);
	
	@Autowired
	private GoldLoanDetailsRepository repository;
	
	@Autowired
	private LoanTypeMstrRepository loanTypeMstrRepository;
	
	@Autowired
	private ApplicationTypeMstrRepository applicationTypeMstrRepository;

	@Override
	public Long save(GoldLoanDetailsBO requestLoanDetailsBO) {
		GoldLoanDetails domainObj = null;
		if(!CommonUtils.isObjectNullOrEmpty(requestLoanDetailsBO.getId())) {
			domainObj = repository.findByIdAndIsActive(requestLoanDetailsBO.getId(), true);
		}
		if(CommonUtils.isObjectNullOrEmpty(domainObj)) {
			domainObj = new GoldLoanDetails();
			domainObj.setCreatedBy(requestLoanDetailsBO.getUserId());
			domainObj.setCreatedDate(new Date());
			domainObj.setLeadReferenceNo("GL-001");
			domainObj.setIsActive(true);
			domainObj.setUserId(requestLoanDetailsBO.getUserId());
		} else {
			domainObj.setModifiedBy(requestLoanDetailsBO.getUserId());
			domainObj.setModifiedDate(new Date());
		}
		BeanUtils.copyProperties(requestLoanDetailsBO, domainObj,"id","createdBy","modifiedDate","createdDate","modifiedBy","userId","isActive");
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
	public GoldLoanDetailsBO get(Long id) {
		GoldLoanDetails loanDetails = repository.findByIdAndIsActive(id, true);
		GoldLoanDetailsBO response = new GoldLoanDetailsBO();
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

