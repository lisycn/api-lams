package com.lams.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.Applications;

public interface ApplicationsRepository  extends JpaRepository<Applications, Long>{

	public List<Applications> findByUserIdAndIsActive(Long userId,Boolean isActive);
	
	public Applications findByIdAndIsActive(Long id,Boolean isActive);
}
