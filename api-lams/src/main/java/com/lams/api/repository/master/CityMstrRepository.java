package com.lams.api.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.master.CityMstr;

public interface CityMstrRepository extends JpaRepository<CityMstr, Long> {
	public List<CityMstr> findByStateId(Long stateId);
}
