package com.lams.api.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.master.LoanTypeMstr;

public interface LoanTypeMstrRepository  extends JpaRepository<LoanTypeMstr, Long>{

	public List<LoanTypeMstr> findByIsActive(Boolean isActive);
}
