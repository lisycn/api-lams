package com.lams.api.service.impl.loan;

import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lams.api.domain.loan.PersonalLoanDetails;
import com.lams.api.repository.ApplicationsRepository;
import com.lams.api.repository.loan.PersonalLoanDetailsRepository;
import com.lams.api.repository.master.ApplicationTypeMstrRepository;
import com.lams.api.repository.master.LoanTypeMstrRepository;
import com.lams.api.service.loan.PersonalLoanDetailsService;
import com.lams.model.loan.bo.PersonalLoanDetailsBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.CommonUtils.ApplicationType;
import com.lams.model.utils.CommonUtils.ApplicationTypeCode;

@Service
@Transactional
public class PersonalLoanDetailsServiceImpl implements PersonalLoanDetailsService {

	public static final Logger logger = Logger.getLogger(PersonalLoanDetailsServiceImpl.class);
	
	@Autowired
	private PersonalLoanDetailsRepository repository;
	
	@Autowired
	private LoanTypeMstrRepository loanTypeMstrRepository;
	
	@Autowired
	private ApplicationTypeMstrRepository applicationTypeMstrRepository;
	
	@Autowired 
	private ApplicationsRepository applicationsRepository;

	@Override
	public Long save(PersonalLoanDetailsBO requestLoanDetailsBO) {
		PersonalLoanDetails domainObj = null;
		if(!CommonUtils.isObjectNullOrEmpty(requestLoanDetailsBO.getId())) {
			domainObj = repository.findByIdAndIsActive(requestLoanDetailsBO.getId(), true);
		}
		if(CommonUtils.isObjectNullOrEmpty(domainObj)) {
			domainObj = new PersonalLoanDetails();
			domainObj.setCreatedBy(requestLoanDetailsBO.getUserId());
			domainObj.setCreatedDate(new Date());
			domainObj.setIsActive(true);
			String lastLeadReferenceNo = applicationsRepository.getLastLeadReferenceNo(Long.valueOf(ApplicationType.PERSONAL_LOAN));
			domainObj.setLeadReferenceNo(CommonUtils.generateRefNo(ApplicationTypeCode.PERSONAL_LOAN, lastLeadReferenceNo));
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
	public PersonalLoanDetailsBO get(Long id) {
		PersonalLoanDetails loanDetails = repository.findByIdAndIsActive(id, true);
		PersonalLoanDetailsBO response = new PersonalLoanDetailsBO();
		if(!CommonUtils.isObjectNullOrEmpty(loanDetails)) {
			BeanUtils.copyProperties(loanDetails, response);
		}
		return response;
	}
}

