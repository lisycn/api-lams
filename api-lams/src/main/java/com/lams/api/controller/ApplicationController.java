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
import com.lams.model.bo.ApplicationRequestBO;
import com.lams.model.bo.ApplicationsBO;
import com.lams.model.bo.LamsResponse;
import com.lams.model.bo.LoginResponse;
import com.lams.model.bo.UserBO;
import com.lams.model.utils.CommonUtils;


@RestController
@RequestMapping(value="/application")
public class ApplicationController {
	
	@Autowired
	private ApplicationsService applicationsService; 

	public static final Logger logger = Logger.getLogger(ApplicationController.class);
	
	@RequestMapping(value="/getAll",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getAll (HttpServletRequest httpServletRequest){
		logger.info("Enter in application list");
		Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		try {
			List<ApplicationsBO> applicationsBO = applicationsService.getAll(userId);
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.OK.value(), "Successfully get data", applicationsBO),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while get application list ---------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/get/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getAll (@PathVariable("id") Long id){
		logger.info("Enter in application by id");
		try {
			ApplicationsBO applicationsBO = applicationsService.get(id);
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.OK.value(), "Successfully get data", applicationsBO),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while get application by id ---------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> save (@RequestBody ApplicationRequestBO applicationsBO,HttpServletRequest httpServletRequest){
		logger.info("Enter in save application");
		Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		if(CommonUtils.isObjectNullOrEmpty(applicationsBO.getApplicationTypeId())) {
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Application Type Null Or Empty"),
					HttpStatus.OK);
		}
		if(CommonUtils.isObjectNullOrEmpty(applicationsBO.getData())) {
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Data value Null Or Empty"),
					HttpStatus.OK);
		}
		applicationsBO.setUserId(userId);
		try {
			Long id = applicationsService.save(applicationsBO);
			if(!CommonUtils.isObjectNullOrEmpty(id)) {
				return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.OK.value(), "Successfully save data", id),
						HttpStatus.OK);	
			} else {
				return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Request"),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info("Throw Exception while save application by id ---------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/getLoanDetails/{id}/{appTypeId}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> save (@PathVariable("id") Long id,@PathVariable("appTypeId") Long appTypeId){
		logger.info("Enter in save application");
		if(CommonUtils.isObjectNullOrEmpty(appTypeId)) {
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Application Type Null Or Empty"),
					HttpStatus.OK);
		}
		if(CommonUtils.isObjectNullOrEmpty(id)) {
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Id Null Or Empty"),
					HttpStatus.OK);
		}
		try {
			LamsResponse lamsResponse = applicationsService.getLoanApplicationDetails(id, appTypeId);
			lamsResponse.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<LamsResponse>(lamsResponse,HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while save application by id ---------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
