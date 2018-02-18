package com.lams.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.LoginAuditTrail;

public interface LoginAuditTrailRepository  extends JpaRepository<LoginAuditTrail, Long>{

	public LoginAuditTrail findByTokenAndIsActive(String token, Boolean isActive);
	
}
