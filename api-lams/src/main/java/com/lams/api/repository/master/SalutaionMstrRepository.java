package com.lams.api.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.master.SalutaionMstr;

public interface SalutaionMstrRepository extends JpaRepository<SalutaionMstr, Long> {

	public List<SalutaionMstr> findByIsActive(Boolean isActive);
}
