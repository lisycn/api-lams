package com.lams.api.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.master.CountryMstr;

public interface CountryMstrRepository extends JpaRepository<CountryMstr, Long> {

	public List<CountryMstr> findByIsActive(Boolean isActive);
}
