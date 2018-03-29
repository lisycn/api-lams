package com.lams.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lams.api.domain.LenderBorrowerConnection;

public interface LenderBorrowerConnectionRepository extends JpaRepository<LenderBorrowerConnection, Long> {

	@Query("select lb from LenderBorrowerConnection lb where lb.application.status =:status and lb.isActive = true and lb.application.id =:appId and lb.application.isActive = true")
	public List<LenderBorrowerConnection> getListByApplication(@Param("appId")Long appId,@Param("status")String status);
}
