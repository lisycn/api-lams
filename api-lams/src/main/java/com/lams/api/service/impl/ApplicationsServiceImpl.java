package com.lams.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lams.api.domain.Applications;
import com.lams.api.repository.ApplicationsRepository;
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
import com.lams.model.bo.ApplicationRequestBO;
import com.lams.model.bo.ApplicationsBO;
import com.lams.model.bo.LamsResponse;
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
import com.lams.model.utils.MultipleJSONObjectHelper;
import com.lams.model.utils.CommonUtils.ApplicationType;

@Service
@Transactional
public class ApplicationsServiceImpl implements ApplicationsService{

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
	public Long save(ApplicationRequestBO applicationRequestBO){
		logger.info("Enter in Save Application Sevice Impl--------------type----> "+ applicationRequestBO.getApplicationTypeId());
		try {
			switch (applicationRequestBO.getApplicationTypeId().intValue()) {
			
			case ApplicationType.HOME_LOAN:
				HomeLoanDetailsBO  homeLoanDetailsBO = (HomeLoanDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),HomeLoanDetailsBO.class);
				homeLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				homeLoanDetailsBO.setUserId(applicationRequestBO.getUserId());
				return homeLoanDetailsService.save(homeLoanDetailsBO);
				
			case ApplicationType.LOAN_AGAINST_PROPERTY:
				LoanAgainstPropertyDetailsBO loanAgainstPropertyDetailsBO = (LoanAgainstPropertyDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),LoanAgainstPropertyDetailsBO.class);
				loanAgainstPropertyDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				loanAgainstPropertyDetailsBO.setUserId(applicationRequestBO.getUserId());
				return loanAgainstPropertyDetailsService.save(loanAgainstPropertyDetailsBO);
				
			case ApplicationType.SECURED_BUSINESS_LOAN:
				SecuredBusinessLoanDetailsBO securedBusinessLoanDetailsBO = (SecuredBusinessLoanDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),SecuredBusinessLoanDetailsBO.class);
				securedBusinessLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				securedBusinessLoanDetailsBO.setUserId(applicationRequestBO.getUserId());
				return securedBusinessLoanDetailsService.save(securedBusinessLoanDetailsBO);
				
			case ApplicationType.WORKING_CAPITAL_LOAN:
				WorkingCapitalLoanDetailsBO workingCapitalLoanDetailsBO = (WorkingCapitalLoanDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),WorkingCapitalLoanDetailsBO.class);
				workingCapitalLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				workingCapitalLoanDetailsBO.setUserId(applicationRequestBO.getUserId());
				return workingCapitalLoanDetailsService.save(workingCapitalLoanDetailsBO);
				
			case ApplicationType.EDUCATION_LOAN:
				EducationLoanDetailsBO educationLoanDetailsBO = (EducationLoanDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),EducationLoanDetailsBO.class);
				educationLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				educationLoanDetailsBO.setUserId(applicationRequestBO.getUserId());
				return educationLoanDetailsService.save(educationLoanDetailsBO);
				
			case ApplicationType.CAR_LOAN:
				CarLoanDetailsBO carLoanDetailsBO = (CarLoanDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),CarLoanDetailsBO.class);
				carLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				carLoanDetailsBO.setUserId(applicationRequestBO.getUserId());
				return carLoanDetailsService.save(carLoanDetailsBO);
			
			case ApplicationType.OVERDRAFT_FACILITIES_LOAN:
				OverDraftFacilitiesLoanDetailsBO overDraftFacilitiesLoanDetailsBO = (OverDraftFacilitiesLoanDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),OverDraftFacilitiesLoanDetailsBO.class);
				overDraftFacilitiesLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				overDraftFacilitiesLoanDetailsBO.setUserId(applicationRequestBO.getUserId());
				return overDraftFacilitiesLoanDetailsService.save(overDraftFacilitiesLoanDetailsBO);
			
			case ApplicationType.DROPLINE_OVERDRAFT_FACILITIES_LOAN:
				DropLineOdFacilitiesLoanDetailsBO dropLineOdFacilitiesLoanDetailsBO = (DropLineOdFacilitiesLoanDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),DropLineOdFacilitiesLoanDetailsBO.class);
				dropLineOdFacilitiesLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				dropLineOdFacilitiesLoanDetailsBO.setUserId(applicationRequestBO.getUserId());
				return dropLineOdFacilitiesLoanDetailsService.save(dropLineOdFacilitiesLoanDetailsBO);
			
			case ApplicationType.BANK_GUARANTEE_LOAN:
				BankGuaranteeLoanDetailsBO bankGuaranteeLoanDetailsBO = (BankGuaranteeLoanDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),BankGuaranteeLoanDetailsBO.class);
				bankGuaranteeLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				bankGuaranteeLoanDetailsBO.setUserId(applicationRequestBO.getUserId());
				return bankGuaranteeLoanDetailsService.save(bankGuaranteeLoanDetailsBO);
				
			case ApplicationType.CC_FACILITIES_LOAN:
				CCFacilitiesLoanDetailsBO cCFacilitiesLoanDetailsBO = (CCFacilitiesLoanDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),CCFacilitiesLoanDetailsBO.class);
				cCFacilitiesLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				cCFacilitiesLoanDetailsBO.setUserId(applicationRequestBO.getUserId());
				return ccFacilitiesLoanDetailsService.save(cCFacilitiesLoanDetailsBO);
				
			case ApplicationType.TERM_LOAN:
				TermLoanDetailsBO termLoanDetailsBO = (TermLoanDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),TermLoanDetailsBO.class);
				termLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				termLoanDetailsBO.setUserId(applicationRequestBO.getUserId());
				return termLoanDetailsService.save(termLoanDetailsBO);
				
			case ApplicationType.LOAN_AGAINST_FDS:
				LoanAgainstFDsDetailsBO loanAgainstFDsDetailsBO = (LoanAgainstFDsDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),LoanAgainstFDsDetailsBO.class);
				loanAgainstFDsDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				loanAgainstFDsDetailsBO.setUserId(applicationRequestBO.getUserId());
				return loanAgainstFDsDetailsService.save(loanAgainstFDsDetailsBO);
				
			case ApplicationType.LOAN_AGAINST_SECURITIS:
				LoanAgainstSecuritiesLoanDetailsBO loanAgainstSecuritiesLoanDetailsBO = (LoanAgainstSecuritiesLoanDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),LoanAgainstSecuritiesLoanDetailsBO.class);
				loanAgainstSecuritiesLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				loanAgainstSecuritiesLoanDetailsBO.setUserId(applicationRequestBO.getUserId());
				return loanAgainstSecuritiesLoanDetailsService.save(loanAgainstSecuritiesLoanDetailsBO);
				
			case ApplicationType.PROJECT_FINANCE_LOAN:
				ProjectFinanceLoanDetailsBO projectFinanceLoanDetailsBO = (ProjectFinanceLoanDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),ProjectFinanceLoanDetailsBO.class);
				projectFinanceLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				projectFinanceLoanDetailsBO.setUserId(applicationRequestBO.getUserId());
				return projectFinanceLoanDetailsService.save(projectFinanceLoanDetailsBO);
				
			case ApplicationType.PRIVATE_EQUITY_FINANCE_LOAN:
				PrivateEquityFinanceLoanDetailsBO privateEquityFinanceLoanDetailsBO = (PrivateEquityFinanceLoanDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),PrivateEquityFinanceLoanDetailsBO.class);
				privateEquityFinanceLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				privateEquityFinanceLoanDetailsBO.setUserId(applicationRequestBO.getUserId());
				return privateEquityFinanceLoanDetailsService.save(privateEquityFinanceLoanDetailsBO);
				
			case ApplicationType.GOLD_LOAN:
				GoldLoanDetailsBO goldLoanDetailsBO = (GoldLoanDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),GoldLoanDetailsBO.class);
				goldLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				goldLoanDetailsBO.setUserId(applicationRequestBO.getUserId());
				return goldLoanDetailsService.save(goldLoanDetailsBO);
				
			case ApplicationType.OTHER_LOAN:
				OthersLoanDetailsBO othersLoanDetailsBO = (OthersLoanDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),OthersLoanDetailsBO.class);
				othersLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				othersLoanDetailsBO.setUserId(applicationRequestBO.getUserId());
				return othersLoanDetailsService.save(othersLoanDetailsBO);
				
			case ApplicationType.PERSONAL_LOAN:
				PersonalLoanDetailsBO personalLoanDetailsBO = (PersonalLoanDetailsBO) MultipleJSONObjectHelper.getObjectFromString(applicationRequestBO.getData().toString(),PersonalLoanDetailsBO.class);
				personalLoanDetailsBO.setApplicationTypeId(applicationRequestBO.getApplicationTypeId());
				personalLoanDetailsBO.setUserId(applicationRequestBO.getUserId());
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
			applicationsBO.setApplicationTypeCode(applications.getApplicationTypeId().getCode());
		}
		if(!CommonUtils.isObjectNullOrEmpty(applications.getLoanTypeId())) {
			applicationsBO.setLoanTypeId(applications.getLoanTypeId().getId());
			applicationsBO.setLoanTypeName(applications.getLoanTypeId().getName());	
		}
		return applicationsBO;
	}
	
	public LamsResponse getLoanApplicationDetails(Long id,Long applicationTypeId,Long userId){
		
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
			OverDraftFacilitiesLoanDetailsBO overDraftFacilitiesLoanDetailsBO = overDraftFacilitiesLoanDetailsService.get(id);
			overDraftFacilitiesLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(overDraftFacilitiesLoanDetailsBO);
			break;
			
		case ApplicationType.DROPLINE_OVERDRAFT_FACILITIES_LOAN:
			DropLineOdFacilitiesLoanDetailsBO dropLineOdFacilitiesLoanDetailsBO = dropLineOdFacilitiesLoanDetailsService.get(id);
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
			LoanAgainstSecuritiesLoanDetailsBO loanAgainstSecuritiesLoanDetailsBO = loanAgainstSecuritiesLoanDetailsService.get(id);
			loanAgainstSecuritiesLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(loanAgainstSecuritiesLoanDetailsBO);
			break;
			
		case ApplicationType.PROJECT_FINANCE_LOAN:
			ProjectFinanceLoanDetailsBO projectFinanceLoanDetailsBO = projectFinanceLoanDetailsService.get(id);
			projectFinanceLoanDetailsBO.setEmploymentType(employmentType);
			lamsResponse.setData(projectFinanceLoanDetailsBO);
			break;
			
		case ApplicationType.PRIVATE_EQUITY_FINANCE_LOAN:
			PrivateEquityFinanceLoanDetailsBO privateEquityFinanceLoanDetailsBO = privateEquityFinanceLoanDetailsService.get(id);
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
	
}
