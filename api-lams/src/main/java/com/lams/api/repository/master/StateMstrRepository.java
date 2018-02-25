package com.lams.api.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.master.StateMstr;

public interface StateMstrRepository extends JpaRepository<StateMstr, Long> {

	public List<StateMstr> findByCountryId(Long countryId);
}
