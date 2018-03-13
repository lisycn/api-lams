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

import com.lams.api.domain.master.BankMstr;
import com.lams.api.repository.master.BankMstrRepository;
import com.lams.api.service.master.BankService;
import com.lams.model.bo.master.MasterBaseBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.Enums;
import com.lams.model.utils.Enums.Mode;

@Service
@Transactional
public class BankServiceImpl implements BankService {

	public static final Logger logger = Logger.getLogger(BankServiceImpl.class.getName());

	@Autowired
	private BankMstrRepository bankMstrRepository;

	@Override
	public List<MasterBaseBO> getBanksByMode(Integer mode) {
		logger.log(Level.INFO, "Entry in getBanksByMode");
		List<BankMstr> banks = null;
		Mode type = Enums.Mode.getType(mode);
		switch (type) {
		case ACTIVE:
			banks = bankMstrRepository.findByIsActive(true);
			break;
		case INACTIVE:
			banks = bankMstrRepository.findByIsActive(false);
			break;
		case BOTH:
			banks = bankMstrRepository.findAll();
			break;
		default:
			break;
		}
		if (CommonUtils.isListNullOrEmpty(banks)) {
			logger.log(Level.WARNING, "No salutaions found for MODE===>{0}", mode);
			return Collections.emptyList();
		}

		List<MasterBaseBO> response = new ArrayList<>(banks.size());
		for (BankMstr bank : banks) {
			MasterBaseBO baseBO = new MasterBaseBO();
			BeanUtils.copyProperties(bank, baseBO);
			response.add(baseBO);
		}
		logger.log(Level.INFO, "Exit in getBanksByMode");
		return response;
	}
}
