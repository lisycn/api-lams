package com.lams.api.service.impl.master;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lams.api.domain.master.LoanTypeMstr;
import com.lams.api.repository.master.LoanTypeMstrRepository;
import com.lams.api.service.master.LoanTypeMstrService;
import com.lams.model.bo.master.MasterBaseBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.Enums;
import com.lams.model.utils.Enums.Mode;

@Service
@Transactional
public class LoanTypeMstrServiceImpl implements LoanTypeMstrService{

public static final Logger logger = Logger.getLogger(LoanTypeMstrServiceImpl.class.getName());
	
	@Autowired
	private LoanTypeMstrRepository loanTypeMstrRepository;

	@Override
	public List<MasterBaseBO> getLoanTypeByMode(Integer mode) {
		logger.log(Level.INFO, "Entry in getLoanTypeByMode");
		List<LoanTypeMstr> loanTypes = null;
		Mode type = Enums.Mode.getType(mode);
		switch (type) {
		case ACTIVE:
			loanTypes = loanTypeMstrRepository.findByIsActive(true);
			break;
		case INACTIVE:
			loanTypes = loanTypeMstrRepository.findByIsActive(false);
			break;
		case BOTH:
			loanTypes = loanTypeMstrRepository.findAll();
			break;
		default:
			break;
		}
		if (CommonUtils.isListNullOrEmpty(loanTypes)) {
			logger.log(Level.WARNING, "No Loan Type found for MODE===>{0}", mode);
			return Collections.emptyList();
		}

		List<MasterBaseBO> response = new ArrayList<>(loanTypes.size());
		for (LoanTypeMstr loanType : loanTypes) {
			MasterBaseBO baseBO = new MasterBaseBO();
			BeanUtils.copyProperties(loanType, baseBO);
			response.add(baseBO);
		}
		logger.log(Level.INFO, "Exit in getLoanTypeByMode");
		return response;
	} 
	
	
}
