package com.lams.api.service.impl.loan;

import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lams.api.domain.loan.OthersLoanDetails;
import com.lams.api.repository.loan.OthersLoanDetailsRepository;
import com.lams.api.repository.master.ApplicationTypeMstrRepository;
import com.lams.api.repository.master.LoanTypeMstrRepository;
import com.lams.api.service.loan.OthersLoanDetailsService;
import com.lams.model.loan.bo.OthersLoanDetailsBO;
import com.lams.model.utils.CommonUtils;

@Service
@Transactional
public class OthersLoanDetailsServiceImpl implements OthersLoanDetailsService {

	public static final Logger logger = Logger.getLogger(OthersLoanDetailsServiceImpl.class);
	
	@Autowired
	private OthersLoanDetailsRepository repository;
	
	@Autowired
	private LoanTypeMstrRepository loanTypeMstrRepository;
	
	@Autowired
	private ApplicationTypeMstrRepository applicationTypeMstrRepository;

	@Override
	public Long save(OthersLoanDetailsBO requestLoanDetailsBO) {
		OthersLoanDetails domainObj = null;
		if(!CommonUtils.isObjectNullOrEmpty(requestLoanDetailsBO.getId())) {
			domainObj = repository.findByIdAndIsActive(requestLoanDetailsBO.getId(), true);
		}
		if(CommonUtils.isObjectNullOrEmpty(domainObj)) {
			domainObj = new OthersLoanDetails();
			domainObj.setCreatedBy(requestLoanDetailsBO.getUserId());
			domainObj.setCreatedDate(new Date());
			domainObj.setIsActive(true);
			domainObj.setLeadReferenceNo("OL-001");
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
	public OthersLoanDetailsBO get(Long id) {
		OthersLoanDetails loanDetails = repository.findByIdAndIsActive(id, true);
		OthersLoanDetailsBO response = new OthersLoanDetailsBO();
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

