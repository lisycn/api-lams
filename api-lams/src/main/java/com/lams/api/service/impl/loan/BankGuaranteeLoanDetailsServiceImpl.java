package com.lams.api.service.impl.loan;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lams.api.domain.loan.BankGuaranteeLoanDetails;
import com.lams.api.repository.loan.BankGuaranteeLoanDetailsRepository;
import com.lams.api.repository.master.ApplicationTypeMstrRepository;
import com.lams.api.repository.master.LoanTypeMstrRepository;
import com.lams.api.service.loan.BankGuaranteeLoanDetailsService;
import com.lams.model.loan.bo.BankGuaranteeLoanDetailsBO;
import com.lams.model.utils.CommonUtils;

@Service
@Transactional
public class BankGuaranteeLoanDetailsServiceImpl implements BankGuaranteeLoanDetailsService{

	public static final Logger logger = Logger.getLogger(BankGuaranteeLoanDetailsServiceImpl.class);
	
	@Autowired
	private BankGuaranteeLoanDetailsRepository repository;
	
	@Autowired
	private LoanTypeMstrRepository loanTypeMstrRepository;
	
	@Autowired
	private ApplicationTypeMstrRepository applicationTypeMstrRepository;

	@Override
	public Long save(BankGuaranteeLoanDetailsBO requestLoanDetailsBO) {
		BankGuaranteeLoanDetails domainObj = null;
		if(!CommonUtils.isObjectNullOrEmpty(requestLoanDetailsBO.getId())) {
			domainObj = repository.findByIdAndIsActive(requestLoanDetailsBO.getId(), true);
		}
		if(CommonUtils.isObjectNullOrEmpty(domainObj)) {
			domainObj = new BankGuaranteeLoanDetails();
			domainObj.setCreatedBy(requestLoanDetailsBO.getUserId());
			domainObj.setCreatedDate(new Date());
			domainObj.setIsActive(true);
			domainObj.setLeadReferenceNo("BGL-001");
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
	public BankGuaranteeLoanDetailsBO get(Long id) {
		BankGuaranteeLoanDetails bankGuaranteeLoanDetails = repository.findByIdAndIsActive(id, true);
		BankGuaranteeLoanDetailsBO response = new BankGuaranteeLoanDetailsBO();
		if(!CommonUtils.isObjectNullOrEmpty(bankGuaranteeLoanDetails)) {
			BeanUtils.copyProperties(bankGuaranteeLoanDetails, response);
			if(!CommonUtils.isObjectNullOrEmpty(bankGuaranteeLoanDetails.getApplicationTypeId())) {
				response.setApplicationTypeId(bankGuaranteeLoanDetails.getApplicationTypeId().getId());
				response.setApplicationTypeName(bankGuaranteeLoanDetails.getApplicationTypeId().getName());	
			}
			if(!CommonUtils.isObjectNullOrEmpty(bankGuaranteeLoanDetails.getLoanTypeId())) {
				response.setLoanTypeId(bankGuaranteeLoanDetails.getLoanTypeId().getId());
				response.setLoanTypeName(bankGuaranteeLoanDetails.getLoanTypeId().getName());	
			}
		}
		return response;
	}
	
	
	
}
