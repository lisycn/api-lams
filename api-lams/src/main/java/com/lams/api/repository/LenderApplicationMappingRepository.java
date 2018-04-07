package com.lams.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lams.api.domain.LenderApplicationMapping;
import com.lams.api.domain.master.ApplicationTypeMstr;

public interface LenderApplicationMappingRepository extends JpaRepository<LenderApplicationMapping, Long> {

	@Query("select lam from LenderApplicationMapping lam where lam.userId =:userId and lam.isActive =:isActive")
	public List<LenderApplicationMapping> getApplicationTypesByUserIdAndIsActive(@Param("userId") Long userId,
			@Param("isActive") Boolean isActive);
	
	@Query("select lam.applicationTypeId from LenderApplicationMapping lam where lam.userId =:userId and lam.isActive =:isActive")
	public List<ApplicationTypeMstr> getApplicationByUserIdAndIsActive(@Param("userId") Long userId,
			@Param("isActive") Boolean isActive);

	@Modifying
	@Query("update LenderApplicationMapping lam set lam.isActive = false,lam.modifiedDate = NOW(),lam.modifiedBy =:modifiedBy where lam.userId =:userId and lam.isActive = true")
	public int inActiveByUserId(@Param("userId") Long userId, @Param("modifiedBy") Long modifiedBy);

	@Query("select lam.applicationTypeId.id from LenderApplicationMapping lam where lam.userId =:userId and lam.isActive =:isActive")
	public List<Long> getApplicationTypesByUserId(@Param("userId") Long userId, @Param("isActive") Boolean isActive);
	
	@Query("select lam.id from LenderApplicationMapping lam where lam.userId =:userId and lam.applicationTypeId.id =:applicationTypeId")
	public List<Long> findByUserIdAndApplicationTypeId(@Param("userId") Long userId, @Param("applicationTypeId") Long applicationTypeId);
	
}
