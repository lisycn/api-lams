package com.lams.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lams.api.domain.Applications;
import com.lams.api.domain.LenderBorrowerConnection;
import com.lams.api.domain.User;
import com.lams.api.repository.ApplicationsRepository;
import com.lams.api.repository.LenderApplicationMappingRepository;
import com.lams.api.repository.LenderBorrowerConnectionRepository;
import com.lams.api.repository.UserMstrRepository;
import com.lams.api.service.ApplicationsService;
import com.lams.api.service.loan.BankGuaranteeLoanDetailsService;
import com.lams.api.service.loan.CCFacilitiesLoanDetailsService;
import com.lams.api.service.loan.CarLoanDetailsService;
import com.lams.api.service.loan.DropLineOdFacilitiesLoanDetailsService;
import com.lams.api.service.loan.EducationLoanDetailsService;
import com.lams.api.service.loan.GoldLoanDetailsService;
import com.lams.api.service.loan.HomeLoanDetailsService;
import com.lams.api.service.loan.LoanAgainstFDsDetailsService;
import com.lams.api.service.loan.LoanAgainstPropertyDetailsService;
import com.lams.api.service.loan.LoanAgainstSecuritiesLoanDetailsService;
import com.lams.api.service.loan.OthersLoanDetailsService;
import com.lams.api.service.loan.OverDraftFacilitiesLoanDetailsService;
import com.lams.api.service.loan.PersonalLoanDetailsService;
import com.lams.api.service.loan.PrivateEquityFinanceLoanDetailsService;
import com.lams.api.service.loan.ProjectFinanceLoanDetailsService;
import com.lams.api.service.loan.SecuredBusinessLoanDetailsService;
import com.lams.api.service.loan.TermLoanDetailsService;
import com.lams.api.service.loan.WorkingCapitalLoanDetailsService;
import com.lams.api.utils.MailAsynComponent;
import com.lams.model.bo.ApplicationRequestBO;
import com.lams.model.bo.ApplicationsBO;
import com.lams.model.bo.LamsResponse;
import com.lams.model.bo.LenderBorrowerConnectionBO;
import com.lams.model.bo.UserBO;
import com.lams.model.loan.bo.BankGuaranteeLoanDetailsBO;
import com.lams.model.loan.bo.CCFacilitiesLoanDetailsBO;
import com.lams.model.loan.bo.CarLoanDetailsBO;
import com.lams.model.loan.bo.DropLineOdFacilitiesLoanDetailsBO;
import com.lams.model.loan.bo.EducationLoanDetailsBO;
import com.lams.model.loan.bo.GoldLoanDetailsBO;
import com.lams.model.loan.bo.HomeLoanDetailsBO;
import com.lams.model.loan.bo.LoanAgainstFDsDetailsBO;
import com.lams.model.loan.bo.LoanAgainstPropertyDetailsBO;
import com.lams.model.loan.bo.LoanAgainstSecuritiesLoanDetailsBO;
import com.lams.model.loan.bo.OthersLoanDetailsBO;
import com.lams.model.loan.bo.OverDraftFacilitiesLoanDetailsBO;
import com.lams.model.loan.bo.PersonalLoanDetailsBO;
import com.lams.model.loan.bo.PrivateEquityFinanceLoanDetailsBO;
import com.lams.model.loan.bo.ProjectFinanceLoanDetailsBO;
import com.lams.model.loan.bo.SecuredBusinessLoanDetailsBO;
import com.lams.model.loan.bo.TermLoanDetailsBO;
import com.lams.model.loan.bo.WorkingCapitalLoanDetailsBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.CommonUtils.ApplicationType;
import com.lams.model.utils.MultipleJSONObjectHelper;

@Service
@Transactional
public class ApplicationsServiceImpl implements ApplicationsService {

	public static final Logger logger = Logger.getLogger(ApplicationsServiceImpl.class);

	@Autowired
	private ApplicationsRepository applicationsRepository;

	@Autowired
	private UserMstrRepository userMstrRepository;

	@Autowired
	private BankGuaranteeLoanDetailsService bankGuaranteeLoanDetailsService;

	@Autowired
	private CarLoanDetailsService carLoanDetailsService;

	@Autowired
	private CCFacilitiesLoanDetailsService ccFacilitiesLoanDetailsService;

	@Autowired
	private DropLineOdFacilitiesLoanDetailsService dropLineOdFacilitiesLoanDetailsService;

	@Autowired
	private EducationLoanDetailsService educationLoanDetailsService;

	@Autowired
	private GoldLoanDetailsService goldLoanDetailsService;

	@Autowired
	private HomeLoanDetailsService homeLoanDetailsService;

	@Autowired
	private LoanAgainstFDsDetailsService loanAgainstFDsDetailsService;

	@Autowired
	private LoanAgainstPropertyDetailsService loanAgainstPropertyDetailsService;

	@Autowired
	private LoanAgainstSecuritiesLoanDetailsService loanAgainstSecuritiesLoanDetailsService;

	@Autowired
	private OthersLoanDetailsService othersLoanDetailsService;

	@Autowired
	private OverDraftFacilitiesLoanDetailsService overDraftFacilitiesLoanDetailsService;

	@Autowired
	private PersonalLoanDetailsService personalLoanDetailsService;

	@Autowired
	private PrivateEquityFinanceLoanDetailsService privateEquityFinanceLoanDetailsService;

	@Autowired
	private ProjectFinanceLoanDetailsService projectFinanceLoanDetailsService;

	@Autowired
	private SecuredBusinessLoanDetailsService securedBusinessLoanDetailsService;

	@Autowired
	private TermLoanDetailsService termLoanDetailsService;

	@Autowired
	private WorkingCapitalLoanDetailsService workingCapitalLoanDetailsService;

	@Autowired
	private LenderApplicationMappingRepository lenderApplicationMappingRepository;

	@Autowired
	private LenderBorrowerConnectionRepository lenderBorrowerConnectionRepository;
	
	@Autowired
	private MailAsynComponent mailAsynComponent; 

	/*
	 * GET ALL APPLICATIONS BY USER ID AND IS ACTIVE TRUE
	 */
	@Override
	public List<ApplicationsBO> getAll(Long userId) {
		List<Applications> applicationsList = applicationsRepository.findByUserIdAndIsActive(userId, true);
		List<ApplicationsBO> applicationsBOList = new ArrayList<>(applicationsList.size());
		for (Applications applications : applicationsList) {
			applicationsBOList.add(convertDomainToBO(applications));
		}
		return applicationsBOList;
	}
	
	/**
	 * Get All Application Of Borrower which is  Added by Channel Partner 
	 */

	@Override
	public List<ApplicationsBO> getAllByCP(Long userId, Long cpUserId) {
		List<Applications> applicationsList = applicationsRepository.getAllAppByUserIdAndCpId(userId, cpUserId);
		List<ApplicationsBO> applicationsBOList = new ArrayList<>(applicationsList.size());
		for (Applications applications : applicationsList) {
			applicationsBOList.add(convertDomainToBO(applications));
		}
		return applicationsBOList;
	}



	/**
	 * GET APPLICATION DETAILS BY APPLICATION ID
	 */
	@Override
	public ApplicationsBO get(Long id) {
		Applications applications = applicationsRepository.findByIdAndIsActive(id, true);
		return convertDomainToBO(applications);
	}

	/**
	 * SAVE AND UPDATE APPLICATION DATA
	 */
	@Override
	public Long save(ApplicationRequestBO applicationRequestBO) {
		logger.info("Enter in Save Application Sevice Impl--------------type----> "
				+ applicationRequestBO.getApplicationTypeId());
		
		Long userId = applicationRequestBO.getUserId();
		Long applicationId = null;
		try {
			switch (applicationRequestBO.getApplicationTypeId().intValue()) {

			case ApplicationType.HOME_LOAN:
				HomeLoanDetailsBO homeLoanDetailsBO = (HomeLoanDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(), HomeLoanDetailsBO.class);
				homeLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				homeLoanDetailsBO.setUserId(userId);
				homeLoanDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				applicationId = homeLoanDetailsService.save(homeLoanDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, homeLoanDetailsBO.getIsLoanDetailsLock());
				return applicationId;
			case ApplicationType.LOAN_AGAINST_PROPERTY:
				LoanAgainstPropertyDetailsBO loanAgainstPropertyDetailsBO = (LoanAgainstPropertyDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(),
								LoanAgainstPropertyDetailsBO.class);
				loanAgainstPropertyDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				loanAgainstPropertyDetailsBO.setUserId(userId);
				loanAgainstPropertyDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				applicationId = loanAgainstPropertyDetailsService.save(loanAgainstPropertyDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, loanAgainstPropertyDetailsBO.getIsLoanDetailsLock());
				return applicationId;

			case ApplicationType.SECURED_BUSINESS_LOAN:
				SecuredBusinessLoanDetailsBO securedBusinessLoanDetailsBO = (SecuredBusinessLoanDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(),
								SecuredBusinessLoanDetailsBO.class);
				securedBusinessLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				securedBusinessLoanDetailsBO.setUserId(userId);
				securedBusinessLoanDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				applicationId = securedBusinessLoanDetailsService.save(securedBusinessLoanDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, securedBusinessLoanDetailsBO.getIsLoanDetailsLock());
				return applicationId;

			case ApplicationType.WORKING_CAPITAL_LOAN:
				WorkingCapitalLoanDetailsBO workingCapitalLoanDetailsBO = (WorkingCapitalLoanDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(),
								WorkingCapitalLoanDetailsBO.class);
				workingCapitalLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				workingCapitalLoanDetailsBO.setUserId(userId);
				workingCapitalLoanDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				applicationId = workingCapitalLoanDetailsService.save(workingCapitalLoanDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, workingCapitalLoanDetailsBO.getIsLoanDetailsLock());
				return applicationId;
				
			case ApplicationType.EDUCATION_LOAN:
				EducationLoanDetailsBO educationLoanDetailsBO = (EducationLoanDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(), EducationLoanDetailsBO.class);
				educationLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				educationLoanDetailsBO.setUserId(userId);
				educationLoanDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				applicationId = educationLoanDetailsService.save(educationLoanDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, educationLoanDetailsBO.getIsLoanDetailsLock());
				return applicationId;

			case ApplicationType.CAR_LOAN:
				CarLoanDetailsBO carLoanDetailsBO = (CarLoanDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(), CarLoanDetailsBO.class);
				carLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				carLoanDetailsBO.setUserId(userId);
				carLoanDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				applicationId = carLoanDetailsService.save(carLoanDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, carLoanDetailsBO.getIsLoanDetailsLock());
				return applicationId;

			case ApplicationType.OVERDRAFT_FACILITIES_LOAN:
				OverDraftFacilitiesLoanDetailsBO overDraftFacilitiesLoanDetailsBO = (OverDraftFacilitiesLoanDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(),
								OverDraftFacilitiesLoanDetailsBO.class);
				overDraftFacilitiesLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				overDraftFacilitiesLoanDetailsBO.setUserId(userId);
				overDraftFacilitiesLoanDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				applicationId = overDraftFacilitiesLoanDetailsService.save(overDraftFacilitiesLoanDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, overDraftFacilitiesLoanDetailsBO.getIsLoanDetailsLock());
				return applicationId;

			case ApplicationType.DROPLINE_OVERDRAFT_FACILITIES_LOAN:
				DropLineOdFacilitiesLoanDetailsBO dropLineOdFacilitiesLoanDetailsBO = (DropLineOdFacilitiesLoanDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(),
								DropLineOdFacilitiesLoanDetailsBO.class);
				dropLineOdFacilitiesLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				dropLineOdFacilitiesLoanDetailsBO.setUserId(userId);
				dropLineOdFacilitiesLoanDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				applicationId = dropLineOdFacilitiesLoanDetailsService.save(dropLineOdFacilitiesLoanDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, dropLineOdFacilitiesLoanDetailsBO.getIsLoanDetailsLock());
				return applicationId;
				
			case ApplicationType.BANK_GUARANTEE_LOAN:
				BankGuaranteeLoanDetailsBO bankGuaranteeLoanDetailsBO = (BankGuaranteeLoanDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(),
								BankGuaranteeLoanDetailsBO.class);
				bankGuaranteeLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				bankGuaranteeLoanDetailsBO.setUserId(userId);
				bankGuaranteeLoanDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				applicationId = bankGuaranteeLoanDetailsService.save(bankGuaranteeLoanDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, bankGuaranteeLoanDetailsBO.getIsLoanDetailsLock());
				return applicationId;
				
			case ApplicationType.CC_FACILITIES_LOAN:
				CCFacilitiesLoanDetailsBO cCFacilitiesLoanDetailsBO = (CCFacilitiesLoanDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(),
								CCFacilitiesLoanDetailsBO.class);
				cCFacilitiesLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				cCFacilitiesLoanDetailsBO.setUserId(userId);
				cCFacilitiesLoanDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				applicationId = ccFacilitiesLoanDetailsService.save(cCFacilitiesLoanDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, cCFacilitiesLoanDetailsBO.getIsLoanDetailsLock());
				return applicationId;
				
			case ApplicationType.TERM_LOAN:
				TermLoanDetailsBO termLoanDetailsBO = (TermLoanDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(), TermLoanDetailsBO.class);
				termLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				termLoanDetailsBO.setUserId(userId);
				termLoanDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				applicationId = termLoanDetailsService.save(termLoanDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, termLoanDetailsBO.getIsLoanDetailsLock());
				return applicationId;
				
			case ApplicationType.LOAN_AGAINST_FDS:
				LoanAgainstFDsDetailsBO loanAgainstFDsDetailsBO = (LoanAgainstFDsDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(), LoanAgainstFDsDetailsBO.class);
				loanAgainstFDsDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				loanAgainstFDsDetailsBO.setUserId(userId);
				loanAgainstFDsDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				applicationId = loanAgainstFDsDetailsService.save(loanAgainstFDsDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, loanAgainstFDsDetailsBO.getIsLoanDetailsLock());
				return applicationId;
				
			case ApplicationType.LOAN_AGAINST_SECURITIS:
				LoanAgainstSecuritiesLoanDetailsBO loanAgainstSecuritiesLoanDetailsBO = (LoanAgainstSecuritiesLoanDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(),
								LoanAgainstSecuritiesLoanDetailsBO.class);
				loanAgainstSecuritiesLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				loanAgainstSecuritiesLoanDetailsBO.setUserId(userId);
				loanAgainstSecuritiesLoanDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				applicationId = loanAgainstSecuritiesLoanDetailsService.save(loanAgainstSecuritiesLoanDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, loanAgainstSecuritiesLoanDetailsBO.getIsLoanDetailsLock());
				return applicationId;
				
			case ApplicationType.PROJECT_FINANCE_LOAN:
				ProjectFinanceLoanDetailsBO projectFinanceLoanDetailsBO = (ProjectFinanceLoanDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(),
								ProjectFinanceLoanDetailsBO.class);
				projectFinanceLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				projectFinanceLoanDetailsBO.setUserId(userId);
				projectFinanceLoanDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				applicationId = projectFinanceLoanDetailsService.save(projectFinanceLoanDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, projectFinanceLoanDetailsBO.getIsLoanDetailsLock());
				return applicationId;
				
			case ApplicationType.PRIVATE_EQUITY_FINANCE_LOAN:
				PrivateEquityFinanceLoanDetailsBO privateEquityFinanceLoanDetailsBO = (PrivateEquityFinanceLoanDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(),
								PrivateEquityFinanceLoanDetailsBO.class);
				privateEquityFinanceLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				privateEquityFinanceLoanDetailsBO.setUserId(userId);
				privateEquityFinanceLoanDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				applicationId = privateEquityFinanceLoanDetailsService.save(privateEquityFinanceLoanDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, privateEquityFinanceLoanDetailsBO.getIsLoanDetailsLock());
				return applicationId;
				
			case ApplicationType.GOLD_LOAN:
				GoldLoanDetailsBO goldLoanDetailsBO = (GoldLoanDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(), GoldLoanDetailsBO.class);
				goldLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				goldLoanDetailsBO.setUserId(userId);
				goldLoanDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				
				applicationId = goldLoanDetailsService.save(goldLoanDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, goldLoanDetailsBO.getIsLoanDetailsLock());
				return applicationId;
				
			case ApplicationType.OTHER_LOAN:
				OthersLoanDetailsBO othersLoanDetailsBO = (OthersLoanDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(), OthersLoanDetailsBO.class);
				othersLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				othersLoanDetailsBO.setUserId(userId);
				othersLoanDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				
				applicationId = othersLoanDetailsService.save(othersLoanDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, othersLoanDetailsBO.getIsLoanDetailsLock());
				return applicationId;
				
			case ApplicationType.PERSONAL_LOAN:
				PersonalLoanDetailsBO personalLoanDetailsBO = (PersonalLoanDetailsBO) MultipleJSONObjectHelper
						.getObjectFromString(applicationRequestBO.getData().toString(), PersonalLoanDetailsBO.class);
				personalLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				personalLoanDetailsBO.setUserId(userId);
				personalLoanDetailsBO.setStatus(CommonUtils.Status.OPEN);
				
				applicationId = personalLoanDetailsService.save(personalLoanDetailsBO);
				sentMailWhenBRLockDetails(applicationId, userId, personalLoanDetailsBO.getIsLoanDetailsLock());
				return applicationId;
				
			default:
				return null;
			}
		} catch (Exception e) {
			logger.info("Error while save application form data");
			e.printStackTrace();
		}
		return null;
	}

	
	private void sentMailWhenBRLockDetails(Long applicationId, Long userId, Boolean isLockDetails) {
		try {
			if(!CommonUtils.isObjectNullOrEmpty(isLockDetails) && isLockDetails) {
				mailAsynComponent.sendMailToBorrowerWhenBRSubmitForm(applicationId, userId);
				mailAsynComponent.sendMailToLenderWhenBRSubmitForm(applicationId, userId);	
			}
		} catch (Exception e) {
			logger.info("THROW EXCEPTION WHILE SENDING MAIL");
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * CONVERT APPLICATIONS DOMAIN OBJ TO BO OBJ
	 * 
	 * @param applications
	 * @return
	 */
	public ApplicationsBO convertDomainToBO(Applications applications) {
		ApplicationsBO applicationsBO = new ApplicationsBO();
		if (CommonUtils.isObjectNullOrEmpty(applications)) {
			return applicationsBO;
		}
		BeanUtils.copyProperties(applications, applicationsBO);
		if (!CommonUtils.isObjectNullOrEmpty(applications.getApplicationTypeId())) {
			applicationsBO.setApplicationTypeId(applications.getApplicationTypeId().getId());
			applicationsBO.setApplicationTypeName(applications.getApplicationTypeId().getName());
			applicationsBO.setApplicationTypeCode(applications.getApplicationTypeId().getCode());
		}
		if (!CommonUtils.isObjectNullOrEmpty(applications.getLoanTypeId())) {
			applicationsBO.setLoanTypeId(applications.getLoanTypeId().getId());
			applicationsBO.setLoanTypeName(applications.getLoanTypeId().getName());
		}
		return applicationsBO;
	}

	public LamsResponse getLoanApplicationDetails(Long id, Long applicationTypeId, Long userId) {

		LamsResponse lamsResponse = new LamsResponse();
		Long employmentType = userMstrRepository.getEmpTypeById(userId);
		switch (applicationTypeId.intValue()) {

		case ApplicationType.HOME_LOAN:
			HomeLoanDetailsBO homeLoanDetailsBO = homeLoanDetailsService.get(id);
			homeLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(homeLoanDetailsBO);
			break;

		case ApplicationType.LOAN_AGAINST_PROPERTY:
			LoanAgainstPropertyDetailsBO loanAgainstPropertyDetailsBO = loanAgainstPropertyDetailsService.get(id);
			loanAgainstPropertyDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(loanAgainstPropertyDetailsBO);
			break;

		case ApplicationType.SECURED_BUSINESS_LOAN:
			SecuredBusinessLoanDetailsBO securedBusinessLoanDetailsBO = securedBusinessLoanDetailsService.get(id);
			securedBusinessLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(securedBusinessLoanDetailsBO);
			break;

		case ApplicationType.WORKING_CAPITAL_LOAN:
			WorkingCapitalLoanDetailsBO workingCapitalLoanDetailsBO = workingCapitalLoanDetailsService.get(id);
			workingCapitalLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(workingCapitalLoanDetailsBO);
			break;

		case ApplicationType.EDUCATION_LOAN:
			EducationLoanDetailsBO educationLoanDetailsBO = educationLoanDetailsService.get(id);
			educationLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(educationLoanDetailsBO);
			break;

		case ApplicationType.CAR_LOAN:
			CarLoanDetailsBO carLoanDetailsBO = carLoanDetailsService.get(id);
			carLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(carLoanDetailsBO);
			break;

		case ApplicationType.OVERDRAFT_FACILITIES_LOAN:
			OverDraftFacilitiesLoanDetailsBO overDraftFacilitiesLoanDetailsBO = overDraftFacilitiesLoanDetailsService
					.get(id);
			overDraftFacilitiesLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(overDraftFacilitiesLoanDetailsBO);
			break;

		case ApplicationType.DROPLINE_OVERDRAFT_FACILITIES_LOAN:
			DropLineOdFacilitiesLoanDetailsBO dropLineOdFacilitiesLoanDetailsBO = dropLineOdFacilitiesLoanDetailsService
					.get(id);
			dropLineOdFacilitiesLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(dropLineOdFacilitiesLoanDetailsBO);
			break;

		case ApplicationType.BANK_GUARANTEE_LOAN:
			BankGuaranteeLoanDetailsBO bankGuaranteeLoanDetailsBO = bankGuaranteeLoanDetailsService.get(id);
			bankGuaranteeLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(bankGuaranteeLoanDetailsBO);
			break;

		case ApplicationType.CC_FACILITIES_LOAN:
			CCFacilitiesLoanDetailsBO ccFacilitiesLoanDetailsBO = ccFacilitiesLoanDetailsService.get(id);
			ccFacilitiesLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(ccFacilitiesLoanDetailsBO);
			break;

		case ApplicationType.TERM_LOAN:
			TermLoanDetailsBO termLoanDetailsBO = termLoanDetailsService.get(id);
			termLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(termLoanDetailsBO);
			break;

		case ApplicationType.LOAN_AGAINST_FDS:
			LoanAgainstFDsDetailsBO loanAgainstFDsDetailsBO = loanAgainstFDsDetailsService.get(id);
			loanAgainstFDsDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(loanAgainstFDsDetailsBO);
			break;

		case ApplicationType.LOAN_AGAINST_SECURITIS:
			LoanAgainstSecuritiesLoanDetailsBO loanAgainstSecuritiesLoanDetailsBO = loanAgainstSecuritiesLoanDetailsService
					.get(id);
			loanAgainstSecuritiesLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(loanAgainstSecuritiesLoanDetailsBO);
			break;

		case ApplicationType.PROJECT_FINANCE_LOAN:
			ProjectFinanceLoanDetailsBO projectFinanceLoanDetailsBO = projectFinanceLoanDetailsService.get(id);
			projectFinanceLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(projectFinanceLoanDetailsBO);
			break;

		case ApplicationType.PRIVATE_EQUITY_FINANCE_LOAN:
			PrivateEquityFinanceLoanDetailsBO privateEquityFinanceLoanDetailsBO = privateEquityFinanceLoanDetailsService
					.get(id);
			privateEquityFinanceLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(privateEquityFinanceLoanDetailsBO);
			break;

		case ApplicationType.GOLD_LOAN:
			GoldLoanDetailsBO goldLoanDetailsBO = goldLoanDetailsService.get(id);
			goldLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(goldLoanDetailsBO);
			break;

		case ApplicationType.OTHER_LOAN:
			OthersLoanDetailsBO othersLoanDetailsBO = othersLoanDetailsService.get(id);
			othersLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(othersLoanDetailsBO);
			break;
		case ApplicationType.PERSONAL_LOAN:
			PersonalLoanDetailsBO personalLoanDetailsBO = personalLoanDetailsService.get(id);
			personalLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(personalLoanDetailsBO);
			break;
		default:
			lamsResponse.setMessage("Invalid Application Type Id");
			return lamsResponse;
		}
		lamsResponse.setMessage("Successfully get data");
		return lamsResponse;
	}

	@Override
	public LamsResponse getApplicationsForLender(Long userId) {
		List<Long> applicationTypesByUserId = lenderApplicationMappingRepository.getApplicationTypesByUserId(userId,
				true);
		logger.info("applicationTypesByUserId====" + applicationTypesByUserId + " usrId====>." + userId);
		if (CommonUtils.isListNullOrEmpty(applicationTypesByUserId)) {
			return new LamsResponse(HttpStatus.OK.value(), "No ApplicationType found for User");
		}
		List<Long> borrwersIds = applicationsRepository.getUserIdByApplicationTypeId(applicationTypesByUserId);
		if (CommonUtils.isListNullOrEmpty(borrwersIds)) {
			return new LamsResponse(HttpStatus.OK.value(), "No Borrower found for ApplicationType");
		}

		List<User> list = userMstrRepository.findByIdInAndIsActive(borrwersIds, true);
		List<UserBO> response = new ArrayList<>(list.size());
		for (User user : list) {
			UserBO userBo = new UserBO();
			BeanUtils.copyProperties(user, userBo);
			List<Applications> apps = applicationsRepository
					.findByUserIdAndIsActiveAndApplicationTypeIdIdIn(user.getId(), true, applicationTypesByUserId);
			List<ApplicationsBO> appResponse = new ArrayList<>(apps.size());
			Long employmentType = userMstrRepository.getEmpTypeById(user.getId());
			for (Applications app : apps) {
				switch (app.getApplicationTypeId().getId().intValue()) {

				case ApplicationType.HOME_LOAN:
					HomeLoanDetailsBO homeLoanDetailsBO = homeLoanDetailsService.get(app.getId());
					BeanUtils.copyProperties(app, homeLoanDetailsBO);
					homeLoanDetailsBO.setEmploymentType(employmentType);
					appResponse.add(homeLoanDetailsBO);
					break;

				case ApplicationType.LOAN_AGAINST_PROPERTY:
					LoanAgainstPropertyDetailsBO loanAgainstPropertyDetailsBO = loanAgainstPropertyDetailsService
							.get(app.getId());
					BeanUtils.copyProperties(app, loanAgainstPropertyDetailsBO);
					loanAgainstPropertyDetailsBO.setEmploymentType(employmentType);
					appResponse.add(loanAgainstPropertyDetailsBO);
					break;

				case ApplicationType.SECURED_BUSINESS_LOAN:
					SecuredBusinessLoanDetailsBO securedBusinessLoanDetailsBO = securedBusinessLoanDetailsService
							.get(app.getId());
					BeanUtils.copyProperties(app, securedBusinessLoanDetailsBO);
					securedBusinessLoanDetailsBO.setEmploymentType(employmentType);
					appResponse.add(securedBusinessLoanDetailsBO);
					break;

				case ApplicationType.WORKING_CAPITAL_LOAN:
					WorkingCapitalLoanDetailsBO workingCapitalLoanDetailsBO = workingCapitalLoanDetailsService
							.get(app.getId());
					BeanUtils.copyProperties(app, workingCapitalLoanDetailsBO);
					workingCapitalLoanDetailsBO.setEmploymentType(employmentType);
					appResponse.add(workingCapitalLoanDetailsBO);
					break;

				case ApplicationType.EDUCATION_LOAN:
					EducationLoanDetailsBO educationLoanDetailsBO = educationLoanDetailsService.get(app.getId());
					BeanUtils.copyProperties(app, educationLoanDetailsBO);
					educationLoanDetailsBO.setEmploymentType(employmentType);
					appResponse.add(educationLoanDetailsBO);
					break;

				case ApplicationType.CAR_LOAN:
					CarLoanDetailsBO carLoanDetailsBO = carLoanDetailsService.get(app.getId());
					BeanUtils.copyProperties(app, carLoanDetailsBO);
					carLoanDetailsBO.setEmploymentType(employmentType);
					appResponse.add(carLoanDetailsBO);
					break;

				case ApplicationType.OVERDRAFT_FACILITIES_LOAN:
					OverDraftFacilitiesLoanDetailsBO overDraftFacilitiesLoanDetailsBO = overDraftFacilitiesLoanDetailsService
							.get(app.getId());
					BeanUtils.copyProperties(app, overDraftFacilitiesLoanDetailsBO);
					overDraftFacilitiesLoanDetailsBO.setEmploymentType(employmentType);
					appResponse.add(overDraftFacilitiesLoanDetailsBO);
					break;

				case ApplicationType.DROPLINE_OVERDRAFT_FACILITIES_LOAN:
					DropLineOdFacilitiesLoanDetailsBO dropLineOdFacilitiesLoanDetailsBO = dropLineOdFacilitiesLoanDetailsService
							.get(app.getId());
					BeanUtils.copyProperties(app, dropLineOdFacilitiesLoanDetailsBO);
					dropLineOdFacilitiesLoanDetailsBO.setEmploymentType(employmentType);
					appResponse.add(dropLineOdFacilitiesLoanDetailsBO);
					break;

				case ApplicationType.BANK_GUARANTEE_LOAN:
					BankGuaranteeLoanDetailsBO bankGuaranteeLoanDetailsBO = bankGuaranteeLoanDetailsService
							.get(app.getId());
					BeanUtils.copyProperties(app, bankGuaranteeLoanDetailsBO);
					bankGuaranteeLoanDetailsBO.setEmploymentType(employmentType);
					appResponse.add(bankGuaranteeLoanDetailsBO);
					break;

				case ApplicationType.CC_FACILITIES_LOAN:
					CCFacilitiesLoanDetailsBO ccFacilitiesLoanDetailsBO = ccFacilitiesLoanDetailsService
							.get(app.getId());
					BeanUtils.copyProperties(app, ccFacilitiesLoanDetailsBO);
					ccFacilitiesLoanDetailsBO.setEmploymentType(employmentType);
					appResponse.add(ccFacilitiesLoanDetailsBO);
					break;

				case ApplicationType.TERM_LOAN:
					TermLoanDetailsBO termLoanDetailsBO = termLoanDetailsService.get(app.getId());
					BeanUtils.copyProperties(app, termLoanDetailsBO);
					termLoanDetailsBO.setEmploymentType(employmentType);
					appResponse.add(termLoanDetailsBO);
					break;

				case ApplicationType.LOAN_AGAINST_FDS:
					LoanAgainstFDsDetailsBO loanAgainstFDsDetailsBO = loanAgainstFDsDetailsService.get(app.getId());
					BeanUtils.copyProperties(app, loanAgainstFDsDetailsBO);
					loanAgainstFDsDetailsBO.setEmploymentType(employmentType);
					appResponse.add(loanAgainstFDsDetailsBO);
					break;

				case ApplicationType.LOAN_AGAINST_SECURITIS:
					LoanAgainstSecuritiesLoanDetailsBO loanAgainstSecuritiesLoanDetailsBO = loanAgainstSecuritiesLoanDetailsService
							.get(app.getId());
					BeanUtils.copyProperties(app, loanAgainstSecuritiesLoanDetailsBO);
					loanAgainstSecuritiesLoanDetailsBO.setEmploymentType(employmentType);
					appResponse.add(loanAgainstSecuritiesLoanDetailsBO);
					break;

				case ApplicationType.PROJECT_FINANCE_LOAN:
					ProjectFinanceLoanDetailsBO projectFinanceLoanDetailsBO = projectFinanceLoanDetailsService
							.get(app.getId());
					BeanUtils.copyProperties(app, projectFinanceLoanDetailsBO);
					projectFinanceLoanDetailsBO.setEmploymentType(employmentType);
					appResponse.add(projectFinanceLoanDetailsBO);
					break;

				case ApplicationType.PRIVATE_EQUITY_FINANCE_LOAN:
					PrivateEquityFinanceLoanDetailsBO privateEquityFinanceLoanDetailsBO = privateEquityFinanceLoanDetailsService
							.get(app.getId());
					BeanUtils.copyProperties(app, privateEquityFinanceLoanDetailsBO);
					privateEquityFinanceLoanDetailsBO.setEmploymentType(employmentType);
					appResponse.add(privateEquityFinanceLoanDetailsBO);
					break;

				case ApplicationType.GOLD_LOAN:
					GoldLoanDetailsBO goldLoanDetailsBO = goldLoanDetailsService.get(app.getId());
					BeanUtils.copyProperties(app, goldLoanDetailsBO);
					goldLoanDetailsBO.setEmploymentType(employmentType);
					appResponse.add(goldLoanDetailsBO);
					break;

				case ApplicationType.OTHER_LOAN:
					OthersLoanDetailsBO othersLoanDetailsBO = othersLoanDetailsService.get(app.getId());
					BeanUtils.copyProperties(app, othersLoanDetailsBO);
					othersLoanDetailsBO.setEmploymentType(employmentType);
					appResponse.add(othersLoanDetailsBO);
					break;
				case ApplicationType.PERSONAL_LOAN:
					PersonalLoanDetailsBO personalLoanDetailsBO = personalLoanDetailsService.get(app.getId());
					BeanUtils.copyProperties(app, personalLoanDetailsBO);
					personalLoanDetailsBO.setEmploymentType(employmentType);
					appResponse.add(personalLoanDetailsBO);
					break;
				}
				userBo.setApplications(appResponse);
			}
			response.add(userBo);
		}
		return new LamsResponse(HttpStatus.OK.value(), "Success", response);
	}

	@Override
	public LamsResponse getApplicationsForLenderByApplicationId(Long appId, String status, Long lenderId) {
		List<Applications> applications = null;

		if (CommonUtils.Status.OPEN.equalsIgnoreCase(status)) {
			applications = applicationsRepository.findByApplicationTypeIdIdAndIsActiveAndStatus(appId, true, status);
			List<ApplicationsBO> applicationsBOs = new ArrayList<>(applications.size());
			for (Applications application : applications) {
				
				// Check lender has taken any action on this application, if no action taken then it is open for Lender.
				if(lenderBorrowerConnectionRepository.isActionTakenOnApplicationByLender(application.getId(),lenderId) == 0) {
				ApplicationsBO applicationsBO = new ApplicationsBO();
				BeanUtils.copyProperties(application, applicationsBO);
				if (!CommonUtils.isObjectNullOrEmpty(application.getUserId())) {
					User user = userMstrRepository.findOne(application.getUserId());
					applicationsBO.setFirstName(user.getFirstName());
					applicationsBO.setLastName(user.getLastName());
					applicationsBO.setName(user.getName());
					applicationsBO.setEmail(user.getEmail());
					applicationsBO.setMobile(user.getMobile());
					applicationsBO.setEmploymentType(user.getEmploymentType());
					applicationsBOs.add(applicationsBO);
				}
				}
			}
				
			return new LamsResponse(HttpStatus.OK.value(), "Success", applicationsBOs);
		} else {
			List<LenderBorrowerConnection> listData = lenderBorrowerConnectionRepository.findApplicationsByAppTypeIdAndStatusAndByCreatedId(appId, status, lenderId);
			List<LenderBorrowerConnectionBO> applicationsBOs = new ArrayList<>(listData.size());
			for (LenderBorrowerConnection borrowerConnection : listData) {
				LenderBorrowerConnectionBO  applicationsBO = new LenderBorrowerConnectionBO();
				BeanUtils.copyProperties(borrowerConnection, applicationsBO);
				if(!CommonUtils.isObjectNullOrEmpty(borrowerConnection.getApplication())) {
					Applications application = borrowerConnection.getApplication();
					ApplicationsBO applicationsbo = new ApplicationsBO();
					BeanUtils.copyProperties(application, applicationsbo);
					if (!CommonUtils.isObjectNullOrEmpty(application.getUserId())) {
						User user = userMstrRepository.findOne(application.getUserId());
						applicationsbo.setFirstName(user.getFirstName());
						applicationsbo.setLastName(user.getLastName());
						applicationsbo.setName(user.getName());
						applicationsbo.setEmail(user.getEmail());
						applicationsbo.setMobile(user.getMobile());
						applicationsbo.setEmploymentType(user.getEmploymentType());
					}
					applicationsBO.setApplication(applicationsbo);
				}
				applicationsBOs.add(applicationsBO);
			}
			return new LamsResponse(HttpStatus.OK.value(), "Success", applicationsBOs);
		}
	}

	@Override
	public Boolean updateStatus(Long applicationId, String status, Long userId) {
		Applications app = applicationsRepository.findOne(applicationId);
		app.setStatus(status);
		applicationsRepository.save(app);
		return Boolean.TRUE;
	}


	@Override
	public Long saveFromCP(ApplicationsBO applicationRequestBO,Long brUserId,String cpUserCode) {
		logger.info("Enter in Save Application Sevice Impl--------------type----> "
				+ applicationRequestBO.getApplicationTypeId());
		try {
			switch (applicationRequestBO.getApplicationTypeId().intValue()) {

			case ApplicationType.HOME_LOAN:
				HomeLoanDetailsBO homeLoanDetailsBO = new HomeLoanDetailsBO();
				setCommonProperty(applicationRequestBO, homeLoanDetailsBO, cpUserCode, brUserId);
				return homeLoanDetailsService.save(homeLoanDetailsBO);

			case ApplicationType.LOAN_AGAINST_PROPERTY:
				LoanAgainstPropertyDetailsBO loanAgainstPropertyDetailsBO = new LoanAgainstPropertyDetailsBO();
				setCommonProperty(applicationRequestBO, loanAgainstPropertyDetailsBO, cpUserCode, brUserId);
				return loanAgainstPropertyDetailsService.save(loanAgainstPropertyDetailsBO);

			case ApplicationType.SECURED_BUSINESS_LOAN:
				SecuredBusinessLoanDetailsBO securedBusinessLoanDetailsBO = new SecuredBusinessLoanDetailsBO();
				setCommonProperty(applicationRequestBO, securedBusinessLoanDetailsBO, cpUserCode, brUserId);
				return securedBusinessLoanDetailsService.save(securedBusinessLoanDetailsBO);

			case ApplicationType.WORKING_CAPITAL_LOAN:
				WorkingCapitalLoanDetailsBO workingCapitalLoanDetailsBO = new WorkingCapitalLoanDetailsBO();
				setCommonProperty(applicationRequestBO, workingCapitalLoanDetailsBO, cpUserCode, brUserId);
				return workingCapitalLoanDetailsService.save(workingCapitalLoanDetailsBO);

			case ApplicationType.EDUCATION_LOAN:
				EducationLoanDetailsBO educationLoanDetailsBO = new EducationLoanDetailsBO();
				setCommonProperty(applicationRequestBO, educationLoanDetailsBO, cpUserCode, brUserId);
				return educationLoanDetailsService.save(educationLoanDetailsBO);

			case ApplicationType.CAR_LOAN:
				CarLoanDetailsBO carLoanDetailsBO = new CarLoanDetailsBO();
				setCommonProperty(applicationRequestBO, carLoanDetailsBO, cpUserCode, brUserId);
				return carLoanDetailsService.save(carLoanDetailsBO);

			case ApplicationType.OVERDRAFT_FACILITIES_LOAN:
				OverDraftFacilitiesLoanDetailsBO overDraftFacilitiesLoanDetailsBO = new OverDraftFacilitiesLoanDetailsBO();
				setCommonProperty(applicationRequestBO, overDraftFacilitiesLoanDetailsBO, cpUserCode, brUserId);
				return overDraftFacilitiesLoanDetailsService.save(overDraftFacilitiesLoanDetailsBO);

			case ApplicationType.DROPLINE_OVERDRAFT_FACILITIES_LOAN:
				DropLineOdFacilitiesLoanDetailsBO dropLineOdFacilitiesLoanDetailsBO = new DropLineOdFacilitiesLoanDetailsBO();
				setCommonProperty(applicationRequestBO, dropLineOdFacilitiesLoanDetailsBO, cpUserCode, brUserId);
				return dropLineOdFacilitiesLoanDetailsService.save(dropLineOdFacilitiesLoanDetailsBO);

			case ApplicationType.BANK_GUARANTEE_LOAN:
				BankGuaranteeLoanDetailsBO bankGuaranteeLoanDetailsBO = new BankGuaranteeLoanDetailsBO();
				setCommonProperty(applicationRequestBO, bankGuaranteeLoanDetailsBO, cpUserCode, brUserId);
				return bankGuaranteeLoanDetailsService.save(bankGuaranteeLoanDetailsBO);

			case ApplicationType.CC_FACILITIES_LOAN:
				CCFacilitiesLoanDetailsBO cCFacilitiesLoanDetailsBO = new CCFacilitiesLoanDetailsBO();
				setCommonProperty(applicationRequestBO, cCFacilitiesLoanDetailsBO, cpUserCode, brUserId);
				return ccFacilitiesLoanDetailsService.save(cCFacilitiesLoanDetailsBO);

			case ApplicationType.TERM_LOAN:
				TermLoanDetailsBO termLoanDetailsBO = new TermLoanDetailsBO();
				setCommonProperty(applicationRequestBO, termLoanDetailsBO, cpUserCode, brUserId);
				return termLoanDetailsService.save(termLoanDetailsBO);

			case ApplicationType.LOAN_AGAINST_FDS:
				LoanAgainstFDsDetailsBO loanAgainstFDsDetailsBO = new LoanAgainstFDsDetailsBO();
				setCommonProperty(applicationRequestBO, loanAgainstFDsDetailsBO, cpUserCode, brUserId);
				return loanAgainstFDsDetailsService.save(loanAgainstFDsDetailsBO);

			case ApplicationType.LOAN_AGAINST_SECURITIS:
				LoanAgainstSecuritiesLoanDetailsBO loanAgainstSecuritiesLoanDetailsBO = new LoanAgainstSecuritiesLoanDetailsBO();
				setCommonProperty(applicationRequestBO, loanAgainstSecuritiesLoanDetailsBO, cpUserCode, brUserId);
				return loanAgainstSecuritiesLoanDetailsService.save(loanAgainstSecuritiesLoanDetailsBO);

			case ApplicationType.PROJECT_FINANCE_LOAN:
				ProjectFinanceLoanDetailsBO projectFinanceLoanDetailsBO = new ProjectFinanceLoanDetailsBO();
				setCommonProperty(applicationRequestBO, projectFinanceLoanDetailsBO, cpUserCode, brUserId);
				return projectFinanceLoanDetailsService.save(projectFinanceLoanDetailsBO);

			case ApplicationType.PRIVATE_EQUITY_FINANCE_LOAN:
				PrivateEquityFinanceLoanDetailsBO privateEquityFinanceLoanDetailsBO = new PrivateEquityFinanceLoanDetailsBO();
				setCommonProperty(applicationRequestBO, privateEquityFinanceLoanDetailsBO, cpUserCode, brUserId);
				return privateEquityFinanceLoanDetailsService.save(privateEquityFinanceLoanDetailsBO);

			case ApplicationType.GOLD_LOAN:
				GoldLoanDetailsBO goldLoanDetailsBO = new GoldLoanDetailsBO();
				setCommonProperty(applicationRequestBO, goldLoanDetailsBO, cpUserCode, brUserId);
				return goldLoanDetailsService.save(goldLoanDetailsBO);

			case ApplicationType.OTHER_LOAN:
				OthersLoanDetailsBO othersLoanDetailsBO = new OthersLoanDetailsBO();
				setCommonProperty(applicationRequestBO, othersLoanDetailsBO, cpUserCode, brUserId);
				return othersLoanDetailsService.save(othersLoanDetailsBO);

			case ApplicationType.PERSONAL_LOAN:
				PersonalLoanDetailsBO personalLoanDetailsBO = new PersonalLoanDetailsBO();
				setCommonProperty(applicationRequestBO, personalLoanDetailsBO, cpUserCode, brUserId);
				return personalLoanDetailsService.save(personalLoanDetailsBO);

			default:
				return null;
			}
		} catch (Exception e) {
			logger.info("Error while save application form data");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@Override
	public int inActiveByUserId(Long userId) {
		return applicationsRepository.inActiveByUserId(userId);
	}

	private void setCommonProperty(ApplicationsBO from,ApplicationsBO to,String cpUserCode,Long brUserId) {
//		to.setId(from.getId());
		to.setApplicationTypeId(from.getApplicationTypeId());
		to.setUserId(brUserId);
		to.setLoanAmount(from.getLoanAmount());
		to.setLoanTypeId(Long.valueOf(CommonUtils.LoanType.CURRENT_LOAN));
		to.setIsFromCP(from.getIsFromCP());
		to.setStatus(CommonUtils.Status.OPEN);
		to.setLeadReferenceNo(cpUserCode);
	}

	@Override
	public int inActiveByApplicationIdAndUserId(Long applicationId, Long userId) {
		logger.info("Start inActiveByApplicationIdAndUserId()");
		logger.info("ApplicationId====>" + applicationId  + " User Id===> " + userId);
		int count = 0;
		if(CommonUtils.isObjectNullOrEmpty(userId)) {
			logger.log(Level.INFO, "UserId is NUll So Inactivating only by ApplicationId ");
			count = applicationsRepository.inActiveByApplicationId(applicationId);
		}else {
			count = applicationsRepository.inActiveByApplicationIdAndUserId(applicationId, userId);
			logger.log(Level.INFO, "Inactivating Application by UserId and Application Id");
		}
		logger.info("Inactivated Rows=================>" + count);
		logger.log(Level.INFO, "Start inActiveByApplicationIdAndUserId()");
		return count;
	}
	
	

}
