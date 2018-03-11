package com.lams.api.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.master.ApplicationTypeMstr;


public interface ApplicationTypeMstrRepository extends JpaRepository<ApplicationTypeMstr, Long>{

	public List<ApplicationTypeMstr> findByIsActive(Boolean isActive);
}
