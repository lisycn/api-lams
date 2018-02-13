package com.lams.api.service.impl;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lams.api.repository.UserMstrRepository;
import com.lams.api.service.UserMstrService;

@Service
@Transactional
public class UserMstrServiceImpl implements UserMstrService{

	public final static Logger logger = Logger.getLogger(UserMstrServiceImpl.class.getName()); 
	
	@Autowired
	private UserMstrRepository userMstrRepository;
	
}
