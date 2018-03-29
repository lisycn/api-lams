package com.lams.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lams.api.domain.Applications;
import com.lams.api.domain.LenderApplicationMapping;
import com.lams.api.domain.LenderBorrowerConnection;
import com.lams.api.domain.User;
import com.lams.api.repository.ApplicationsRepository;
import com.lams.api.repository.LenderApplicationMappingRepository;
import com.lams.api.repository.LenderBorrowerConnectionRepository;
import com.lams.api.repository.UserMstrRepository;
import com.lams.api.service.ApplicationsService;
import com.lams.api.service.LenderBorrowerConnectionService;
import com.lams.model.bo.ApplicationsBO;
import com.lams.model.bo.BankBO;
import com.lams.model.bo.LenderApplicationMappingBO;
import com.lams.model.bo.LenderBorrowerConnectionBO;
import com.lams.model.bo.UserBO;
import com.lams.model.bo.master.ApplicationTypeMstrBO;
import com.lams.model.utils.CommonUtils;

@Service
@Transactional
public class LenderBorrowerConnectionServiceImpl implements LenderBorrowerConnectionService {

	@Autowired
	private LenderBorrowerConnectionRepository repo;

	@Autowired
	private ApplicationsRepository appRepo;

	@Autowired
	private LenderApplicationMappingRepository map;

	@Autowired
	private ApplicationsService applicationService;

	@Autowired
	private UserMstrRepository userMstrRepository;

	@Override
	public List<LenderBorrowerConnectionBO> getConnections(Long applicationId, String status) {
		// TODO Auto-generated method stub
		List<LenderBorrowerConnection> responseData = repo.getListByApplication(applicationId, status);
		List<LenderBorrowerConnectionBO> response = new ArrayList<>();
		for (LenderBorrowerConnection con : responseData) {
			LenderBorrowerConnectionBO res = new LenderBorrowerConnectionBO();
			BeanUtils.copyProperties(con, res);
			ApplicationsBO app = new ApplicationsBO();
			BeanUtils.copyProperties(con.getApplication(), app);
			res.setApplication(app);

			LenderApplicationMapping lenderApplicationMapping = con.getLenderApplicationMapping();
			if (!CommonUtils.isObjectNullOrEmpty(lenderApplicationMapping)) {
				LenderApplicationMappingBO applicationMappingBO = new LenderApplicationMappingBO();
				BeanUtils.copyProperties(lenderApplicationMapping, applicationMappingBO);
				if (!CommonUtils.isObjectNullOrEmpty(lenderApplicationMapping.getApplicationTypeId())) {
					ApplicationTypeMstrBO applicationTypeMstrBO = new ApplicationTypeMstrBO();
					BeanUtils.copyProperties(lenderApplicationMapping.getApplicationTypeId(), applicationTypeMstrBO);
					applicationMappingBO.setApplicationTypeMstrBO(applicationTypeMstrBO);
				}

				if (!CommonUtils.isObjectNullOrEmpty(lenderApplicationMapping.getUserId())) {
					User user = userMstrRepository.findOne(lenderApplicationMapping.getUserId());
					if (!CommonUtils.isObjectNullOrEmpty(user)) {
						UserBO target = new UserBO();
						BeanUtils.copyProperties(user, target);
						if(!CommonUtils.isObjectNullOrEmpty(user.getBank())) {
							BankBO bankBO = new BankBO();
							BeanUtils.copyProperties(user.getBank(), bankBO);
							target.setBank(bankBO);
						}
						applicationMappingBO.setUser(target);
					}
				}
				res.setApplicationMappingBO(applicationMappingBO);
			}
			response.add(res);

		}
		return response;
	}

	@Override
	public List<LenderBorrowerConnectionBO> getConnections(Long userId, Long applicationId, Boolean isActive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long save(LenderBorrowerConnectionBO bo) {

		applicationService.updateStatus(bo.getApplication().getId(), CommonUtils.Status.RESPONDED);

		LenderBorrowerConnection obj = new LenderBorrowerConnection();

		obj.setLoanPossibleAmount(bo.getLoanPossibleAmount());
		obj.setTenure(bo.getTenure());
		obj.setRoi(bo.getRoi());
		obj.setProcessingFees(bo.getProcessingFees());
		obj.setTermAndCondition(bo.getTermAndCondition());
		obj.setComments(bo.getComments());
		obj.setCreatedBy(bo.getCreatedBy());
		obj.setCreatedDate(new Date());
		obj.setIsActive(Boolean.TRUE);

		Applications app = appRepo.findOne(bo.getApplication().getId());
		obj.setApplication(app);

		List<Long> mapResult = map.findByUserIdAndApplicationTypeId(bo.getCreatedBy(),
				bo.getApplication().getApplicationTypeId());
		obj.setLenderApplicationMapping(map.findOne(mapResult.get(0)));

		obj = repo.save(obj);
		return obj.getId();
	}

}
