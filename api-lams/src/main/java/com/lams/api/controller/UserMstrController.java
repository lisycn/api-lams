package com.lams.api.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lams.api.service.UserMstrService;
import com.lams.model.bo.LamsResponse;
import com.lams.model.bo.UserBO;
import com.lams.model.utils.CommonUtils;

//@CrossOrigin(origins = {"http://localhost:*","http://localhost:*"})
@RestController
public class UserMstrController {

	public final static Logger logger = Logger.getLogger(UserMstrController.class.getName());
	
	@Autowired
	private UserMstrService userMstrService;
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> registration(@RequestBody UserBO userBO){
		logger.info("Enter in registration process");
		if(CommonUtils.isObjectNullOrEmpty(userBO.getEmail())) {
			logger.info("Email is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Email is Null Or Empty"), HttpStatus.OK);
		}
		
		if(CommonUtils.isObjectNullOrEmpty(userBO.getMobile())) {
			logger.info("Mobile is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Mobile is Null Or Empty"), HttpStatus.OK);
		}
		
		if(CommonUtils.isObjectNullOrEmpty(userBO.getPassword())) {
			logger.info("Password is null or empty");
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Password is Null Or Empty"), HttpStatus.OK);
		}
		
		try {
			return new ResponseEntity<LamsResponse>(userMstrService.registration(userBO), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while registrion ---------------->" +userBO.getEmail());
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value = "/getUsersByType/{userType}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getUsersByType(@PathVariable(value="userType",required=false) Long userType){
		logger.info("Enter in get users by user type");
		try {
			List<UserBO> userList = userMstrService.getUsersByUserType(userType);
			logger.info("Successfully get users data by type id ------------>" + userType);
			return new ResponseEntity<LamsResponse>(
					new LamsResponse(HttpStatus.OK.value(), "Successfully get userList",userList), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while get users by user type");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
