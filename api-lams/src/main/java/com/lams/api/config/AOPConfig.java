package com.lams.api.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lams.api.domain.LoginAuditTrail;
import com.lams.api.repository.LoginAuditTrailRepository;
import com.lams.model.utils.CommonUtils;

@Aspect
@Component
@Transactional
public class AOPConfig {


	private Logger logger = LoggerFactory.getLogger(AOPConfig.class);
	
	@Autowired
	private LoginAuditTrailRepository auditTrailRepository; 
	
	 @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) && " +
		        "@annotation(requestMapping) && " +
		        "execution(* *(..))")
	 public void controller(RequestMapping requestMapping) {}

	@Before("controller(requestMapping)")
	public void beforeMethod(JoinPoint joinPoint,RequestMapping requestMapping) throws Exception {
		
		String url = requestMapping.value()[0];
		logger.info("Enter in authentication --------------------------------> " + url);
		if(CommonUtils.lamsUrls.contains(url)) {
			logger.info("Skip Authentication URI -------------> "+url);
			return;
		}
		 
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
		
		String token = request.getHeader("token");
		if(CommonUtils.isObjectNullOrEmpty(token)) {
			logger.info("Token can't found from http request");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			throw new BadRequestException("Bad Request, Token null or Empty !!",HttpStatus.BAD_REQUEST.value());
		}
		LoginAuditTrail loginAuditTrail = auditTrailRepository.findByTokenAndIsActive(token, true);
		if(CommonUtils.isObjectNullOrEmpty(loginAuditTrail)) {
			logger.info("Token is not valid -----------------------> " + token);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			throw new BadRequestException("Token is not valid !!",HttpStatus.UNAUTHORIZED.value());
		}
		if(CommonUtils.findDiffBetTwoDate(loginAuditTrail.getLoginDate()) > 0) {
			logger.info("Token is expire -----------------------> " + token);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);	
			throw new BadRequestException("Token is Expire !!",HttpStatus.UNAUTHORIZED.value());
		}
		request.setAttribute(CommonUtils.USER_ID, loginAuditTrail.getUserId());
		request.setAttribute(CommonUtils.USER_TYPE, loginAuditTrail.getUserType());
		return;
	}
}
