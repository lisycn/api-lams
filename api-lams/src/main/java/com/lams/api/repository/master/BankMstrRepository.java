package com.lams.api.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.master.BankMstr;

public interface BankMstrRepository extends JpaRepository<BankMstr, Long>{

	public List<BankMstr> findByIsActive(Boolean isActive);
	
}
