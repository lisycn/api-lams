package com.lams.api.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.master.BusinessTypeMstr;

public interface BusinessTypeMstrRepository extends JpaRepository<BusinessTypeMstr, Long> {

	public List<BusinessTypeMstr> findByIsActive(Boolean isActive);
}
