package com.lams.api.service.impl.loan;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lams.api.domain.loan.PrivateEquityFinanceLoanDetails;
import com.lams.api.repository.ApplicationsRepository;
import com.lams.api.repository.loan.PrivateEquityFinanceLoanDetailsRepository;
import com.lams.api.repository.master.ApplicationTypeMstrRepository;
import com.lams.api.repository.master.LoanTypeMstrRepository;
import com.lams.api.service.loan.PrivateEquityFinanceLoanDetailsService;
import com.lams.model.loan.bo.PrivateEquityFinanceLoanDetailsBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.CommonUtils.ApplicationType;
import com.lams.model.utils.CommonUtils.ApplicationTypeCode;

@Service
@Transactional
public class PrivateEquityFinanceLoanDetailsServiceImpl implements PrivateEquityFinanceLoanDetailsService {

	public static final Logger logger = Logger.getLogger(PrivateEquityFinanceLoanDetailsServiceImpl.class);
	
	@Autowired
	private PrivateEquityFinanceLoanDetailsRepository repository;
	
	@Autowired
	private LoanTypeMstrRepository loanTypeMstrRepository;
	
	@Autowired
	private ApplicationTypeMstrRepository applicationTypeMstrRepository;
	
	@Autowired 
	private ApplicationsRepository applicationsRepository;

	@Override
	public Long save(PrivateEquityFinanceLoanDetailsBO requestLoanDetailsBO) {
		PrivateEquityFinanceLoanDetails domainObj = null;
		if(!CommonUtils.isObjectNullOrEmpty(requestLoanDetailsBO.getId())) {
			domainObj = repository.findByIdAndIsActive(requestLoanDetailsBO.getId(), true);
		}
		if(CommonUtils.isObjectNullOrEmpty(domainObj)) {
			domainObj = new PrivateEquityFinanceLoanDetails();
			domainObj.setCreatedBy(requestLoanDetailsBO.getUserId());
			domainObj.setCreatedDate(new Date());
			domainObj.setIsActive(true);
			
			if(!CommonUtils.isObjectNullOrEmpty(requestLoanDetailsBO.getIsFromCP()) && requestLoanDetailsBO.getIsFromCP()) {
				//requestLoanDetailsBO.getLeadReferenceNo() Property Contains Code of Channel Partner
				String lastLeadReferenceNo = applicationsRepository.getLastLeadReferenceNoForCP(Long.valueOf(ApplicationType.PRIVATE_EQUITY_FINANCE_LOAN));
				domainObj.setLeadReferenceNo(CommonUtils.generateRefNoFromCP(ApplicationTypeCode.PRIVATE_EQUITY_FINANCE_LOAN, lastLeadReferenceNo,requestLoanDetailsBO.getLeadReferenceNo()));
			}else {
				String lastLeadReferenceNo = applicationsRepository.getLastLeadReferenceNo(Long.valueOf(ApplicationType.PRIVATE_EQUITY_FINANCE_LOAN));
				domainObj.setLeadReferenceNo(CommonUtils.generateRefNo(ApplicationTypeCode.PRIVATE_EQUITY_FINANCE_LOAN, lastLeadReferenceNo));				
			}
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
		domainObj.setIsFromCP(requestLoanDetailsBO.getIsFromCP());
		domainObj = repository.save(domainObj);
		return domainObj.getId();
	}
	
	@Override
	public PrivateEquityFinanceLoanDetailsBO get(Long id) {
		PrivateEquityFinanceLoanDetails loanDetails = repository.findByIdAndIsActive(id, true);
		PrivateEquityFinanceLoanDetailsBO response = new PrivateEquityFinanceLoanDetailsBO();
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

