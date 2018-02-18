package com.lams.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lams.api.service.LoginService;
import com.lams.model.bo.LoginResponse;
import com.lams.model.bo.UserBO;
import com.lams.model.utils.CommonUtils;

@RestController
public class LoginController {

	public static final Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/login",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponse> login (@RequestBody UserBO userBO){
		logger.info("Enter in login service");
		if(CommonUtils.isObjectNullOrEmpty(userBO.getEmail())) {
			logger.info("Email id null or empty");
			return new ResponseEntity<LoginResponse>(
					new LoginResponse(HttpStatus.BAD_REQUEST.value(), "Email can't be null or empty"),HttpStatus.OK);
		}
		if(CommonUtils.isObjectNullOrEmpty(userBO.getPassword())) {
			logger.info("Password id null or empty");
			return new ResponseEntity<LoginResponse>(
					new LoginResponse(HttpStatus.BAD_REQUEST.value(), "Password can't be null or empty"),HttpStatus.OK);
		}
		try {
			return new ResponseEntity<LoginResponse>(loginService.login(userBO),HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while login user ---------------->"  +userBO.getEmail());
			e.printStackTrace();
			return new ResponseEntity<LoginResponse>(
					new LoginResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/logout",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponse> login (HttpServletRequest httpServletRequest){
		logger.info("Enter in logout service");
		String token = httpServletRequest.getHeader("token");
		if(CommonUtils.isObjectNullOrEmpty(token)) {
			logger.info("Token id null or empty");
			return new ResponseEntity<LoginResponse>(
					new LoginResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Requet"),HttpStatus.OK);
		}
		try {
			return new ResponseEntity<LoginResponse>(loginService.logout(token),HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Exception while logout user ---------------->"  +token);
			e.printStackTrace();
			return new ResponseEntity<LoginResponse>(
					new LoginResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
