package com.lams.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lams.api.service.ApplicationsService;
import com.lams.api.service.LenderApplicationMappingService;
import com.lams.api.service.LenderBorrowerConnectionService;
import com.lams.model.bo.ApplicationRequestBO;
import com.lams.model.bo.ApplicationsBO;
import com.lams.model.bo.LamsResponse;
import com.lams.model.bo.LenderApplicationMappingBO;
import com.lams.model.bo.LenderBorrowerConnectionBO;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.Enums;

@RestController
@RequestMapping(value = "/application")
public class ApplicationController {

	@Autowired
	private ApplicationsService applicationsService;

	@Autowired
	private LenderApplicationMappingService applicationMappingService;

	@Autowired
	private LenderBorrowerConnectionService lenderBorrowerService;

	public static final Logger logger = Logger.getLogger(ApplicationController.class);

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getAll(HttpServletRequest httpServletRequest) {
		logger.info("Enter in application list");
		Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		Long userType = (Long) httpServletRequest.getAttribute(CommonUtils.USER_TYPE);
		try {
			if (Enums.UserType.BORROWER.getId() == userType) {
				List<ApplicationsBO> applicationsBO = applicationsService.getAll(userId);
				return new ResponseEntity<LamsResponse>(
						new LamsResponse(HttpStatus.OK.value(), "Successfully get data", applicationsBO),
						HttpStatus.OK);
			} else if (Enums.UserType.LENDER.getId() == userType) {
				List<LenderApplicationMappingBO> list = applicationMappingService
						.getApplicationTypeByUserIdAndIsActive(userId, true);
				return new ResponseEntity<LamsResponse>(
						new LamsResponse(HttpStatus.OK.value(), "Successfully get data", list), HttpStatus.OK);
			} else {
				return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.OK.value(), "Invalid User"),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info("Throw Exception while get application list ---------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getAll(@PathVariable("id") Long id) {
		logger.info("Enter in application by id");
		try {
			ApplicationsBO applicationsBO = applicationsService.get(id);
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.OK.value(), "Successfully get data", applicationsBO), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while get application by id ---------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> save(@RequestBody ApplicationRequestBO applicationsBO,
			HttpServletRequest httpServletRequest) {
		logger.info("Enter in save application");
		Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		if (CommonUtils.isObjectNullOrEmpty(applicationsBO.getApplicationTypeId())) {
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Application Type Null Or Empty"), HttpStatus.OK);
		}
		if (CommonUtils.isObjectNullOrEmpty(applicationsBO.getData())) {
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Data value Null Or Empty"), HttpStatus.OK);
		}
		applicationsBO.setUserId(userId);
		try {
			Long id = applicationsService.save(applicationsBO);
			if (!CommonUtils.isObjectNullOrEmpty(id)) {
				return new ResponseEntity<LamsResponse>(
						new LamsResponse(HttpStatus.OK.value(), "Successfully save data", id), HttpStatus.OK);
			} else {
				return new ResponseEntity<LamsResponse>(
						new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Request"), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info("Throw Exception while save application by id ---------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getLoanDetails/{id}/{appTypeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> save(@PathVariable("id") Long id, @PathVariable("appTypeId") Long appTypeId,
			HttpServletRequest httpServletRequest) {
		logger.info("Enter in get application details");
		if (CommonUtils.isObjectNullOrEmpty(appTypeId)) {
			logger.info("Application Type Null Or Empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Application Type Null Or Empty"), HttpStatus.OK);
		}
		if (CommonUtils.isObjectNullOrEmpty(id)) {
			logger.info("Id Null Or Empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Id Null Or Empty"), HttpStatus.OK);
		}
		Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		try {
			LamsResponse lamsResponse = applicationsService.getLoanApplicationDetails(id, appTypeId, userId);
			lamsResponse.setStatus(HttpStatus.OK.value());
			logger.info("Successfully get details");
			return new ResponseEntity<LamsResponse>(lamsResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while save application by id ---------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/get_borrowers_for_lender", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getBorrowerForLender(HttpServletRequest httpServletRequest) {
		logger.info("Enter in get application details");
		Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		if (CommonUtils.isObjectNullOrEmpty(userId)) {
			logger.info("User Id Null Or Empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "UnAuthorized! Please try again to Login"),
					HttpStatus.OK);
		}
		try {
			logger.info("Successfully get details");
			return new ResponseEntity<LamsResponse>(applicationsService.getApplicationsForLender(userId),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while Getting Matches Borrowers ---------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/get_borrowers_for_lender_app_id/{id}/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getBorrowerForLenderByAppId(@PathVariable("id") Long id,
			@PathVariable("status") String status, HttpServletRequest httpServletRequest) {
		
		Long lenderId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);

		try {
			return new ResponseEntity<LamsResponse>(
					applicationsService.getApplicationsForLenderByApplicationId(id, status, lenderId), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while Getting Matches Borrowers ---------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}

	// @RequestMapping(value="/get_application_details_for_lender/{appId}/{userId}",
	// method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<LamsResponse>
	// getApplicationDetailsByIdAndUserId(@PathVariable("appId") Long appId,
	// @PathVariable("userId") Long userId, HttpServletRequest httpServletRequest){
	//
	// try {
	// return new
	// ResponseEntity<LamsResponse>(applicationsService.getApplicationDetailsByApplicationTypeIdAndUserId(appId,
	// userId), HttpStatus.OK);
	// } catch (Exception e) {
	// logger.info("Throw Exception while Getting Application Details
	// ---------------->");
	// e.printStackTrace();
	// return new ResponseEntity<LamsResponse>(new
	// LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
	// CommonUtils.SOMETHING_WENT_WRONG),HttpStatus.OK);
	// }
	// }

	@RequestMapping(value = "/save_approval_request", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> saveApprovalRequest(@RequestBody LenderBorrowerConnectionBO connectionBo,
			HttpServletRequest httpServletRequest) {

		Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		logger.info("User ID-----------> " + userId);
		if (CommonUtils.isObjectNullOrEmpty(connectionBo.getApplication())) {
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Application Type Null Or Empty"), HttpStatus.OK);
		}

		connectionBo.setCreatedBy(userId);

		try {
			connectionBo.setStatus(CommonUtils.Status.RESPONDED);
			Long id = lenderBorrowerService.save(connectionBo);
			if (!CommonUtils.isObjectNullOrEmpty(id)) {
				return new ResponseEntity<LamsResponse>(
						new LamsResponse(HttpStatus.OK.value(), "Successfully save data", id), HttpStatus.OK);
			} else {
				return new ResponseEntity<LamsResponse>(
						new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Request"), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info("Throw Exception while save application by id ---------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/get_connections/{appId}/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getConnections(@PathVariable("appId") Long appId,
			@PathVariable("status") String status, HttpServletRequest httpServletRequest) {
		logger.info("Enter in get getConnections");
		Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		if (CommonUtils.isObjectNullOrEmpty(userId)) {
			logger.info("User Id Null Or Empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "UnAuthorized! Please try again to Login"),
					HttpStatus.OK);
		}

		if (CommonUtils.isObjectNullOrEmpty(appId)) {
			logger.info("Application Id Null Or Empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.INVALID_REQUEST), HttpStatus.OK);
		}

		if (CommonUtils.isObjectNullOrEmpty(status)) {
			logger.info("StatusId Null Or Empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.INVALID_REQUEST), HttpStatus.OK);
		}
		try {

			logger.info("Successfully get details");
			List<LenderBorrowerConnectionBO> connections = lenderBorrowerService.getConnections(appId, status);
			LamsResponse lamsResponse = new LamsResponse(HttpStatus.OK.value(), "Success", connections);
			return new ResponseEntity<LamsResponse>(lamsResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while Getting Matches Borrowers ---------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/update_status/{status}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> updateStatus(@PathVariable("status") String status,
			@RequestBody LenderBorrowerConnectionBO connectionBO, HttpServletRequest httpServletRequest) {
		logger.info("Enter in get updateStatus");
		Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		if (CommonUtils.isObjectNullOrEmpty(userId)) {
			logger.info("User Id Null Or Empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "UnAuthorized! Please try again to Login"),
					HttpStatus.OK);
		}

		if (CommonUtils.isObjectNullOrEmpty(connectionBO.getApplication())
				|| CommonUtils.isObjectNullOrEmpty(connectionBO.getApplication().getId())) {
			logger.info("Application Id Null Or Empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.INVALID_REQUEST), HttpStatus.OK);
		}

		if (CommonUtils.isObjectNullOrEmpty(status)) {
			logger.info("StatusId Null Or Empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), CommonUtils.INVALID_REQUEST), HttpStatus.OK);
		}
		try {

			logger.info("Successfully get details");
			connectionBO.setStatus(status);
			LamsResponse lamsResponse = new LamsResponse(HttpStatus.OK.value(), "Success",
					lenderBorrowerService.save(connectionBO));
			return new ResponseEntity<LamsResponse>(lamsResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while Getting Matches Borrowers ---------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/get_responded_application/{brId}/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getRespondedApplicationsByBorrowerIdAndAppTypeId(
			@PathVariable("brId") Long brId, @PathVariable("applicationId") Long applicationId,
			HttpServletRequest httpServletRequest) {
		Long lrId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		try {
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.OK.value(), "Successfully get data",
					lenderBorrowerService.getRespondedApplication(lrId, brId, applicationId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while Getting Application Details ---------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonUtils.SOMETHING_WENT_WRONG),
					HttpStatus.OK);
		}
	}
}
