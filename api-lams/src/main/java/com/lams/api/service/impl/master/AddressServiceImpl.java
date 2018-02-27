package com.lams.api.service.impl.master;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lams.api.domain.User;
import com.lams.api.domain.master.AddressMstr;
import com.lams.api.domain.master.CityMstr;
import com.lams.api.repository.master.AddressMstrRepository;
import com.lams.api.service.master.AddressService;
import com.lams.model.bo.AddressBO;
import com.lams.model.utils.CommonUtils;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	public static final Logger logger = Logger.getLogger(AddressServiceImpl.class.getName());

	@Autowired
	private AddressMstrRepository addressMstrRepository;

	@Override
	public void saveAddress(AddressBO addressBO, Long userId) {
		logger.log(Level.INFO, "Start saveAddress()");
		if (CommonUtils.isObjectNullOrEmpty(addressBO)) {
			logger.log(Level.INFO, "Address must not be null while saving");
			return;
		}
		AddressMstr addressMstr = null;
		if (CommonUtils.isObjectNullOrEmpty(addressBO.getId())) {
			addressMstr = new AddressMstr();
			addressMstr.setIsActive(true);
			addressMstr.setCreatedBy(userId);
			addressMstr.setUser(new User(userId));
		} else {
			addressMstr = addressMstrRepository.findByIdAndIsActive(addressBO.getId(), true);
			if (CommonUtils.isObjectNullOrEmpty(addressMstr)) {
				logger.log(Level.WARNING, "Invalid Address Id===>{}", addressBO.getId());
				return;
			}
			addressMstr.setModifiedBy(userId);
			addressMstr.setModifiedDate(new Date());
		}
		
		if (!CommonUtils.isObjectNullOrEmpty(addressBO.getCity())
				&& !CommonUtils.isObjectNullOrEmpty(addressBO.getCity().getId())) {
			addressMstr.setCity(new CityMstr(addressBO.getCity().getId()));
		}
		addressMstr.setLandMark(addressBO.getLandMark());
		addressMstr.setStreetName(addressBO.getStreetName());
		addressMstrRepository.save(addressMstr);
		logger.log(Level.INFO, "End saveAddress()");
	}

}
