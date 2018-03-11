package com.lams.api.controller.master;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lams.api.service.master.ApplicationTypeMstrService;
import com.lams.api.service.master.CityService;
import com.lams.api.service.master.CountryService;
import com.lams.api.service.master.LoanTypeMstrService;
import com.lams.api.service.master.SalutationService;
import com.lams.api.service.master.StateService;
import com.lams.model.bo.LamsResponse;
import com.lams.model.bo.LoginResponse;
import com.lams.model.bo.master.MasterBaseBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.Enums;
import com.lams.model.utils.Enums.YesNoType;

@RestController
@RequestMapping("/master")
public class MasterController {

	public static final Logger logger = Logger.getLogger(MasterController.class.getName());

	@Autowired
	private CountryService countryService;

	@Autowired
	private StateService stateService;

	@Autowired
	private CityService cityService;
	
	@Autowired
	private SalutationService salutationService;
	
	@Autowired
	private ApplicationTypeMstrService applicationTypeMstrService;
	
	@Autowired
	private LoanTypeMstrService loanTypeMstrService;
	

	@RequestMapping(value = "/get_country/{mode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getCountry(@PathVariable("mode") Integer mode) {
		logger.info("Enter in getCountry");
		try {
			if (CommonUtils.isObjectNullOrEmpty(mode)) {
				logger.log(Level.WARNING, "No Mode Found so Returnig All Countries===>Mode====>{}", mode);
				mode = Enums.Mode.BOTH.getId();
			}
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.OK.value(), "Success", countryService.getCountriesByMode(mode)),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while Getting Countries by Mode==>{}" + mode);
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LoginResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/get_state_by_country_id/{countryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getStateByCountryId(@PathVariable("countryId") Long countryId) {
		logger.info("Enter in getStateByCountryId");
		try {
			if (CommonUtils.isObjectNullOrEmpty(countryId)) {
				logger.log(Level.WARNING, "Country Id must no be NULL====>{}", countryId);
				return new ResponseEntity<LamsResponse>(
						new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.INVALID_REQUEST), HttpStatus.OK);
			}

			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.OK.value(), "Success", stateService.getStatesByCountryId(countryId)),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while Getting States by Country Id==>{}" + countryId);
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LoginResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/get_city_by_state_id/{stateId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getCityByStateId(@PathVariable("stateId") Long stateId) {
		logger.info("Enter in getCityByStateId");
		try {
			if (CommonUtils.isObjectNullOrEmpty(stateId)) {
				logger.log(Level.WARNING, "State Id must no be NULL====>{}", stateId);
				return new ResponseEntity<LamsResponse>(
						new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.INVALID_REQUEST), HttpStatus.OK);
			}

			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.OK.value(), "Success", cityService.getCitiesByStateId(stateId)),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while Getting Cities by State Id==>{}" + stateId);
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LoginResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/get_salutation/{mode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getSalutation(@PathVariable("mode") Integer mode) {
		logger.info("Enter in getSalutation");
		try {
			if (CommonUtils.isObjectNullOrEmpty(mode)) {
				logger.log(Level.WARNING, "No Mode Found so Returnig All Salutations===>Mode====>{}", mode);
				mode = Enums.Mode.BOTH.getId();
			}
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.OK.value(), "Success", salutationService.getSalutationByMode(mode)),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while Getting Salutations by Mode==>{}" + mode);
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LoginResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/get_application_type/{mode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getApplicationType(@PathVariable("mode") Integer mode) {
		logger.info("Enter in getApplicationType");
		try {
			if (CommonUtils.isObjectNullOrEmpty(mode)) {
				logger.log(Level.WARNING, "No Mode Found so Returnig All Application Type===>Mode====>{}", mode);
				mode = Enums.Mode.BOTH.getId();
			}
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.OK.value(), "Success", applicationTypeMstrService.getApplicationTypeByMode(mode)),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while Getting ApplicationType by Mode==>{}" + mode);
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LoginResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/get_loan_type/{mode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getLoanType(@PathVariable("mode") Integer mode) {
		logger.info("Enter in getLoanType");
		try {
			if (CommonUtils.isObjectNullOrEmpty(mode)) {
				logger.log(Level.WARNING, "No Mode Found so Returnig All Loan Type===>Mode====>{}", mode);
				mode = Enums.Mode.BOTH.getId();
			}
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.OK.value(), "Success", loanTypeMstrService.getLoanTypeByMode(mode)),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while Getting Loan Type by Mode==>{}" + mode);
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LoginResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/get_yes_no_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getYesNoList() {
		logger.info("Enter in getYesNoList");
		try {
			YesNoType[] yesNoTypeList = YesNoType.getAll();
			List<MasterBaseBO> masterBaseBOList = new ArrayList<>(yesNoTypeList.length);
			MasterBaseBO masterBaseBO = null;
			for(YesNoType yesNoType : yesNoTypeList) {
				masterBaseBO = new MasterBaseBO();
				masterBaseBO.setId(yesNoType.getId());
				masterBaseBO.setName(yesNoType.getValue());
				masterBaseBOList.add(masterBaseBO);
			}
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.OK.value(), "Success", masterBaseBOList),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while Getting Yes No Master List");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LoginResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}

}
